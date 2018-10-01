package jp.co.biglobe.kintai.datasource;

import jp.co.biglobe.kintai.domain.MonthlyWorkTimeCard;
import jp.co.biglobe.kintai.domain.WorkTime;
import jp.co.biglobe.kintai.domain.WorkTimeRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;


public class WorkTimeRepositoryDb implements WorkTimeRepository {

    private static final int Csv_Column_Date_Index = 0;
    private static final int Csv_Column_Minutes_Index = 3;
    private static final int Csv_Column_OverWorkMinutes_Index = 4;

    private static final String FileName = "data.csv";

    @Override
    public void input(final WorkTime workTime) {
        File file = new File(FileName);
        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n", workTime.getDate(), workTime.getStartTime(), workTime.getEndTime(),
                    workTime.getMinutes(), workTime.getOverWorkMinutes(), workTime.getNow()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<MonthlyWorkTimeCard> findWorkTimeCard(final String yearMonth) {

        MonthlyWorkTimeCard timeCard = new MonthlyWorkTimeCard();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(FileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                if (!columns[Csv_Column_Date_Index].startsWith(yearMonth)) {
                    continue;
                }
                WorkTime workTime = new WorkTime();
                workTime.setMinutes(Integer.valueOf(columns[Csv_Column_Minutes_Index]));
                workTime.setOverWorkMinutes(Integer.valueOf(columns[Csv_Column_OverWorkMinutes_Index]));

                timeCard.punch(columns[Csv_Column_Date_Index], workTime);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(timeCard.isPunched()){
            return Optional.ofNullable(timeCard);
        }else{
            return Optional.empty();
        }
    }
}