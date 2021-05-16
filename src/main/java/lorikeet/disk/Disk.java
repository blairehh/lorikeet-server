package lorikeet.disk;

import java.io.File;

public class Disk {
    public FileDigResult digFile(String filePath) {
        final File file = new File(filePath);
        if (!file.exists()) {
            return new FileDigResult.NotFound(filePath);
        }
        return new FileDigResult.Found(new DiskFile(file.getAbsolutePath(), file));
    }
}
