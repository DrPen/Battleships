import java.util.Random;

public class ai extends player {

	// terribly inefficient >_>
	private boolean autoPlaceShips(boat boat, Random rand) {
		int x1Tmp, x2Tmp, y1Tmp, y2Tmp;
		while (true) {
			// 0 horizontal 1 vertical
			if (rand.nextInt(2) == 0) {
				y1Tmp = rand.nextInt((106 - 97) + 1) + 97;
				x1Tmp = rand.nextInt(10) + 1;

				// boat placement direction
				if (rand.nextInt(2) == 0)
					x2Tmp = x1Tmp + boat.getLen() - 1;
				else
					x2Tmp = x1Tmp - boat.getLen() - 1;

				String y = String.valueOf((char) y1Tmp);
				String x1 = Integer.toString(x1Tmp);
				String p1 = y + x1;

				String x2 = Integer.toString(x2Tmp);
				String p2 = y + x2;

				try {
					if (placeShipLogic(p1, p2, boat))
						return true;
				} catch (Exception e) {
				}

			} else {
				y1Tmp = rand.nextInt((106 - 97) + 1) + 97;
				x1Tmp = rand.nextInt(10) + 1;

				// boat placement direction
				if (rand.nextInt(2) == 0)
					y2Tmp = y1Tmp + boat.getLen() - 1;
				else
					y2Tmp = y1Tmp - boat.getLen() - 1;

				String y1 = String.valueOf((char) y1Tmp);
				String x = Integer.toString(x1Tmp);
				String p1 = y1 + x;

				String y2 = String.valueOf((char) y2Tmp);
				String p2 = y2 + x;

				try {
					if (placeShipLogic(p1, p2, boat))
						return true;
				} catch (Exception e) {
				}
			}
		}
	}

//	@Override
//	public int[][] attack() {
//		return field;
//		
//	}
	
	// PC
	public ai() {
		super("PC"); // explicity call superconstructor with parameter String

		this.playerField = new int[10][10];

		// initialize field with 0
		for (int[] y : playerField) {
			for (int x : y) {
				x = 0;
			}
		}

		Random rand = new Random();

		for (int x = 0; x < boats.size(); x++) {
			autoPlaceShips(boats.get(x), rand);
		}

		System.out.println("The Computer has placed its ships!");

		drawNumField(this.playerField);
	}
}
