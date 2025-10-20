package com.patientmanagement.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    // Read all lines from a file
    public static List<String> readFile(String filePath) {
        List<String> lines = new ArrayList<>();
        File file = new File(filePath);

        // Create file if not existing
        if (!file.exists()) {
            try { file.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
            return lines;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    // Write lines to file (overwrite)
    public static void writeFile(String filePath, List<String> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false))) {
            for (String line : data) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Append a single line
    public static void appendLine(String filePath, String line) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
