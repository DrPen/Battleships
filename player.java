package battleships;

import java.util.Vector;
import java.util.Scanner;



public class player {	
	private int field[][];
	private Vector<boat> boats = new Vector<boat>();
	private int boatType = 2;

	private boolean placeShipLogic(String x, String y, boat b) throws Exception {
		if (x.length() < 2 || x.length() > 3 || y.length() < 2 || y.length() > 3) {
			throw new Exception("Invalid Coordinates");
		}

		// x1 -= 97;
		int x1, y1, x2, y2;

		try {
			x1 = (Integer.parseInt(x.substring(1, x.length()))) - 1;
			y1 = x.substring(0, 1).toLowerCase().charAt(0) - 97;

			x2 = (Integer.parseInt(y.substring(1, x.length()))) - 1;
			y2 = y.substring(0, 1).toLowerCase().charAt(0) - 97;
		} catch (Exception e) {
			throw new Exception("Invalid Coordinates");
		}

		if (x1 < 0 || x1 > 9 || x2 < 0 || x2 > 9) {
			throw new Exception("Invalid Coordinates");
		}

		if ((y1 > y2 ? y1 - y2 : y2 - y1) != b.getLen() - 1 && (x1 > x2 ? x1 - x2 : x2 - x1) != b.getLen() - 1) {
			throw new Exception("Distance between coordinate is incorrect");

		}

		try {
			if (y1 == y2) { // horizont
				if (x1 < x2) {
					for (int a = 0; a < b.getLen(); a++) {
						if (field[y1][x1 + a] != 0)
							throw new Exception("This space is alerady occupied by another ship!");
					}

					for (int a = 0; a < b.getLen(); a++) {
						field[y1][x1 + a] = b.getType();
					}
				}

				else {
					for (int a = 0; a < b.getLen(); a++) {
						if (field[y1][x1 - a] != 0)
							throw new Exception("This space is alerady occupied by another ship!");
					}

					for (int a = 0; a < b.getLen(); a++) {
						field[y1][x1 - a] = b.getType();
					}
				}
			}

			else if (x1 == x2) { // horizontal
				if (y1 < y2) {
					for (int a = 0; a < b.getLen(); a++) {
						if (field[y1 + a][x1] != 0)
							throw new Exception("This space is alerady occupied by another ship!");
					}

					for (int a = 0; a < b.getLen(); a++) {
						field[y1 + a][x1] = b.getType();
					}
				}

				else {
					for (int a = 0; a < b.getLen(); a++) {
						if (field[y1 - a][x1] != 0)
							throw new Exception("This space is alerady occupied by another ship!");
					}

					for (int a = 0; a < b.getLen(); a++) {
						field[y1 - a][x1] = b.getType();
					}
				}
			}

			else {
				throw new Exception("Invalid Coordinates XD!");
			}

			return true;

		} catch (Exception e) {
			// throw new Exception("Error placing ship");
			throw e;
		}
	}

	private boolean placeShip(boat b, Scanner scan) {
		while (true) {
			System.out.println("Place your " + b.getHP() + " hp ship!");

			System.out.println("First Coordinate");
			String p1 = scan.nextLine();

			System.out.println("Second Coordinate");
			String p2 = scan.nextLine();

			try {
				if (placeShipLogic(p1, p2, b))
					return true;
			} catch (Exception e) {
				System.out.println(e.toString());
				System.out.println("Try again!");
			}
		}
	}

	public player() {
		this.field = new int[10][10];

		// initialize field with 0
		for (int[] y : field) {
			for (int x : y) {
				x = 0;
			}
		}

		System.out.println("Enter Coordinates to place your ships!");
		Scanner scan = new Scanner(System.in);
		
		boats.add(new boat(2,2));
		boats.add(new boat(3,3));
		boats.add(new boat(3,4));
		boats.add(new boat(4,5));
		boats.add(new boat(5,6));
		
		for (int x = 0; x < boats.size(); x++) {
			placeShip(boats.get(x), scan);
			System.out.println("imadick " + x);
		}
	}
}
