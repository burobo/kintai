package com.naosim.dddwork.kintai_management.domain.word;

import com.naosim.dddwork.kintai_management.domain.system.IsPresentCheckable;
import jp.co.biglobe.lib.publication.date.DateFormatter;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 勤務開始時刻
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class WorkingStartTime implements IsPresentCheckable {
    @Getter
    private final String value;

    public static WorkingStartTime blank() {
        return new WorkingStartTime(null);
    }

    public String getFormatValue() {
        if (!isPresent()) {
            return "";
        }
        return value;
    }
}
