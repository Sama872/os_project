package project3;

import java.util.Date;

class Car implements Runnable {

    int carId;
    Date inTime;
 
    Cshop shop;
 
    public Car(Cshop shop) {
    
        this.shop = shop;
    }
 
    public int getCarId() {										
        return carId;
    }
 
    public Date getInTime() {
        return inTime;
    }
 
    public void setcarId(int carId) {
        this.carId = carId;
    }
 
    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }
 
    public void run() {													
    
        goForWashCar();
    }
    private synchronized void goForWashCar() {							
    
        shop.add(this);
    }
}
