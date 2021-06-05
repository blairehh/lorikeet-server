package lorikeet.subsystem;

import lorikeet.disk.DiskFile;
import lorikeet.exceptions.FailedToLoadJARFileException;
import lorikeet.exceptions.NoIndexFileInSubSystemJARException;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class SubSystemJARLoader {
    public SubSystemJARLoadResult load(DiskFile file) {
        try {
            final JarFile jarFile = new JarFile(file.fullPath());
            final Enumeration<JarEntry> enumeration = jarFile.entries();
            final URL[] urls = { file.asJarURL() };
            final URLClassLoader classLoader = URLClassLoader.newInstance(urls);
            final List<Class<?>> javaClasses = new ArrayList<>();

            boolean foundIndex = false;

            while (enumeration.hasMoreElements()) {
                final JarEntry entry = enumeration.nextElement();
                if (entry.isDirectory()) {
                    continue;
                }
                if (entry.getName().equals("index.lorikeet")) {
                    foundIndex = true;
                }
                if(!entry.getName().endsWith(".class")){
                    continue;
                }

                final String className = entry.getName()
                    .substring(0, entry.getName().length() - 6)
                    .replace('/', '.');

                javaClasses.add(classLoader.loadClass(className));
            }

            if (!foundIndex) {
                return new SubSystemJARLoadResult.InvalidJAR(new NoIndexFileInSubSystemJARException(file));
            }

            return new SubSystemJARLoadResult.Ok(new SubSystemJAR(javaClasses));
        } catch (IOException | ClassNotFoundException ioe) {
            return new SubSystemJARLoadResult.UnexpectedError(new FailedToLoadJARFileException(file.fullPath(), ioe));
        }
    }
}
