package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.word.WorkTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.ValidationException;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 退勤時刻Form
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
public class WorkTimeToForm {

    @Getter
    @Size(min = 4, max = 4)
    @Pattern(regexp = "^[0-9]*$")
    private String value;

    public WorkTimeToForm(String value) {

        // [-end:]を取り除いてからセット
        if (value != null) {
            String[] tmpStr = value.split(":", -1);
            if (tmpStr.length > 1) {
                value = tmpStr[1];
            }
            if (!"-end".equals(tmpStr[0])) {
                throw new ValidationException("指定された形式で入力してください：退勤時刻");
            }
        }
        this.value = value;
    }

    public WorkTime getValueObject() {
        return new WorkTime(value);
    }
}
