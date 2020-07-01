package API.Serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class SerializationCar {

    @Test
    public void serialzition() throws IOException {

        Car car = new Car("BMW", "m3", 20000, 2018);

        car.setType("coupe");

        ObjectMapper objectMapper=new ObjectMapper();

        objectMapper.writeValue(new File("target/car.json"),car);


    }
}
