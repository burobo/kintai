package refactor.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class OvertimeHours {
    @Getter
    private final int minutes;
}