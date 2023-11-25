package Controller;

import Users.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVReader {
    public static ArrayList<User> readCSV(String filepath, Roles.ROLES role) {
        try {
            ArrayList<User> allUsers = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            String line;
            boolean firstRow = true;
            int i = 0;
            HashMap<Integer, ArrayList<String>> data = new HashMap<>();

            while ((line = br.readLine()) != null) {
                if (firstRow) {
                    firstRow = false;
                    continue;
                }
                ArrayList<String> arr = new ArrayList<>();
                String[] values = line.split(",");
                boolean isEmptyRow = true;

                for (String value : values) {
                    value = value.trim();
                    if (value.contains("@") && value.endsWith(";")) {
                        value = value.substring(0, value.length() - 1);
                    }
                    arr.add(value.toUpperCase());
                    if (!value.isEmpty()) {
                        isEmptyRow = false;
                    }
                }

                if (!isEmptyRow) {
                    data.put(i, arr);
                    i++;
                }
            }

            for (Map.Entry<Integer, ArrayList<String>> entry : data.entrySet()) {
                ArrayList<String> arrayRef = entry.getValue();
                User temp;
                if (role == Roles.ROLES.STAFF) {
                    temp = new Staff(arrayRef.get(0), arrayRef.get(1), Faculty.getFacultyType(arrayRef.get(2)));
                } else {
                    temp = new Student(arrayRef.get(0), arrayRef.get(1), Faculty.getFacultyType(arrayRef.get(2)));
                }
                System.out.println(temp.toString());
                allUsers.add(temp);
            }

            br.close();
            return allUsers;
        } catch (IOException e) {
            return null;
        }
    }
}
