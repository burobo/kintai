package com.naosim.dddwork.api;

import com.naosim.dddwork.service.InputKintai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                throw new RuntimeException("引数が足りません");
            }
            String methodType = args[0];

            if ("input".equals(methodType)) {
                if (args.length < 4) {
                    throw new RuntimeException("引数が足りません");
                }
                String date = args[1];
                String start = args[2];
                String end = args[3];
                String now = LocalDateTime.now().toString();
                
                InputKintai.input(date, start, end);


            } else if ("total".equals(methodType)) {
                String yearMonth = args[1];
                if (args.length < 2) {
                    throw new RuntimeException("引数が足りません");
                }

                int totalWorkMinutes = 0;
                int totalOverWorkMinutes = 0;

                File file = new File("data.csv");

                try (
                        FileReader fr = new FileReader(file);
                        BufferedReader br = new BufferedReader(fr);
                ) {

                    String line = br.readLine();
                    Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
                    Map<String, Integer> totalOverWorkMinutesMap = new HashMap<>();
                    while (line != null) {
                        String[] columns = line.split(",");
                        if (!columns[0].startsWith(yearMonth)) {
                            continue;
                        }
                        totalWorkMinutesMap.put(columns[0], Integer.valueOf(columns[3]));
                        totalOverWorkMinutesMap.put(columns[0], Integer.valueOf(columns[4]));

                        line = br.readLine();
                    }

                    Set<String> keySet = totalWorkMinutesMap.keySet();
                    for (String key : keySet) {
                        totalWorkMinutes += totalWorkMinutesMap.get(key);
                        totalOverWorkMinutes += totalOverWorkMinutesMap.get(key);
                    }

                    System.out.println("勤務時間: " + totalWorkMinutes / 60 + "時間" + totalWorkMinutes % 60 + "分");
                    System.out.println("残業時間: " + totalOverWorkMinutes / 60 + "時間" + totalOverWorkMinutes % 60 + "分");
                }

            } else {
                throw new RuntimeException("methodTypeが不正です");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}