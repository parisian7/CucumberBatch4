package API.Serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class SerializationCar {

    @Test
    public void serialzition() throws IOException {

//        Car car = new Car("BMW", "m3", 20000, 2018);
//
//        car.setType("coupe");

        Car car=Car.builder()
                .name("BMW")
                .brand("m3")
                .mile(20000)
                .year(2018)
                .build();

        car.setType("coupe");

        System.out.println(car.toString());

        ObjectMapper objectMapper=new ObjectMapper();

        objectMapper.writeValue(new File("target/car.json"),car);


    }
}
