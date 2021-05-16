package lorikeet.subsystem;

import lorikeet.disk.DiskFile;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class SubSystemJARLoader {
    public SubSystemJAR load(DiskFile file) {

        try {
            final JarFile jarFile = new JarFile(file.fullPath());
            final Enumeration<JarEntry> enumeration = jarFile.entries();
            final URL[] urls = { file.asJarURL() };
            final URLClassLoader classLoader = URLClassLoader.newInstance(urls);

            final List<Class<?>> javaClasses = new ArrayList<>();
            while (enumeration.hasMoreElements()) {
                final JarEntry entry = enumeration.nextElement();
                if (entry.isDirectory()) {
                    continue;
                }
                if(!entry.getName().endsWith(".class")){
                    continue;
                }

                final String className = entry.getName()
                    .substring(0, entry.getName().length() - 6)
                    .replace('/', '.');

                javaClasses.add(classLoader.loadClass(className));
            }

            return new SubSystemJAR(javaClasses, null);
        } catch (IOException | ClassNotFoundException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }
}
