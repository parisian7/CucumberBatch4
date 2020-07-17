package API.Serialization;

import lombok.*;

//@Data  contains @Getter @Setter @ToString @EqualsAndHashCode @RequiredArgsConstructor
@AllArgsConstructor //generates constructor with all variables
@NoArgsConstructor // generates permanent default constructor
@Getter
@Setter
@ToString(callSuper = false)
//@Value
public class Car {

    private String name;
    private String brand;
    private int mile;
    private int year;


    private String type;

    @Builder  //I can put it directly on the class or on the method
    public Car(String name, String brand, int mile, int year) {
        this.name = name;
        this.brand = brand;
        this.mile = mile;
        this.year = year;

    }
}
