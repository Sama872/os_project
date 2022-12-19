package project3;

//import project3.Cshop;

class Employee implements Runnable {										

   Cshop shop;
    int employeeId;
 
    public Employee(Cshop shop, int employeeId) {
    
        this.shop = shop;
        this.employeeId = employeeId;
    }
    
    public void run() {
    
        while(true) {
        
            shop.washCar(employeeId);
        }
    }
}
