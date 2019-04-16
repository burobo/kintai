package com.naosim.dddwork.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Value
@Builder
public class Attendance implements WorkTimeOfDay {

    private final LocalDate date;
    private final TimePoint startTime;
    private final TimePoint endTime;
    private final WorkMinute workMinute;
    private final WorkMinute overWorkMinute;
    private final LocalDateTime createAt;
}
