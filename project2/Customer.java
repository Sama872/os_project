package project2;

import java.util.Date;

class Customer implements Runnable {

    int customerId;
    Date inTime;
 
    Bshop shop;
 
    public Customer(Bshop shop) {
    
        this.shop = shop;
    }
 
    public int getCustomerId() {										
        return customerId;
    }
 
    public Date getInTime() {
        return inTime;
    }
 
    public void setcustomerId(int customerId) {
        this.customerId = customerId;
    }
 
    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }
 
    public void run() {													
    
        goForHairCut();
    }
    private synchronized void goForHairCut() {							
    
        shop.add(this);
    }
}