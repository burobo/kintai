//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.naosim.dddwork.domain.word;

import java.math.BigDecimal;

public class WorkingMinute {
    private final BigDecimal value;

    public WorkingMinute(BigDecimal value) {
        this.value = value;
    }

    public String toString() {
        return "WorkingMinute(" + this.getValue() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof WorkingMinute)) {
            return false;
        } else {
            WorkingMinute other = (WorkingMinute) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$value = this.getValue();
                Object other$value = other.getValue();
                if (this$value == null) {
                    if (other$value != null) {
                        return false;
                    }
                } else if (!this$value.equals(other$value)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof WorkingMinute;
    }

    public int hashCode() {
        //int PRIME = true;
        int result = 1;
        Object $value = this.getValue();
        //int result = result * 59 + ($value == null ? 43 : $value.hashCode());
        result = result * 59 + ($value == null ? 43 : $value.hashCode());
        return result;
    }

    public BigDecimal getValue() {
        return this.value;
    }
}
