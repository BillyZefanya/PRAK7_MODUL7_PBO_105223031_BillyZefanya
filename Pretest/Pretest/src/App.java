class Vehicle {
    void speedUp() {
        System.out.println("Vehicle accelerating...");
    }
}

class Car extends Vehicle {
    @Override
    void speedUp() {
        System.out.println("Car accelerating by 22 units...");
    }

    void drift() {
        System.out.println("Performing a drift!");
    }
}

public class Main {
    public static void main(String[] args) {

        Vehicle myVehicle = new Car(); 

        myVehicle.speedUp(); 

        if (myVehicle instanceof Car) {
            ((Car) myVehicle).drift();
        } else {
            System.out.println("Bukan objek Car");
        }
    }
}