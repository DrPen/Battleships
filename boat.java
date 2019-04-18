public class boat {
	private int len;
	private int HP;
	private int ID;
	
	public int getLen(){
		return this.len;
	}
	
	public int getHP() {
		return this.HP;
	}
	
	public boolean hasHP() {
		return this.HP != 0;
	}
	
	public int getID() {
		return this.ID;
	}
	
	public void damage() {
		this.HP--;
	}
	
	public boat(int x, int ID) {
		this.len = x;
		this.HP = x;
		this.ID = ID;
	}
}
