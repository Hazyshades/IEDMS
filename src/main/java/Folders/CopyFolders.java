package Folders;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class CopyFolders {
    public static void main(String[] args) {
        incomingCopyContainer();
    }
    public static void incomingCopyContainer() {
        String localSourceFolderPath = "C:\\Users\\Kolbasov.L\\Documents\\GitHub\\IEDMS\\src\\main\\resources\\localOrg=sber";
        String remoteDestinationFolderPath = "\\\\banksy-sc\\c$\\Program Files (x86)\\Digital Design\\IEDMS Interface Service\\IEDMS\\Incoming";

        try {
            Path sourcePath = Paths.get(localSourceFolderPath);
            Path destinationPath = Paths.get(remoteDestinationFolderPath);

            // Копирование папки с заменой существующих файлов
            Files.walkFileTree(sourcePath, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    Path targetDir = destinationPath.resolve(sourcePath.relativize(dir));
                    if (!Files.exists(targetDir))
                        Files.createDirectory(targetDir);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.copy(file, destinationPath.resolve(sourcePath.relativize(file)), StandardCopyOption.REPLACE_EXISTING);
                    return FileVisitResult.CONTINUE;
                }
            });

            System.out.println("Папка успешно скопирована.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }