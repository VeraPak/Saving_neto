import java.io.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        GameProgress gameProgress1 = new GameProgress(10, 3, 1, 500);
        GameProgress gameProgress2 = new GameProgress(7, 2, 2, 300);
        GameProgress gameProgress3 = new GameProgress(4, 1, 3, 100);

        /*
        Задание 2 - сохранение объектов в файл и архивация
         */
        SaveGameZip.saveGame("/Users/vera/Games/savegames/save1.dat", gameProgress1);
        SaveGameZip.saveGame("/Users/vera/Games/savegames/save2.dat", gameProgress2);
        SaveGameZip.saveGame("/Users/vera/Games/savegames/save3.dat", gameProgress3);

        File folder = new File("/Users/vera/Games/savegames");
        File[] listOfFiles = folder.listFiles();

        SaveGameZip.zipFiles("/Users/vera/Games/savegames/zip.zip", listOfFiles);

        for (File f : listOfFiles) {
            f.delete();
        }

        /*
        Задание 3 - разархивация zip файлов и чтение
         */
        Thread.sleep(300);

        UnZip.openZip("/Users/Vera/Games/savegames/zip.zip", "/Users/vera/Games/savegames");
        System.out.println(UnZip.openProgress("/Users/vera/Games/savegames/save2.dat"));
    }
}
