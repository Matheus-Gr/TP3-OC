package fakecomputer;

import java.util.Scanner;

public class TP3 {
	public static Computer comp;
	public static Memories mem;
	public static Instructions instruc;
	boolean showOps = false;
	Scanner sc;
	static boolean stop = false;
	
	void run() {
		mem = new Memories();
		instruc = new Instructions();
		comp = new Computer();
		
		showOps = askShow();
		
		mem.buildHD();
		mem.buildNullCache(Memories.ramSize2, mem.RAM2);
		mem.buildNullCache(Memories.cache1Size, mem.cache1);
		mem.buildNullCache(Memories.cache2Size, mem.cache2);
		mem.buildNullCache(Memories.cache3Size, mem.cache3);
		
		menu();
		
		mem.saveAllrefreshed();
		
		if(showOps) {
			mem.printMemory2(mem.cache1, Memories.cache1Size, "Cache 1");
			mem.printMemory2(mem.cache2, Memories.cache2Size, "Cache 2");
			mem.printMemory2(mem.cache3, Memories.cache3Size, "Cache 3");
			mem.printMemory2(mem.RAM2, Memories.ramSize2, "RAM");
			mem.printHD();
		}
		System.out.println("FINAL OVER");
		comp.printTotalResults();
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
			comp.run3(mem, instruc, showOps,1);
			break;
			
		case 2:
			instruc.setIGenerator();
			comp.run3(mem, instruc, showOps,1);
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
