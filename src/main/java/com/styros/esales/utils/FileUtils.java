package com.styros.esales.utils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * Created by atul on 11/19/2017.
 */
public class FileUtils {

    public static String getResourceFileAsString(String resourceFileName) {
        InputStream is = FileUtils.class.getClassLoader().getResourceAsStream(resourceFileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        return reader.lines().collect(Collectors.joining("\n"));
    }

    public static void writeStringToFile(String path,String contents) throws Exception {
        FileOutputStream fos=null;
        try{
            fos = new FileOutputStream(path);
            fos.write(contents.getBytes());
        } catch (IOException e) {
            throw e;
        }
        finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String readFileAsString(String path)throws Exception{
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded,Charset.defaultCharset());
    }
}
