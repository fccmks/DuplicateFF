package ru.mks.duplicateff;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Created by moiseev on 30.03.2017.
 */
public class Main {
    public static void main(String[] args) {
        File f = new File("D:\\tmp\\dff");
        if (f.isDirectory()) System.out.println("It is DIRECTORY!!!");
        System.out.println(Arrays.asList(f.listFiles().length));
        
    }
}
