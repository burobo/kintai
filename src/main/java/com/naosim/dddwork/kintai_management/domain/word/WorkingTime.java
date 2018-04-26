package com.naosim.dddwork.kintai_management.domain.word;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 勤務時間
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class WorkingTime {
    @Getter
    private final Integer value;

    public static WorkingTime blank() {
        return new WorkingTime(null);
    }

    public static WorkingTime create(WorkingStartTime workingStartTime, WorkingEndTime workingEndTime, HolidayKind holidayKind) {

        int startH;
        int startM;
        int endH;
        int endM;

        if("v".equals(holidayKind.getValue())) {
            startH = 9;
            startM = 0;
            endH = 20;
            endM = 0;
        } else if("am".equals(holidayKind.getValue())) {
            startH = 9;
            startM = 0;
            endH = Integer.valueOf(workingEndTime.getValue().substring(0, 2));
            endM = Integer.valueOf(workingEndTime.getValue().substring(2, 4));

        } else if("pm".equals(holidayKind.getValue())) {
            startH = Integer.valueOf(workingStartTime.getValue().substring(0, 2));
            startM = Integer.valueOf(workingStartTime.getValue().substring(2, 4));
            endH = 20;
            endM = 0;
        } else {
            startH = Integer.valueOf(workingStartTime.getValue().substring(0, 2));
            startM = Integer.valueOf(workingStartTime.getValue().substring(2, 4));
            endH = Integer.valueOf(workingEndTime.getValue().substring(0, 2));
            endM = Integer.valueOf(workingEndTime.getValue().substring(2, 4));
        }

        int workMinutes = endH * 60 + endM - (startH * 60 + startM);

        if(endH == 12) {
            workMinutes -= endM;
        } else if(endH >= 13) {
            workMinutes -= 60;
        }

        // 休憩増える対応(増える日に合わせてモジュールリリースしてください)
        if(endH == 15) {
            workMinutes -= endM;
        } else if(endH >= 16) {
            workMinutes -= 60;
        }

        if(endH == 18) {
            workMinutes -= endM;
        } else if(endH >= 19) {
            workMinutes -= 60;
        }

        if(endH == 21) {
            workMinutes -= endM;
        } else if(endH >= 22) {
            workMinutes -= 60;
        }

        return new WorkingTime(Integer.valueOf(workMinutes));
    }


}
