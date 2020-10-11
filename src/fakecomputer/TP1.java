package fakecomputer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TP1 {

	public static Computer comp;
	public static Memories ram;
	public static Instructions instruc;
	Scanner sc;
	boolean showOps = false, invert = false, dinvert = false, loop = true; 
	
	void run() {
		ram = new Memories();
		instruc = new Instructions();
		comp = new Computer();
		
		showOps = askShow();
		ram.buildRam();
		do{
			menu();
			ram.printResult();
			if(showOps) {
				ram.printMemory();
			}
		}while(loop != false);
	}
	
	void menu() {
		System.out.println("  |->            Select an operation           <-|");
		System.out.println("1 | Random (+ / -/ save)                         |");
		System.out.println("2 | add                                       a+b|");
		System.out.println("3 | subtract                                  a-b|");
		System.out.println("4 | Multiply                                  a.b|");
		System.out.println("5 | Division 	                              a/b|");
		System.out.println("6 | Exponentiation                            a^b|");
		System.out.println("7 | Root                                      aVb|");
		System.out.println("8 | First degree function                    ax+b|");
		System.out.println("9 | Distributive                           a(b+c)|");
		System.out.println("10| Distributive                           a(b-c)|");
		System.out.println("11| Delta                             -b^2 -4.a.c|");
		System.out.println("12| Bhaskara                 (-b +- VDelta) / 2.a|");
		System.out.println("13| Second degree function         a.x^2 + bx + c|");
		System.out.println("14| Logarithmic                             log b|");
		System.out.println("15| Pythagoras (hypotenuse)           c^2=a^2+b^2|");
		System.out.println("16| Pythagoras (height or base)       a^2=c^2-b^2|");
		System.out.println("17| Arithmetic average                (a+b+c) / n|");
		System.out.println("18| Square area                               s^2|");
		System.out.println("19| Rectangle area                            b.h|");
		System.out.println("20| Triangle area                         (b.h)/2|");
		System.out.println("21| Trapezoid area            ( (b1+b2) / 2 ) . h|");
		System.out.println("22| Cube volume                               s^3|");
		System.out.println("23| Parallelepiped volume                   l.w.h|");
		System.out.println("24| Pyramid volume(square base)           (s^2)/3|");
		System.out.println("25| Pyramid volume(rectangular base)    (l.w.h)/3|");
		System.out.println("26| Prisma (triangular base)            (l.b.h)/2|");
		System.out.println("27| Percent                                 a % b|");
		System.out.println("28| (AP)Find term               an = a1 + (n-1).r|");
		System.out.println("29| (AP)Add terms            sn = (n (a1+an)) / 2|");
		System.out.println("30| (GP)Find term               an = ak . q^(n-k)|");
		System.out.println("31| (GP)Add terms       sn = (a1(q^n - 1)) / q -1|");
		System.out.println("32| (Fibonacci)Find term         fn = fn-1 + fn-2|");
		System.out.println("33| Days to hours/minutes/seconds                |");
		System.out.println("34| Ceulsius to fahrenheit/kelvin                |");
		System.out.println("35| Average speed                      vm = Ds/Dt|");
		System.out.println();
		
		
		System.out.print("->");
		
		sc = new Scanner(System.in);
		int op = sc.nextInt();
		
		switch(op){
		
			case 1:
				instruc.randomI();
				comp.run(ram, instruc, showOps);
				break;
				
			case 2:
				System.out.print("A: ");
				sc = new Scanner(System.in);
				int aa = sc.nextInt();
				
				System.out.print("B: ");
				sc = new Scanner(System.in);
				int ab = sc.nextInt();
				
				add(aa,ab);
				break;
			
			case 3:
				System.out.print("A: ");
				sc = new Scanner(System.in);
				int sa = sc.nextInt();
				
				System.out.print("B: ");
				sc = new Scanner(System.in);
				int sb = sc.nextInt();
				
				subtract(sa,sb);
				break;
				
			case 4:
				System.out.print("Multiplicand: ");
				sc = new Scanner(System.in);
				int multiplicand = sc.nextInt();
				
				System.out.print("Multiplier: ");
				int multiplier = sc.nextInt();
				sc = new Scanner(System.in);
				
				multiply(multiplier, multiplicand);
				
				break;
				
			case 5:
				System.out.print("Dividend: ");
				sc = new Scanner(System.in);
				int dividend = sc.nextInt();
				
				System.out.print("Divisor: ");
				int divisor = sc.nextInt();
				sc = new Scanner(System.in);
				
				divide(dividend, divisor);
				break;
				
			case 6:
				System.out.print("Base: ");
				sc = new Scanner(System.in);
				int base = sc.nextInt();
				
				System.out.print("Exponent: ");
				int exponent = sc.nextInt();
				sc = new Scanner(System.in);
				
				 
				exponentiation(base, exponent);
				break;
				
			case 7:
				System.out.print("Radicand: ");
				sc = new Scanner(System.in);
				int radicand = sc.nextInt();
				
				System.out.print("Index: ");
				sc = new Scanner(System.in);
				int index = sc.nextInt();
				
				root(radicand, index);
				break;
			
			case 8:
				System.out.print("A: ");
				sc = new Scanner(System.in);
				int e1a = sc.nextInt();
				
				System.out.print("B: ");
				sc = new Scanner(System.in);
				int e1b = sc.nextInt();
				
				multiply(e1b,-1);
				comp.run(ram, instruc, showOps);
				
				e1b = ram.RAM[1];
				System.out.println("e1b: " + e1b);
				divide(e1b,e1a);
				
				@SuppressWarnings("unused") int e1x = ram.RAM[1];
				
				break;
			case 9:
				System.out.print("A: ");
				sc = new Scanner(System.in);
				int d1a = sc.nextInt();
				
				System.out.print("B: ");
				sc = new Scanner(System.in);
				int d1b = sc.nextInt();
				
				System.out.print("C: ");
				sc = new Scanner(System.in);
				int d1c = sc.nextInt();
				
				multiply(d1a,d1c);
				saveValue(ram.RAM[1], 2);
				multiply(d1a,d1b);
				add( ram.RAM[1] , ram.RAM[2]);
				break;
		
			case 10:
				System.out.print("A: ");
				sc = new Scanner(System.in);
				int d2a = sc.nextInt();
				
				System.out.print("B: ");
				sc = new Scanner(System.in);
				int d2b = sc.nextInt();
				
				System.out.print("C: ");
				sc = new Scanner(System.in);
				int d2c = sc.nextInt();
				
				multiply(d2a,d2c);
				saveValue(ram.RAM[1], 2);
				multiply(d2a,d2b);
				subtract( ram.RAM[1] , ram.RAM[2]);
				break;
			
			case 11:
				System.out.print("A: ");
				sc = new Scanner(System.in);
				int da = sc.nextInt();
				
				System.out.print("B: ");
				sc = new Scanner(System.in);
				int db = sc.nextInt();
				
				System.out.print("C: ");
				sc = new Scanner(System.in);
				int dc = sc.nextInt();
				
				delta( da, db, dc);
				break;
			
			case 12:
				System.out.print("A: ");
				sc = new Scanner(System.in);
				int ba = sc.nextInt();
				
				System.out.print("B: ");
				sc = new Scanner(System.in);
				int bb = sc.nextInt();
				
				System.out.print("Delta: ");
				sc = new Scanner(System.in);
				int delta = sc.nextInt();
				
				bhaskara( ba, bb, delta);
				
				break;
				
			case 13:
				System.out.print("A: ");
				sc = new Scanner(System.in);
				int e2a = sc.nextInt();
				
				System.out.print("B: ");
				sc = new Scanner(System.in);
				int e2b = sc.nextInt();
				
				System.out.print("C: ");
				sc = new Scanner(System.in);
				int e2c = sc.nextInt();
				
				delta( e2a, e2b, e2c);
				bhaskara( e2a, e2b, ram.RAM[1]);
				break;
				
			case 14:
				System.out.print("base: ");
				sc = new Scanner(System.in);
				int lbase = sc.nextInt();
				
				System.out.print("Argument: ");
				sc = new Scanner(System.in);
				int argument = sc.nextInt();
				
				saveValue(argument,3);
				saveValue(0,20); //counter
				do
				{
					exponentiation(lbase, ram.RAM[20]);
					saveValue(ram.RAM[1],4);
					
					add(ram.RAM[20],1);			//counter++
					saveValue(ram.RAM[1],20);
				}
				while(ram.RAM[4] < ram.RAM[3]);
				
				subtract(ram.RAM[20],1);
				
				break;
				
			case 15:
				System.out.print("Base: ");
				sc = new Scanner(System.in);
				int pbase = sc.nextInt();
				
				System.out.print("Height: ");
				sc = new Scanner(System.in);
				int phei = sc.nextInt();
				
				exponentiation(pbase, 2);
				saveValue(ram.RAM[1],3);
				exponentiation(phei, 2);
				saveValue(ram.RAM[1],4);
				add(ram.RAM[3],ram.RAM[4]);
				root(ram.RAM[1],2);
				break;
				
			case 16:
				System.out.print("Hypotenuse: ");
				sc = new Scanner(System.in);
				int pHyp = sc.nextInt();
				
				System.out.print("Height or Base: ");
				sc = new Scanner(System.in);
				int phb = sc.nextInt();
				
				exponentiation(pHyp, 2);
				saveValue(ram.RAM[1],3);
				exponentiation(phb, 2);
				saveValue(ram.RAM[1],4);
				subtract(ram.RAM[3],ram.RAM[4]);
				root(ram.RAM[1],2);
				
				break;
				
			case 17:
				
				System.out.print("Values: ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
				String lines = null;
				try {
					lines = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}    
			        
			    String[] strs = lines.trim().split("\\s+");
			    
			    int[] values = new int [strs.length];     
			    for (int i = 0; i < strs.length; i++) {
			    	values[i] = Integer.parseInt(strs[i]);
			    	add(21,i);
					int indice1 = ram.RAM[1];
					saveValue(values[i], indice1);
			    }
				
			    subtract(strs.length,1);
			    int lengmenus = ram.RAM[1];
			    
				for(int i = 0; i < lengmenus ; i++) {
					add(22,i);
					int indice2 = ram.RAM[1];
					add(ram.RAM[21],ram.RAM[indice2]);
					saveValue(ram.RAM[1],21);
				}
				divide(ram.RAM[21],strs.length);
				
				break;
			
			case 18:
				System.out.print("S: ");
				sc = new Scanner(System.in);
				int s = sc.nextInt();	
				
				exponentiation(s,2);
				break;
				
			case 19:
				System.out.print("B: ");
				sc = new Scanner(System.in);
				int b2 = sc.nextInt();	
				
				System.out.print("H: ");
				sc = new Scanner(System.in);
				int h2 = sc.nextInt();	
				
				multiply(b2,h2);
				break;
			
			case 20:
				System.out.print("B: ");
				sc = new Scanner(System.in);
				int b3 = sc.nextInt();	
				
				System.out.print("H: ");
				sc = new Scanner(System.in);
				int h3 = sc.nextInt();	
				
				multiply(b3,h3);
				divide(ram.RAM[1],2);
				break;
				
			case 21:
				System.out.print("B1: ");
				sc = new Scanner(System.in);
				int b14 = sc.nextInt();	
				
				System.out.print("B2: ");
				sc = new Scanner(System.in);
				int b15 = sc.nextInt();	
				
				System.out.print("H: ");
				sc = new Scanner(System.in);
				int h4 = sc.nextInt();	
				
				add(b14,b15);
				divide(ram.RAM[1],2);
				multiply(ram.RAM[1],h4);
				break;
				
			case 22:
				System.out.print("S: ");
				sc = new Scanner(System.in);
				int s2 = sc.nextInt();	
				
				exponentiation(s2,3);
				break;
				
			case 23:
				System.out.print("L: ");
				sc = new Scanner(System.in);
				int l = sc.nextInt();	
				
				System.out.print("W: ");
				sc = new Scanner(System.in);
				int w = sc.nextInt();	
				
				System.out.print("H: ");
				sc = new Scanner(System.in);
				int h5 = sc.nextInt();		
				
				multiply(l,w);
				multiply(ram.RAM[1],h5);
				break;
				
			case 24:
				System.out.print("S: ");
				sc = new Scanner(System.in);
				int s3 = sc.nextInt();	
					
				System.out.print("H: ");
				sc = new Scanner(System.in);
				int h6 = sc.nextInt();
				exponentiation(s3,2);
				multiply(ram.RAM[1],h6);
				divide(ram.RAM[1],3);
				break;
				
			case 25:
				System.out.print("L: ");
				sc = new Scanner(System.in);
				int l3 = sc.nextInt();	
				
				System.out.print("W: ");
				sc = new Scanner(System.in);
				int w3 = sc.nextInt();	
				
				System.out.print("H: ");
				sc = new Scanner(System.in);
				int h7 = sc.nextInt();
				multiply(l3,w3);
				multiply(ram.RAM[1],h7);
				divide(ram.RAM[1],3);
				break;
			
			case 26:
				System.out.print("L: ");
				sc = new Scanner(System.in);
				int l4 = sc.nextInt();	
				
				System.out.print("B: ");
				sc = new Scanner(System.in);
				int b4 = sc.nextInt();	
				
				System.out.print("H: ");
				sc = new Scanner(System.in);
				int h8 = sc.nextInt();
				
				multiply(l4,b4);
				multiply(ram.RAM[1],h8);
				divide(ram.RAM[1],2);
				break;
				
			case 27:
				System.out.print("Total value: ");
				sc = new Scanner(System.in);
				int tv = sc.nextInt();	
				
				System.out.print("Part value: ");
				sc = new Scanner(System.in);
				int pv = sc.nextInt();
				
				
				multiply(pv,100);
				divide(ram.RAM[1],tv);
				break;
				
			case 28:
				System.out.print("A: ");
				sc = new Scanner(System.in);
				int paa = sc.nextInt();	
				
				System.out.print("R: ");
				sc = new Scanner(System.in);
				int par = sc.nextInt();
				
				System.out.print("N: ");
				sc = new Scanner(System.in);
				int pan = sc.nextInt();
				
				pa(paa,par,pan);
				break;
				
			case 29:
				System.out.print("A1: ");
				sc = new Scanner(System.in);
				int spaa = sc.nextInt();	
				
				System.out.print("R: ");
				sc = new Scanner(System.in);
				int spar = sc.nextInt();
				
				System.out.print("N: ");
				sc = new Scanner(System.in);
				int span = sc.nextInt();
				
				pa(spaa,spar,span);
				add(spaa,ram.RAM[1]);
				multiply(ram.RAM[1],span);
				divide(ram.RAM[1],2);
				break;
				
			case 30:
				System.out.print("A: ");
				sc = new Scanner(System.in);
				int pga = sc.nextInt();	
				
				System.out.print("K: ");
				sc = new Scanner(System.in);
				int pgk = sc.nextInt();
				
				System.out.print("Q: ");
				sc = new Scanner(System.in);
				int pgq = sc.nextInt();
				
				System.out.print("N: ");
				sc = new Scanner(System.in);
				int pgn = sc.nextInt();
				
				subtract(pgn,pgk);
				exponentiation(pgq,ram.RAM[1]);
				multiply(pga,ram.RAM[1]);
				break;
				
			case 31:
				System.out.print("A: ");
				sc = new Scanner(System.in);
				int spga = sc.nextInt();	
				
				System.out.print("Q: ");
				sc = new Scanner(System.in);
				int spgq = sc.nextInt();
				
				System.out.print("N: ");
				sc = new Scanner(System.in);
				int spgn = sc.nextInt();
				
				exponentiation(spgq,spgn);
				subtract(ram.RAM[1],1);
				multiply(ram.RAM[1],spga);
				saveValue(ram.RAM[1],3);
				subtract(spgq,1);
				divide(ram.RAM[3],ram.RAM[1]);
				break;
				
			case 32:
				System.out.print("N: ");
				sc = new Scanner(System.in);
				int n = sc.nextInt();
				
				for(int i=0; i < n; i++) {					
					
					saveValue(ram.RAM[12],11);
					saveValue(ram.RAM[1],12);
					
					if(i == 0) {
						saveValue(0,11);
						saveValue(0,12);
					}else if(i == 1) {
						saveValue(1,11);
					}
					add(ram.RAM[12],ram.RAM[11]);
				}
				break;
				
			case 33:
				System.out.print("Days: ");
				sc = new Scanner(System.in);
				int days = sc.nextInt();
				
				multiply(days,24);
				System.out.print("Hours:");
				ram.printResult();
				multiply(ram.RAM[1],60);
				System.out.print("Minutes:");
				ram.printResult();
				multiply(ram.RAM[1],60);
				System.out.print("Seconds:");
				break;
		
			case 34:
				System.out.print("Celsius: ");
				sc = new Scanner(System.in);
				int celsius = sc.nextInt();
				
				multiply(celsius,9);
				divide(ram.RAM[1],5);
				add(ram.RAM[1],32);
				System.out.print("Fahrenheit:");
				ram.printResult();
				add(celsius,273);
				System.out.print("Kelvin:");
				break;
				
			case 35:
				System.out.print("Distance(km): ");
				sc = new Scanner(System.in);
				int vms = sc.nextInt();
				
				System.out.print("Time(h): ");
				sc = new Scanner(System.in);
				int vmt = sc.nextInt();
				
				System.out.print("Vm(Km/h): ");
				divide(vms,vmt);
		}
		
	}

	boolean askShow() {
		System.out.println("Show entire operations and RAM? (true/false)");
		System.out.print("->");
		
		sc = new Scanner(System.in);
		boolean showOps = sc.nextBoolean();
		
		return showOps;
	}
	
	void add(int value1, int value2) {
		instruc.add(value1, value2);
		comp.run(ram, instruc, showOps);
	}
	
	void subtract(int value1, int value2) {
		instruc.subtract(value1, value2);
		comp.run(ram, instruc, showOps);
	}
	
	void multiply(int multiplier,int multiplicand) {
		if(multiplier < 0) {
			multiplier = invert(multiplier);
			invert = true;
		}
		if(multiplicand < 0) {
			multiplicand = invert(multiplicand);
			if(invert) {
				invert = false;
			}else
				invert = true;
		}
		
		instruc.multiplyI(multiplicand, multiplier);
		comp.run(ram, instruc, showOps);
		
		if(invert){
			ram.RAM[1] = invert(ram.RAM[1]);
		}
	}
	
	void divide(int dividend, int divisor) {
		
		if(dividend < 0) {
			multiply(dividend,-1);
			dividend = ram.RAM[1];
			dinvert = true;
		}
		if(divisor < 0) {
			multiply(divisor,-1);
			divisor = ram.RAM[1];
			if(dinvert) {
				dinvert = false;
			}else
				dinvert = true;
		}
		saveValue(dividend,2);
		
		saveValue(0,20); //counter
		do{
			multiply(divisor, ram.RAM[20]);
			saveValue(ram.RAM[1],4);
			
			add(ram.RAM[20],1);
			saveValue(ram.RAM[1],20);
		}while(ram.RAM[4] < ram.RAM[2]);
		
		
		if(ram.RAM[4] == ram.RAM[2]) {
			subtract(ram.RAM[20],1);
			if(dinvert) {
				multiply(ram.RAM[1],-1);
			}
		}else {
			subtract(ram.RAM[20],2);
			if(dinvert) {
				multiply(ram.RAM[1],-1);
			}
		}
	}

	void exponentiation(int base, int exponent) {
		saveValue(base,4);
		if(exponent == 0) {
			saveValue(1,1);
		}else if(exponent == 1){
			saveValue(base,1);
		}else{
			for(int i=0; i < exponent - 1; i++) {
			multiply(base, ram.RAM[4]);
			base = ram.RAM[1];
			}
		}
	}

	void root(int radicand, int index) {
		saveValue(radicand,2);

		saveValue(1,20); //counter
		do {
			
			exponentiation(ram.RAM[20], index);
			saveValue(ram.RAM[1],4);
			
			add(ram.RAM[20],1);			//counter++
			saveValue(ram.RAM[1],20);
		}while(ram.RAM[4] < ram.RAM[2]);
		
		subtract(ram.RAM[20],1);
	}
	
	void saveValue(int value, int position) {
		instruc.saveValueI(value, position);
		comp.run(ram,instruc,showOps);
	}
	
	void delta(int a, int b, int c) {
		exponentiation(b, 2);
		saveValue(ram.RAM[1], 2);
		multiply(4, a);
		saveValue(ram.RAM[1],3);
		multiply(ram.RAM[3], c);
		subtract(ram.RAM[2],ram.RAM[1]);
	}
	
	void bhaskara(int a, int b, int delta) {
		multiply(b,-1);
		saveValue(ram.RAM[1],3);				// [3] = -b 
		
		root(delta,2);
		saveValue(ram.RAM[1],7);				// [7] = raiz de delta 		
		
		multiply(a,2);
		saveValue(ram.RAM[1],5);				// [5] = 2*a	
		
		add(ram.RAM[3],ram.RAM[7]);
		saveValue(ram.RAM[1],6);				// [6] = delta + (-b)		 
		
		divide(ram.RAM[6],ram.RAM[5]);
		ram.printResult();						//show x1
		
		subtract(ram.RAM[3],ram.RAM[7]);
		saveValue(ram.RAM[1],6);				// [6] = delta - (-b)		
		
		divide(ram.RAM[6],ram.RAM[5]);			//show x2
	}

	void pa(int a,int r,int n ) {
		subtract(n,1);
		multiply(ram.RAM[1],r);
		add(a,ram.RAM[1]);
	}

	int invert(int n) {
		if(n < 0) {
			for(int i=0; i < Integer.MAX_VALUE; i++) {
				subtract(0,i);
				if(ram.RAM[1] == n) {
					saveValue(i,1);
					break;
				}
			}
		}else {
			subtract(0,n);
		}
		return ram.RAM[1];
	}
}
