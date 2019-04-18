public class battle {

	public static void main(String[] args) {
		ai p2 = new ai();
		ai p1 = new ai();
		
		
		while(true) {
			if(p2.hasLost()) break;
			p2.attack(p1);
		}
		System.out.println("Winner winner chicken dinner");
		
	}

}
