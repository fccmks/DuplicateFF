package ru.mks.duplicateff;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.*;

/**
 * Created by moiseev on 30.03.2017.
 */
public class Main {
    private static ArrayList<File> eqFiles = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        File f = new File("D:\\tmp\\dff");
        File[] spisok = f.listFiles();
        for (File ef : spisok) {
            eqFiles.add(ef);
        }
        for (int i = 0; i < eqFiles.size(); i++) {
            for (int j = i+1; j < eqFiles.size(); j++) {
                if (eqFiles.get(i).length() == eqFiles.get(j).length()) {
                   if (checkEquality(eqFiles.get(i), eqFiles.get(j))) j = j - 1;
                }
            }
        }
//        for (File files : eqFiles) {
//            System.out.println(files);
//        }
        //System.out.println(Arrays.asList(eqFiles));
        //Arrays.sort(fileLength);
        //System.out.println(Arrays.toString(fileLength));
//        ArrayList<Long> eqFiles = new ArrayList<>();
//        for (int i = 0; i < fileLength.length - 1; i++) {
//          if ((i == 0) & (fileLength[i] == fileLength[i+1])) {
//              eqFiles.add(fileLength[i]);
////              eqFiles.add(fileLength[i+1]);
////              k = 2;
//          } else
//          if ((fileLength[i] == fileLength[i+1]) || (fileLength[i] == fileLength[i-1])) {
//              eqFiles.add(fileLength[i]);
//          }
//        }
//        System.out.println(Arrays.asList(eqFiles));
    }

    private static boolean checkEquality(File i, File j) throws Exception {
        String filename1 = i.toString();
        String filename2 = j.toString();
        if (getMD5Checksum(filename1).equals(getMD5Checksum(filename2))) {
            System.out.println(i + " = " + j);
            eqFiles.remove(j);
            return true;
        }
        return false;
    }

    public static byte[] createChecksum(String filename) throws Exception {
        InputStream fis = new FileInputStream(filename);
        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance("MD5");
        int numRead;
        do {
            numRead = fis.read(buffer);
            if (numRead > 0) {
                complete.update(buffer, 0, numRead);
            }
        } while (numRead != -1);
        fis.close();
        return complete.digest();
    }

    public static String getMD5Checksum(String filename) throws Exception {
        byte[] b = createChecksum(filename);
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result +=
                    Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }
}
