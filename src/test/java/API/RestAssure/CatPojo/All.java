package API.RestAssure.CatPojo;

import lombok.Getter;
import lombok.Setter;
//this dependency Annotations provides you the getters and setter No need to generate getters and setter after this time
@Getter @Setter
public class All {

    private String _id;
    private String text;
    private String type;
    private User user;
    private int upvotes;
    private Object userUpvoted;


}
