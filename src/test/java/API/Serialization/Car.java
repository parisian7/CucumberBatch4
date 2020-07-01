package API.Serialization;

public class Car {

    private String name;
    private int year;
    private int mile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMile() {
        return mile;
    }

    public void setMile(int mile) {
        this.mile = mile;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String brand;
    private String type;

    public Car(String name, String brand, int mile , int year){
        this.name=name;
        this.brand=brand;
        this.mile=mile;
        this.year=year;

    }
}
