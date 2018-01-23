package com.naosim.dddwork.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@ToString
public class TotalData extends ProcessData {

    @Getter
    private List<String> registLineList;

    @Getter
    private int totalWorkMinutes;

    @Getter
    private int totalOverWorkMinutes;

    public TotalData(InputKintai inputKintai, List<String> registLineList) {
        super(inputKintai);
        this.registLineList = registLineList;
        this.setTotalData();
    }

    public String getPrintStringForTotalWorkTime() {
        return getPrintString("勤務時間", this.totalWorkMinutes);
    }

    public String getPrintStringForTotalOverWorkTime() {
        return getPrintString("残業時間", this.totalOverWorkMinutes);
    }

    private String getPrintString(String title, int totalTime) {
        return String.format("%s: %s時間%s分", title, totalTime / 60, totalTime % 60);
    }

    private void setTotalData() {
        this.totalWorkMinutes = 0;
        this.totalOverWorkMinutes = 0;

        Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
        Map<String, Integer> totalOverWorkMinutesMap = new HashMap<>();

        for (String line : registLineList) {

            String[] columns = line.split(",");

            LineData lineData = new LineData(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5]);

            if(!lineData.getWorkDate().startsWith(this.inputKintai.getYearMonth())) {
                continue;
            }
            totalWorkMinutesMap.put(lineData.getWorkDate(), Integer.valueOf(lineData.getWorkMinutes()));
            totalOverWorkMinutesMap.put(lineData.getWorkDate(), Integer.valueOf(lineData.getOverWorkMinutes()));
        }

        Set<String> keySet = totalWorkMinutesMap.keySet();
        for(String key : keySet) {
            this.totalWorkMinutes += totalWorkMinutesMap.get(key);
            this.totalOverWorkMinutes += totalOverWorkMinutesMap.get(key);
        }
    }

    @Override
    protected boolean isCorrectMethodType() {
        return InputKintai.MethodType.TOTAL.equals(this.inputKintai.getMethodType());
    }
}
