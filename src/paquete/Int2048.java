package paquete;

public class Int2048 {
	public int num,power;
	private boolean unlocked;
	
	public Int2048(int power){
		this.num = (int)Math.pow(2, power);
		this.unlocked = true;
	}
	
	private Int2048(int num, boolean bool){
		this.num = num;
		this.unlocked = bool;
		
	}

	public boolean isUnlocked() {
		return unlocked;
	}

	public void setUnlocked(boolean unlocked) {
		this.unlocked = unlocked;
	}
	public boolean equals(Int2048 n){
		if(n == null )
			return false;
		if(this.num == n.num){
			return true;
		}
		return false;
	}
	
	
	public void multiply(){
	    this.num *= 2;
	    	
	}
	
	@Override
	public String toString(){
		return this.num + "";
	}
	
}
