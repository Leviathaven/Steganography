package com.readfiles;

import java.io.*;
import java.util.*;

public class ReadFiles {

    public static List<String> readFile(String path, boolean converts) {
        List<String> result = new ArrayList<String>();
        InputStream ins = null;
        Reader r = null;
        BufferedReader br = null;
        try {
            String currLine;
            ins = new FileInputStream(path);
            r = new InputStreamReader(ins, "UTF-8");
            br = new BufferedReader(r);
            while ((currLine = br.readLine()) != null) {
                if(converts) {
                    String convertedToBinary = convertStringToBinary(currLine);
                    result.add(convertedToBinary);
                }
                else {
                    result.add(currLine);
                }
            }
            return result;
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        finally {
            if (br != null) { try { br.close(); } catch(Throwable t) {  } }
            if (r != null) { try { r.close(); } catch(Throwable t) { } }
            if (ins != null) { try { ins.close(); } catch(Throwable t) {  } }
        }
        return new ArrayList<String>();
    }

    public static String convertStringToBinary(String input) {

        StringBuilder result = new StringBuilder();
        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            result.append(
                    String.format("%8s", Integer.toBinaryString(aChar))
                            .replaceAll(" ", "0")
            );
        }
        return result.toString();

    }
}