package API.Exercise.Football;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Competitions {
    private int id;
    private Area area;
    private String name;
    private String code;
    private String plan;
    private CurrentSeason currentSeason;
    private int numberOfAvailableSeasons;
    private String lastUpdated;
}
