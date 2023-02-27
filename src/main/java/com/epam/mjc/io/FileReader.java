package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileReader {

    public Profile getDataFromFile(File file) {
        String name = "";
        int age = 0;
        String email = "";
        long phone = 0;
        Profile profile = null;

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            int ch;
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            while ((ch = fileInputStream.read()) != -1) {
                stringBuilder.append((char) ch);
            }
            line = stringBuilder.toString();
            String[] words = line.split("\n");
            for (String s : words) {
                if (s.startsWith("Name: ")) {
                    name = s.substring(6).trim();
                } else if (s.startsWith("Age: ")) {
                    age = Integer.parseInt(s.substring(5).trim());
                } else if (s.startsWith("Email: ")) {
                    email = s.substring(7).trim();
                } else if (s.startsWith("Phone: ")) {
                    phone = Long.parseLong(s.substring(7).trim());
                }
            }
            profile = new Profile(name, age, email, phone);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return profile;
    }

    public static void main(String[] args) {
        File testFile = new File("E:\\JavaRushTasks\\JavaRushTasks\\stage1-module6-io-task1\\src\\main\\resources\\Profile.txt");
        FileReader fileReader = new FileReader();
        Profile profile = fileReader.getDataFromFile(testFile);
        System.out.println(profile.toString());
    }
}
