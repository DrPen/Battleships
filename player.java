import java.util.Vector;
import java.util.Scanner;

public class player {
	protected int playerField[][];
	protected int enemyField[][];

	protected Vector<boat> boats = new Vector<boat>();
	protected String playerName;
	private boolean firstPlacement;

	protected String nums2Coords(int xCoord, int yCoord) {
		String x = Integer.toString(xCoord + 1);
		String y = String.valueOf((char) (yCoord + 97));

		return y.toUpperCase() + x;
	}

	protected int[][] coords2Nums(String s) throws Exception {
		if (s.length() < 2 || s.length() > 3) {
			throw new Exception("Invalid Coordinates: Invalid amount of arguments");
		}

		int coords[][] = new int[1][2];

		try {
			coords[0][0] = Integer.parseInt(s.substring(1, s.length())) - 1;
			coords[0][1] = s.substring(0, 1).toLowerCase().charAt(0) - 97;
		} catch (Exception e) {
			throw new Exception("Invalid Coordinates");
		}

		if (coords[0][1] < 0 || coords[0][1] > 9 || coords[0][1] < 0 || coords[0][1] > 9) {
			throw new Exception("Invalid Coordinates: Field Parameters are is A1 to J10");
		}

		return coords;
	}

	protected boolean placeShipLogic(String x, String y, boat b) throws Exception {
		int tmp[][] = new int[1][2];

		try {
			tmp = coords2Nums(x);
		} catch (Exception e) {
			throw new Exception(e.toString() + "Invalid X Coordinates");
		}

		int x1 = tmp[0][0];
		int y1 = tmp[0][1];

		try {
			tmp = coords2Nums(y);
		} catch (Exception e) {
			throw new Exception("Invalid Y Coordinates");
		}
		int x2 = tmp[0][0];
		int y2 = tmp[0][1];

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
						if (playerField[y1][x1 + a] != 0)
							throw new Exception("This space is alerady occupied by another ship!");
					}

					for (int a = 0; a < b.getLen(); a++) {
						playerField[y1][x1 + a] = b.getID();
					}
				}

				else {
					for (int a = 0; a < b.getLen(); a++) {
						if (playerField[y1][x1 - a] != 0)
							throw new Exception("This space is alerady occupied by another ship!");
					}

					for (int a = 0; a < b.getLen(); a++) {
						playerField[y1][x1 - a] = b.getID();
					}
				}
			}

			else if (x1 == x2) { // horizontal
				if (y1 < y2) {
					for (int a = 0; a < b.getLen(); a++) {
						if (playerField[y1 + a][x1] != 0)
							throw new Exception("This space is alerady occupied by another ship!");
					}

					for (int a = 0; a < b.getLen(); a++) {
						playerField[y1 + a][x1] = b.getID();
					}
				}

				else {
					for (int a = 0; a < b.getLen(); a++) {
						if (playerField[y1 - a][x1] != 0)
							throw new Exception("This space is alerady occupied by another ship!");
					}

					for (int a = 0; a < b.getLen(); a++) {
						playerField[y1 - a][x1] = b.getID();
					}
				}
			}

			else {
				throw new Exception("Invalid Coordinates XD!");
			}

			return true;

		} catch (Exception e) {
			throw e;
		}
	}


	public boolean placeShips() {
		Scanner scan = new Scanner(System.in);

		if (firstPlacement) {
			System.out.println("Enter Coordinates to place your ships!");
		} else {
			System.out.println("Your current Setup will be erased! Continue Y/N?");

			if (scan.nextLine().toLowerCase().contentEquals("y")) {
				for (int x = 0; x < playerField[0].length; x++) {
					for (int y = 0; y < playerField.length; y++) {
						playerField[x][y] = 0;
						enemyField[x][y] = 0;
					}
				}
			} else {
				return false;
			}
		}

		for (int x = 0; x < boats.size(); x++) {
			boat b = boats.get(x);

			while (true) {
				System.out.println("Place your " + b.getHP() + " hp ship!");

				System.out.println("First Coordinate");
				String p1 = scan.nextLine();

				System.out.println("Second Coordinate");
				String p2 = scan.nextLine();

				try {
					if (placeShipLogic(p1, p2, b))
						break;
				} catch (Exception e) {
					System.out.println(e.toString());
					System.out.println("Try again!");
				}
			}
		}
		drawNumField(this.playerField);
		this.firstPlacement = false;
		return true;
	}
	
	public String getName() {
		return this.playerName;
	}

	public boolean isHit(int x, int y) {
		if (playerField[y][x] > 2) {
			boats.elementAt(playerField[y][x]-3).damage();	// -3 since type starts with 3 in constructor
			return true;
		}
		return false;
	}
	
	public boolean hasLost() {
		for(boat b: this.boats) {
			if(b.hasHP()) {
				return false;
			}
		}
		return true;
	}

	public void attack(player p) {
		Scanner scan = new Scanner(System.in);

		int temp[][] = new int[1][2];
		while (true) {
			System.out.println("Fire Away! Enter your target coordinates!");

			try {
				temp = coords2Nums(scan.nextLine());
			} catch (Exception e) {
				System.out.println(e.toString());
				System.out.println("Try again!");
			}

			int x = temp[0][0];
			int y = temp[0][1];

			if (this.enemyField[y][x] == 1) {
				System.out.println(nums2Coords(x, y) + " has already been hit!");
				System.out.println("Pick a different Coordinate!");
			} else {
				if (p.isHit(x, y))
					this.enemyField[y][x] = 2;
				else
					this.enemyField[y][x] = 1;
				
				drawNumField(enemyField);
			}
		}

	}

	protected void drawNumField(int field[][]) {
		int num = 97;
		for (int x = 1; x < 11; x++) {
			System.out.print("\t" + x);
		}
		for (int[] y : field) {
			System.out.println();
			System.out.print(String.valueOf((char) num++).toUpperCase() + ":\t");
			for (int x : y) {
				System.out.print(x + "\t");
			}
		}
		System.out.println("");
	}

	// human player
	public player(String playerName) {
		this.playerName = playerName;
		this.firstPlacement = true;
		this.playerField = new int[10][10];
		this.enemyField = new int[10][10];

		for (int x = 0; x < playerField[0].length; x++) {
			for (int y = 0; y < playerField.length; y++) {
				playerField[x][y] = 0;
				enemyField[x][y] = 0;
			}
		}

		boats.add(new boat(2, 3));
		boats.add(new boat(3, 4));
		boats.add(new boat(3, 5));
		boats.add(new boat(4, 6));
		boats.add(new boat(5, 7));
	}
}
