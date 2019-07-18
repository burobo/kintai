package refactor.domain;

import java.util.Arrays;
import java.util.List;

public class LaborRegulations {
    public static List<BreakTime> getBreakTimeList() {
        return Arrays.asList(
                new BreakTime(new StartTime("1200"), new EndTime("1300")),
                new BreakTime(new StartTime("1800"), new EndTime("1900")),
                new BreakTime(new StartTime("2100"), new EndTime("2200"))
        );
    }

    public static StandardWorkingTime getStandardWorkingTime() {
        return new StandardWorkingTime(8 * 60);
    }
}