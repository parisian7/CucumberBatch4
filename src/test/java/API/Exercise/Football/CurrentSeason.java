package API.Exercise.Football;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentSeason {
    private int id;
    private String startDate;
    private String endDate;
    private String currentMatchday;

}
