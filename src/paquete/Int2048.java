package paquete;

public class Int2048 {
	private int num,power;
	private boolean unlocked;
	
	public Int2048(int power){
		num=(int) Math.pow(2, power);
		unlocked=true;
	}
	private Int2048(int num, boolean bool){
		num=num;
		unlocked=bool;
		
	}
	
	
	
	
	
}
