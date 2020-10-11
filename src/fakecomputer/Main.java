package fakecomputer;

public class Main {
	
	@SuppressWarnings("unused")
	private static TP1 tp1; 
	@SuppressWarnings("unused")
	private static TP2 tp2; 
	@SuppressWarnings("unused")
	private static TP3 tp3; 
	
	public static void main(String[] args)  {
		tp1 = new TP1();
		tp2 = new TP2();
		tp3 = new TP3();
		
		//tp1.run();
		//tp2.run();
		tp3.run();
	}
	
}
