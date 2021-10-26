package com.writefiles;

import java.io.*;
import java.util.*;

public class WriteFiles {

    public static void writeFile(String path, List<String> toWrite) {

        try(FileWriter writer = new FileWriter(path, false))
        {
            for (String line: toWrite) {
                writer.write(line + "\n");
            }
            writer.close();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

    }
}