package com.naosim.dddwork.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@EqualsAndHashCode
@ToString
public class InputData {

    @AllArgsConstructor @Getter
    public enum MethodType {
        INPUT,
        TOTAL,
    }

    @Getter
    private final MethodType methodType;

    @Getter
    private String date;

    @Getter
    private String startTime;

    @Getter
    private String endTime;

    @Getter
    private String now;

    @Getter
    private String yearMonth;

    public InputData(String[] args) {
        if (args.length < 1) {
            throw new RuntimeException("引数が足りません");
        }

        String methodTypeStr = args[0];
        this.methodType = this.getMethodTypeFromString(methodTypeStr);

        switch (this.methodType) {
            case INPUT:
                this.setFieldsWhenInputProcess(args);
                break;
            case TOTAL:
                this.setFieldsWhenTotalProcess(args);
                break;
            default:
        }
    }

    private void setFieldsWhenInputProcess(String[] args) {
        if(args.length < 4) {
            throw new RuntimeException("引数が足りません");
        }
        this.date = args[1];
        this.startTime = args[2];
        this.endTime = args[3];
        this.now = LocalDateTime.now().toString();
    }

    private void setFieldsWhenTotalProcess(String[] args) {
        if(args.length < 2) {
            throw new RuntimeException("引数が足りません");
        }
        this.yearMonth = args[1];
    }

    private MethodType getMethodTypeFromString(String methodTypeStr) {
        try {
            return MethodType.valueOf(methodTypeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("methodTypeが不正です");
        }
    }
}
