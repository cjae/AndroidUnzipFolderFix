package org.skilli.skillimobile.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by Jedidiah on 15/09/2016.
 */
public class DecompressFile {

    private String zipFile;
    private String destinationFile;

    public DecompressFile(String zipFile, String destinationFile) {
        this.zipFile = zipFile;
        this.destinationFile = destinationFile;
        dirChecker("");
    }

    public boolean unzipFolder(){
        InputStream is;
        ZipInputStream zis;
        try
        {
            String filename;
            is = new FileInputStream(zipFile);
            zis = new ZipInputStream(new BufferedInputStream(is));
            ZipEntry ze;
            byte[] buffer = new byte[1024];
            int count;

            while ((ze = zis.getNextEntry()) != null) {
                filename = ze.getName();

                if (ze.isDirectory()) {
                    dirChecker(filename);
                    continue;
                }

                File fmd = new File(destinationFile + filename);
                File destinationParent = fmd.getParentFile();
                if(!destinationParent.exists()){
                    destinationParent.mkdirs();
                }

                FileOutputStream fout = new FileOutputStream(fmd);

                while ((count = zis.read(buffer)) != -1)
                {
                    fout.write(buffer, 0, count);
                }

                fout.close();
                zis.closeEntry();
            }

            zis.close();
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void dirChecker(String dir) {
        File f = new File(destinationFile + dir);

        if (!f.isDirectory()) {
            f.mkdirs();
        }
    }
}
