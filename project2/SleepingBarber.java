package project2;


import static java.util.concurrent.TimeUnit.SECONDS;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class SleepingBarber {
	private static int noOfBarbers;
	private static int customerId;
	private static int noOfCustomers;
	private static int noOfChairs;
	public static void main (String a[]) throws InterruptedException {	
		
	//	int noOfBarbers=2, customerId=1, noOfCustomers=3, noOfChairs;	
		//int test;
		customerId=1;
		
		//Scanner sc = new Scanner(System.in);
               JFrame f = new JFrame("BarberShop :)");
               JLabel label5;
               label5 = new JLabel("number of barbers");
               label5.setBounds(10,30,200,30);
               f.add(label5);
               
               JLabel label6;
               label6 = new JLabel("number of customers");
               label6.setBounds(10,60,200,30);
               f.add(label6);
               
               JLabel label7;
               label7 = new JLabel("number of chairs");
               label7.setBounds(10,90,200,30);
               f.add(label7);
               
               JButton button1=new JButton("Run");
               button1.setBounds(150,200,150,80);
               f.add(button1);
               
JTextField b1 = new JTextField("");
b1.setBounds(130,30,200,30);
f.add(b1);

JTextField b2 = new JTextField("");
b2.setBounds(130,60,200,30);
f.add(b2);

JTextField b3 = new JTextField("");
b3.setBounds(130,90,200,30);
JLabel label;
JLabel label2;
label = new JLabel("");
 label2 = new JLabel("");

label.setBounds(40,100,1000,100);
label2.setBounds(40,110,1000,100);
label2.setText("\nBarber shop closed");
f.add(label);
f.add(b3);
f.setSize(500,500);
f.setLayout(null);
f.setVisible(true);
	
		//System.out.println("Enter 1 to run to start");			
    	//test=sc.nextInt();
button1.addActionListener(new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {
    	noOfBarbers =	Integer.parseInt(b1.getText());
        noOfCustomers =	Integer.parseInt(b2.getText());
       noOfChairs =	Integer.parseInt(b3.getText());
		ExecutorService exec = Executors.newFixedThreadPool(12);		
    	Bshop shop = new Bshop(noOfBarbers, noOfChairs);				
    	Random r = new Random();  										
       	    	
        System.out.println("\nBarber shop opened with "
        		+noOfBarbers+" barber(s)\n");
        
        long startTime  = System.currentTimeMillis();					
        
        for(int i=1; i<=noOfBarbers;i++) {								
        	
        	Barber barber = new Barber(shop, i);	
        	Thread thbarber = new Thread(barber);
            exec.execute(thbarber);
        }
        
        for(int i=0;i<noOfCustomers;i++) {								
        
            Customer customer = new Customer(shop);
            customer.setInTime(new Date());
            Thread thcustomer = new Thread(customer);
            customer.setcustomerId(customerId++);
            exec.execute(thcustomer);
            
            try {
            	
            	double val = r.nextGaussian() * 2000 + 2000;				
            	int millisDelay = Math.abs((int) Math.round(val));		
            	Thread.sleep(millisDelay);								
            }
            catch(InterruptedException iex) {
            
                iex.printStackTrace();
            }
            
        }
        
        exec.shutdown();												
       try {
		exec.awaitTermination(12, SECONDS);
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}								
 
        long elapsedTime = System.currentTimeMillis() - startTime;		
        
       
        label2.setText("\nBarber shop closed");
        System.out.println("\nTotal time elapsed in seconds"
        		+ " for serving "+noOfCustomers+" customers by "
        		+noOfBarbers+" barbers with "+noOfChairs+
        		" chairs in the waiting room is: "
        		+TimeUnit.MILLISECONDS
        	    .toSeconds(elapsedTime));
     
               label.setText("\nTotal customers: "+noOfCustomers+
        		"   \nTotal customers served: "+shop.getTotalHairCuts()
        		+"   \nTotal customers lost: "+shop.getCustomerLost());
               
    	
    }
});
    	
        //sc.close();
    }
}
 
