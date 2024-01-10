import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public abstract class SaveGameZip {

    static void saveGame(String pathToFile, GameProgress gameProgress) {
        try(FileOutputStream fos = new FileOutputStream(pathToFile);
            ObjectOutputStream ous = new ObjectOutputStream(fos)) {
            ous.writeObject(gameProgress);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static void zipFiles(String archiveName, File[] files) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(archiveName))) {
            for (File file : files) {
                try (FileInputStream fis = new FileInputStream(file.getPath())) {
                    ZipEntry entry = new ZipEntry(file.getName());
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
