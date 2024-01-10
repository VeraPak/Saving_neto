import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public abstract class UnZip {
    static GameProgress openProgress(String path) {
        GameProgress gp;
        try(FileInputStream fin = new FileInputStream(path);
            ObjectInputStream out = new ObjectInputStream(fin)) {
            gp = (GameProgress) out.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return gp;
    }

    static void openZip(String fromFile, String toFolder) {
        try(ZipInputStream zin = new ZipInputStream(new FileInputStream(fromFile))) {
            ZipEntry entry;
            String name;

            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(toFolder + "/" + name);
                for (int c = zin.read(); c !=-1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
