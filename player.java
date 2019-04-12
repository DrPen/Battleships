package battleships;

import java.util.Scanner;

public class player {
	private int field[][];
	private boat boats[];
	
	
	private boolean placeShip(String x, String y, boat b) throws Exception {
		if(x.length()!= 2 || y.length() !=2) {
			throw new Exception("Invalid Coordinate");
		}
		
		// x1 -= 97;
		int x1 = x.substring(0, 1).toLowerCase().charAt(0) - 97;
		int x2 = (Integer.parseInt(x.substring(1)))-1;
				
		int y1 = y.substring(0, 1).toLowerCase().charAt(0) - 97;
		int y2 = (Integer.parseInt(y.substring(1)))-1;
		
				
		if((x1>y1 ? x1-y1:y1-x1)!=b.getLen()  && (x2>y2 ? x2-y2:y2-x2) != b.getLen()-1) {
			throw new Exception("Distance between coordinate is too long");
			
		}
		
		try {
			if(x1==y1) {	// vertical
				if(y1<y2) {
					for(int a = 0; a < b.getLen(); a++) {
						field[x1][y1+a] = b.getType();
					}
				}
			}
			
			if(y1>y2) {
				for(int a = 0; a < b.getLen(); a++) {
					field[x1][y2+a] = b.getType();
				}
			}
		
			
			if(x2==y2) {	// horizontal
				if(y1<y2) {
					for(int a = 0; a < b.getLen(); a++) {
						field[x1+a][y1] = b.getType();
					}
				}
			}
			
			if(y1>y2) {
				for(int a = 0; a < b.getLen(); a++) {
					field[x2+a][y1] = b.getType();
				}
			}
			return true;
			
		} catch(Exception e){
			throw new Exception("Error placing ship");
		}			
	}
	
	public player() {
		this.field = new int[10][10];
		
		// initialize field with 0
		for(int[] y: field) {
			for(int x: y) {
				x = 0;
			}	
		}
		
		boolean isSet = false;
		
		System.out.println("Enter Coordinates to place your ship!");
		
		System.out.println("Place your 2 hp ship!");
		boat b2 = new boat(2);  
		
		while(isSet != true) {
			System.out.println("First Coordinate");
		    Scanner scan = new Scanner (System.in);
		    String p1 = scan.nextLine();
		    
			System.out.println("Second Coordinate");
		    String p2 = scan.nextLine();
						
			try {
				isSet = placeShip(p1, p2, b2);	
			} catch(Exception e) {
				System.out.println(e.toString());
				System.out.println("Try again!");
			}
			// pop boats into vector MAKE IT A VECTOR of boats
			// add other boats
			// add combat
			// kill yourself
			
		}
	}
}
