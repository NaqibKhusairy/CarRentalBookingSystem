package carrentalbookingsystem;

public class Car {
    
    private String CarModel , PlateNumber;

    public Car(String CarModel, String PlateNumber) {
        this.CarModel = CarModel;
        this.PlateNumber = PlateNumber;
    }

    public String getCarModel() {
        return CarModel;
    }

    public String getPlateNumber() {
        return PlateNumber;
    }
    
    
}
