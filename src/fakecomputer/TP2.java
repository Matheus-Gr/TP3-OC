package fakecomputer;

import java.util.Scanner;

public class TP2 {
	public static Computer comp;
	public static Memories ram;
	public static Instructions instruc;
	public static Address address;
	public static Sorting sortt;
	Scanner sc;
	boolean showOps = false, invert = false, dinvert = false, loop = true; 
	
	void run() {
		ram = new Memories();
		instruc = new Instructions();
		comp = new Computer();
		sortt = new Sorting();
		
		showOps = askShow(); 
		
		ram.buildRam2();
		ram.buildNullCache(Memories.cache1Size, ram.cache1);
		ram.buildNullCache(Memories.cache2Size, ram.cache2);
		ram.buildNullCache(Memories.cache3Size, ram.cache3);
		
		menu();
		
		if(showOps) {
			ram.printMemory2(ram.cache1, Memories.cache1Size, "Cache 1");
			ram.printMemory2(ram.cache2, Memories.cache2Size, "Cache 2");
			ram.printMemory2(ram.cache3, Memories.cache3Size, "Cache 3");
			ram.printMemory2(ram.RAM2, Memories.ramSize2, "RAM");
		}
	}
	

	private void menu() {
		System.out.println("  |->            Select an operation           <-|");
		System.out.println("1 | Random (+ / -/ save)                         |");
		System.out.println("2 | Generated Program                            |");
		System.out.println();
		
		System.out.print("->");
		
		sc = new Scanner(System.in);
		int op = sc.nextInt();
		
		switch(op){
		
		case 1:
			instruc.randomI2();
			comp.run2(ram, instruc, showOps);
			break;
			
		case 2:
			instruc.setIGenerator();
			comp.run2(ram, instruc, showOps);
			break;
		}
	}


	boolean askShow() {
		System.out.println("Show entire operations and RAM? (y/n)");
		System.out.print("->");
		
		boolean showOps;
		sc = new Scanner(System.in);
		char c = sc.next().charAt(0);
		if(c == 'y') {
			showOps = true;
		}else showOps = false;
		
		return showOps;
	}
	
}
