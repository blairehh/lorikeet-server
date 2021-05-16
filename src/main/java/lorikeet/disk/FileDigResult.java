package lorikeet.disk;

public sealed interface FileDigResult {
    record Found(DiskFile file) implements FileDigResult {}
    record NotFound(String filePath) implements FileDigResult {}
}
