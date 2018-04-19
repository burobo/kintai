package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.word.WorkTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 出勤日時Form
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
public class WorkTimeFromForm {

    @Getter
    @Size(min = 4, max = 4)
    @Pattern(regexp = "^[0-9]*$")
    private String value;

    public WorkTimeFromForm(String value) {
        this.value = value;
    }

    public WorkTime getValueObject() {
        return new WorkTime(value);
    }
}
