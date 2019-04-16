package com.naosim.dddwork.api;

import com.naosim.dddwork.domain.AttendanceSummary;
import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.WorkMinute;
import com.naosim.dddwork.domain.WorkRegulationException;
import com.naosim.dddwork.domain.YearMonth;
import com.naosim.dddwork.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    public String command(String[] args) {
        try {
            if (args.length < 1) return "引数が足りません";

            String methodType = args[0];

            if ("input".equals(methodType)) {
                if (args.length < 4) return "引数が足りません";

                String date = args[1];
                String start = args[2];
                String end = args[3];
                this.input(date, start, end);
                return "";
            }

            if ("total".equals(methodType)) {
                if (args.length < 2) return "引数が足りません";

                String yearMonth = args[1];
                return this.total(yearMonth);
            }
        } catch (WorkRegulationException e) {
            return e.getMessage();
        }

        return "methodTypeが不正です";
    }

    private void input(String dateStr, String startStr, String endStr) {
        LocalDate date;
        TimePoint startTime;
        TimePoint endTime;
        try {
            date = parseToLocalDate(dateStr);
            startTime = parseToTimePoint(startStr);
            endTime = parseToTimePoint(endStr);
        } catch (DateTimeException e) {
            throw new WorkRegulationException("引数が不正です");
        }
        attendanceService.saveAttendance(date, startTime, endTime);
    }

    private String total(String yearMonthStr) {
        YearMonth yearMonth;
        try {
            yearMonth = parseToYearMonth(yearMonthStr);
        } catch (DateTimeException e) {
            throw new WorkRegulationException("引数が不正です");
        }
        AttendanceSummary summary = attendanceService.fetchAttendanceSummary(yearMonth);
        return toDisplayString(summary);
    }

    private String toDisplayString(AttendanceSummary summary) {
        return "勤務時間: " + toDisplayString(summary.getTotalWorkMinute()) + "\n"
                + "残業時間: " + toDisplayString(summary.getTotalOverWorkMinute());
    }

    private String toDisplayString(WorkMinute minute) {
        return minute.getValue() / 60 + "時間" + minute.getValue() % 60 + "分";
    }

    // TODO: should be moved to another package or class

    private LocalDate parseToLocalDate(String value) {
        return LocalDate.parse(value, DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    private TimePoint parseToTimePoint(String value) {
        // TODO: Validation
        int hour = Integer.valueOf(value.substring(0, 2));
        int minute = Integer.valueOf(value.substring(2, 4));
        return TimePoint.of(hour, minute);
    }

    private YearMonth parseToYearMonth(String value) {
        LocalDate date = parseToLocalDate(value + "01");
        return new YearMonth(date.getYear(), date.getMonthValue());
    }
}
