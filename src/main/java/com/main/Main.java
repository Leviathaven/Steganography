package com.main;

import com.readfiles.ReadFiles;
import com.writefiles.WriteFiles;
import java.io.*;
import java.util.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args)  throws IOException {

        System.out.println("Put in the name of the file where to read from: ");
        Scanner in = new Scanner(System.in);
        String readPath = in.nextLine();
        String readPathFile = readPath + ".txt";
        System.out.println("Put in the name of the file where to hide in: ");
        String hidePath = in.nextLine();
        String hidePathFile = hidePath + ".txt";
        System.out.println("Put in the name of the file where to write to: ");
        String writePath = in.nextLine();
        String writePathFile = writePath + ".txt";
        List<String> theEncodedFile = ReadFiles.readFile(readPathFile, true);
        String encodedInfo = theEncodedFile.stream()
                            .map(n -> String.valueOf(n))
                            .collect(Collectors.joining());
        List<String> theFileWhereItIsHidden = ReadFiles.readFile(hidePathFile, false);
        String[] arrayOfFile = theFileWhereItIsHidden.toArray(new String[theFileWhereItIsHidden.size()]);
        int currNum = 0;
        int numOfLine = 0;
        List<String> resultingFile = new ArrayList<String>();
        boolean cantHideThere = false;
        while (currNum <= encodedInfo.length() - 1) {
            String currentLine = arrayOfFile[numOfLine].trim();
            int cnt = 0;
            int numOfSpaces = 1;
            if (encodedInfo.charAt(currNum) == '1') {
                numOfSpaces = 2;
            }
            while (cnt + numOfSpaces <= 5) {
                for(int i = 1; i <= numOfSpaces; i++) {
                    currentLine += " ";
                }
                cnt += numOfSpaces;
                currNum++;
                if (currNum <= encodedInfo.length() - 1) {
                    if (encodedInfo.charAt(currNum) == '0') {
                        numOfSpaces = 1;
                    } else if (encodedInfo.charAt(currNum) == '1') {
                        numOfSpaces = 2;
                    }
                }
            }
            resultingFile.add(currentLine);
            numOfLine++;
            if (numOfLine >= arrayOfFile.length) {
                cantHideThere = true;
                break;
            }
        }
        if (!cantHideThere) {
            int diff = theFileWhereItIsHidden.size() - resultingFile.size();
            if (diff > 0) {
                int indexOfStart = resultingFile.size();
                while (diff > 0) {
                    resultingFile.add(arrayOfFile[indexOfStart]);
                    indexOfStart++;
                    diff--;
                }
            }
            WriteFiles.writeFile(writePathFile, resultingFile);
        }
        else {

            WriteFiles.writeFile(writePathFile, Arrays.asList("Cannot finish the algorithm"));
        }
    }


}