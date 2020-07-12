package API.Serialization;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Pet {
    private String name;
    private int age;
    private int id;
    private String photoUrl;
    private String status;


    public Pet(String name , String status , int age){
        this.name=name;
        this.status=status;
        this.age=age;
    }
}
