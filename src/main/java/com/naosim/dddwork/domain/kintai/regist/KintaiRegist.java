package com.naosim.dddwork.domain.kintai.regist;


import com.naosim.dddwork.domain.kintai.KintaiOfOneDay;
import com.naosim.dddwork.domain.kintai.time.work.OverWorkMinutes;
import com.naosim.dddwork.domain.kintai.time.work.WorkMinutes;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class KintaiRegist {

    @Getter
    private final WorkStartAndEndTimeOfOneDay workStartAndEndTimeOfOneDay;

    public KintaiRegist(WorkStartAndEndTimeOfOneDay workStartAndEndTimeOfOneDay) {
        this.workStartAndEndTimeOfOneDay = workStartAndEndTimeOfOneDay;
    }

    public KintaiOfOneDay getKintaiOfOneDay() {
        WorkMinutes workMinutes = WorkMinutes.get(
                this.workStartAndEndTimeOfOneDay.getWorkStartTime(),
                this.workStartAndEndTimeOfOneDay.getWorkEndTime()
        );

        OverWorkMinutes overWorkMinutes = OverWorkMinutes.get(workMinutes);

        return new KintaiOfOneDay(
                this.workStartAndEndTimeOfOneDay.getWorkWorkDate(),
                this.workStartAndEndTimeOfOneDay.getWorkStartTime(),
                this.workStartAndEndTimeOfOneDay.getWorkEndTime(),
                workMinutes,
                overWorkMinutes,
                this.workStartAndEndTimeOfOneDay.getNow()
        );
    }
}
