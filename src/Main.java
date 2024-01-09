import java.io.*;
import java.util.*;
import java.util.zip.*;

public class Main {
    public static void main(String[] args) {
        GameProgress gameProgress1 = new GameProgress(10, 3, 1, 500);
        GameProgress gameProgress2 = new GameProgress(7, 2, 2, 300);
        GameProgress gameProgress3 = new GameProgress(4, 1, 3, 100);

        saveGame("/Users/vera/Games/savegames/save1.dat", gameProgress1);
        saveGame("/Users/vera/Games/savegames/save2.dat", gameProgress2);
        saveGame("/Users/vera/Games/savegames/save3.dat", gameProgress3);

        File folder = new File("/Users/vera/Games/savegames");
        File[] listOfFiles = folder.listFiles();

        zipFiles("/Users/vera/Games/savegames/zip.zip", listOfFiles);

        for (File f : listOfFiles) {
            f.delete();
        }
    }

    private static void saveGame(String pathToFile, GameProgress gameProgress) {
        try(FileOutputStream fos = new FileOutputStream(pathToFile);
            ObjectOutputStream ous = new ObjectOutputStream(fos)) {
            ous.writeObject(gameProgress);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void zipFiles(String archiveName, File[] files) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(archiveName))){
            for (File file : files) {
                try(FileInputStream fis = new FileInputStream(file.getPath())) {
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
