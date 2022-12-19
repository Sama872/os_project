package project2;

class Barber implements Runnable {										

    Bshop shop;
    int barberId;
 
    public Barber(Bshop shop, int barberId) {
    
        this.shop = shop;
        this.barberId = barberId;
    }
    
    public void run() {
    
        while(true) {
        
            shop.cutHair(barberId);
        }
    }
}
