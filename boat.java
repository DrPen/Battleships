package battleships;

public class boat {
	private int len;
	private int HP;
	private int type;
	
	public int getLen(){
		return this.len;
	}
	
	public int getHP() {
		return this.HP;	
	}
	
	public int getType() {
		return this.type;
	}
	
	public boat(int x, int type) {
		this.len = x;
		this.HP = x;
		this.type = type;
	}
}
