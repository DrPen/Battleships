package battleships;

public class boat {
	private int len;
	private int HP;
	
	public int getLen(){
		return this.len;
	}
	
	public int getHP() {
		return this.HP;	
	}
	
	public boat(int len) {
		this.len = len;
		this.HP = len;
	}
}
