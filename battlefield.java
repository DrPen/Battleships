package battleships;


public class battlefield {
	private int field[][];
	private boat boats[];
	
	
	private void placeShip(String x, String y) throws Exception {
		if(x.length()!= 2 || y.length() !=2) {
			throw new Exception("Invalid Coordinate");
		}
		
		// a 97 b 98 etc
		int x1 = (Integer.parseInt(x.substring(1).toLowerCase())) - 97;
		int x2 = (Integer.parseInt(x.substring(0, 1))) - 1;
				
		int y1 = (Integer.parseInt(x.substring(1).toLowerCase())) - 97;
		int y2 = (Integer.parseInt(x.substring(0, 1))) - 1;
					
		// are coords valid in terms of length? try catch after for placement
		
		
		
	}
	
	public battlefield() {
		this.field = new int[10][10];
		
		// initialize field with 0
		for(int[] y: field) {
			for(int x: y) {
				x = 0;
			}	
		}
		
		
		
		System.out.println("Enter Coordinates to place your ship!");
		System.out.println("Place your 2 hp ship!");
	}
	
}


