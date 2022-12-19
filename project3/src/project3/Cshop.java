package project3;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

class Cshop {

	private final AtomicInteger totalCarWash = new AtomicInteger(0);
	private final AtomicInteger carsLost = new AtomicInteger(0);
	int npark, noOfEmployee, availableEmployee;
    List<Car> listCar;
    
    Random r = new Random();	 
    
    public Cshop(int noOfEmployees, int noOfParkings){
    
        this.npark = noOfParkings;														
        listCar = new LinkedList<Car>();						
      //  this.noOfEmployee = noOfEmployee;								
        availableEmployee = noOfEmployee;
    }
 
    public AtomicInteger getTotalCarWash() {
    	
    	totalCarWash.get();
    	return totalCarWash;
    }
    
    public AtomicInteger getCarLost() {
    	
    	carsLost.get();
    	return carsLost;
    }
    
    public void washCar(int employeeId)
    {
        Car car;
        synchronized (listCar) {									
        															 	
            while(listCar.size()==0) {
            
                System.out.println("\nEmployee "+employeeId+" is waiting "
                		+ "for the car and sleeps in its place");
                
                
                try {
                
                    listCar.wait();								
                }
                catch(InterruptedException iex) {
                
                    iex.printStackTrace();
                }
            }
            
            car = (Car)((LinkedList<?>)listCar).poll();	
            
            System.out.println("Car "+car.getCarId()+
            		" finds the employee asleep and wakes up "
            		+ "the employee "+employeeId);
        }
        
        int millisDelay=0;
                
        try {
        	
        	availableEmployee--; 										 
        																
            System.out.println("Employee "+employeeId+" wash the car of "+
            		car.getCarId()+ " so car sleeps");
        	
            double val = r.nextGaussian() * 2000 + 4000;				
        	millisDelay = Math.abs((int) Math.round(val));				
        	Thread.sleep(millisDelay);
        	
        	System.out.println("\nCompleted car wash of "+
        			car.getCarId()+" by employee " + 
        			employeeId +" in "+millisDelay+ " milliseconds.");
        
        	totalCarWash.incrementAndGet();
            															
            if(listCar.size()>0) {									
            	System.out.println("employee "+employeeId+					
            			" wakes up a car in the "					
            			+ "parking");		
            }
            
            availableEmployee++;											
        }
        catch(InterruptedException iex) {
        
            iex.printStackTrace();
        }
        
    }
 
    public void add(Car car) {
    
        System.out.println("\nCar "+car.getCarId()+
        		" enters through the entrance door in the the center at "
        		+car.getInTime());
 
        synchronized (listCar) {
        
            if(listCar.size() == npark) {							
            
                System.out.println("\nNo parking available "
                		+ "for car "+car.getCarId()+
                		" so car leaves the center");
                
              carsLost.incrementAndGet();
                
                return;
            }
            else if (availableEmployee > 0) {							
            															
            	((LinkedList<Car>)listCar).offer(car);
				listCar.notify();
			}
            else {														
            															
            	((LinkedList<Car>)listCar).offer(car);
                
            	System.out.println("All employee(s) are busy so "+
            			car.getCarId()+
                		" takes a park in the parking");
                 
                if(listCar.size()==1)
                    listCar.notify();
            }
        }
    }
}
