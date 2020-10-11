package fakecomputer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;


public class Instructions {
	//TP1
	Random r;
	int iSize = 10;
	int[][] iMemory = null;
	
	//TP2
	static int iSize2 = 10001;
	Inst[] iMemory2;
	
	void randomI(){
		iMemory = new int[iSize][4];
		r = new Random();
		
		for(int i=0; i < iSize - 1; i++) {
			iMemory[i][0] = r.nextInt(3);
			iMemory[i][1] = r.nextInt(Memories.ramSize);
			iMemory[i][2] = r.nextInt(Memories.ramSize);
			iMemory[i][3] = r.nextInt(Memories.ramSize);
		}
		iMemory[iSize - 1][0] = -1;
		iMemory[iSize - 1][1] = -1;
		iMemory[iSize - 1][2] = -1;
		iMemory[iSize - 1][3] = -1;
	}
	
	void multiplyI(int multiplicand, int multiplier){		
		iMemory = new int[multiplier + 3][4];
			
		//saving multiplicand in RAM
		iMemory[0][0] = 0; //save in RAM
		iMemory[0][1] = multiplicand;
		iMemory[0][2] = 0; //position in RAM
		iMemory[0][3] = -1;
		
		iMemory[1][0] = 0; //save in RAM
		iMemory[1][1] = 0; //put 0 in position 1 of RAM
		iMemory[1][2] = 1; //position in RAM
		iMemory[1][3] = -1;
		
		for(int i=0; i < multiplier; i++) {
			iMemory[i+2][0] = 1; //adding up
			iMemory[i+2][1] = 0; //picking up content in RAM's position 0
			iMemory[i+2][2] = 1; //picking up content in RAM's position 1
			iMemory[i+2][3] = 1; //putting sum content in RAM's position 1 
		}
		
		iMemory[multiplier + 2][0] = -1;
		iMemory[multiplier + 2][1] = -1;
		iMemory[multiplier + 2][2] = -1;
		iMemory[multiplier + 2][3] = -1;
	}

	void saveValueI(int value, int position) {
		iMemory = new int[2][4];
		
		iMemory[0][0] = 0; //save in RAM
		iMemory[0][1] = value;
		iMemory[0][2] = position; //position in RAM
		iMemory[0][3] = -1;
		
		iMemory[1][0] = -1;
		iMemory[1][1] = -1;
		iMemory[1][2] = -1;
		iMemory[1][3] = -1;
	}
	
	void add(int value1, int value2) {
		iMemory = new int[4][4];
		
		iMemory[0][0] = 0; //save in RAM
		iMemory[0][1] = value1;
		iMemory[0][2] = 0; //position in RAM
		iMemory[0][3] = -1;
		
		iMemory[1][0] = 0; //save in RAM
		iMemory[1][1] = value2;
		iMemory[1][2] = 1; //position in RAM
		iMemory[1][3] = -1;
		
		iMemory[2][0] = 1; 
		iMemory[2][1] = 0;
		iMemory[2][2] = 1; 
		iMemory[2][3] = 1;
		
		iMemory[3][0] = -1;
		iMemory[3][1] = -1;
		iMemory[3][2] = -1;
		iMemory[3][3] = -1;
	}
	
	void subtract(int value1, int value2) {
		iMemory = new int[4][4];
		
		iMemory[0][0] = 0; //save in RAM
		iMemory[0][1] = value1;
		iMemory[0][2] = 0; //position in RAM
		iMemory[0][3] = -1;
		
		iMemory[1][0] = 0; //save in RAM
		iMemory[1][1] = value2;
		iMemory[1][2] = 1; //position in RAM
		iMemory[1][3] = -1;
		
		iMemory[2][0] = 2; 
		iMemory[2][1] = 0;
		iMemory[2][2] = 1; 
		iMemory[2][3] = 1;
		
		iMemory[3][0] = -1;
		iMemory[3][1] = -1;
		iMemory[3][2] = -1;
		iMemory[3][3] = -1;
	}

	//TP2
	void randomI2() {
		iMemory2 = new Inst[iSize2];
		
		Inst aux;
		
		r = new Random();
		for (int i = 0 ; i < iSize2 - 1; i++){
			aux = new Inst();
			aux.setOpcode(r.nextInt(3));
			
			Address add1 = new Address();
			add1.setAddBlock(r.nextInt(Memories.HDSize));
			add1.setAddWords(r.nextInt(Memories.qWordBlock));
			aux.setAdd1(add1);
			
			Address add2 = new Address();
			add2.setAddBlock(r.nextInt(Memories.HDSize));
			add2.setAddWords(r.nextInt(Memories.qWordBlock));
			aux.setAdd2(add2);
			
			Address add3 = new Address();
			add3.setAddBlock(r.nextInt(Memories.HDSize));
			add3.setAddWords(r.nextInt(Memories.qWordBlock));
			aux.setAdd3(add3);
			
			iMemory2[i] = aux;
		}
		aux = new Inst();
		aux.setOpcode(-1);
		
		iMemory2[iSize2 - 1] = aux;
	}

	void interuptions(int size) {
		iMemory2 = new Inst[size];
		
		Inst aux;
		
		r = new Random();
		for (int i = 0 ; i < size - 1; i++){
			aux = new Inst();
			aux.setOpcode(r.nextInt(3));
			
			Address add1 = new Address();
			add1.setAddBlock(r.nextInt(Memories.HDSize));
			add1.setAddWords(r.nextInt(Memories.qWordBlock));
			aux.setAdd1(add1);
			
			Address add2 = new Address();
			add2.setAddBlock(r.nextInt(Memories.HDSize));
			add2.setAddWords(r.nextInt(Memories.qWordBlock));
			aux.setAdd2(add2);
			
			Address add3 = new Address();
			add3.setAddBlock(r.nextInt(Memories.HDSize));
			add3.setAddWords(r.nextInt(Memories.qWordBlock));
			aux.setAdd3(add3);
			
			iMemory2[i] = aux;
		}
		aux = new Inst();
		aux.setOpcode(-1);
		
		iMemory2[size - 1] = aux;
	}
	void setIGenerator() {
		
		iMemory2= new Inst[iSize2];
		
		try{
			File f = new File ("inst.txt");
			FileReader fis = new FileReader(f);
			BufferedReader br = new BufferedReader(fis);
			
			String line = null;
			Inst aInstruction = null;
			int index = 0;
			
			while ( (line = br.readLine()) != null ){
				
				String [] words = line.split(":");
				aInstruction = new Inst();
				
				aInstruction.setOpcode(Integer.parseInt(words[0]));
				
				Address e1 = new Address();
				int e1_AddBlock = Integer.parseInt(words[1]);
				int e1_AddWord = Integer.parseInt(words[2]);
				e1_AddWord = e1_AddWord%4; //mod 4
				e1.setAddBlock(e1_AddBlock);
				e1.setAddWords(e1_AddWord);
				aInstruction.setAdd1(e1);
				
				Address e2 = new Address();
				int e2_AddBlock = Integer.parseInt(words[3]);
				int e2_AddWord = Integer.parseInt(words[4]);
				e2_AddWord = e2_AddWord%4; //mod 4
				e2.setAddBlock(e2_AddBlock);
				e2.setAddWords(e2_AddWord);
				aInstruction.setAdd2(e2);
				
				Address e3 = new Address();
				int e3_AddBlock = Integer.parseInt(words[5]);
				int e3_AddWord = Integer.parseInt(words[6]);
				e3_AddWord = e3_AddWord%4; //mod 4
				e3.setAddBlock(e3_AddBlock);
				e3.setAddWords(e3_AddWord);
				aInstruction.setAdd3(e3);
				
				iMemory2[index] = aInstruction;
				index++;
			}
			fis.close();
			br.close();
			
			aInstruction = new Inst();
			aInstruction.setOpcode(-1);
						
			iMemory2[iSize2-1] = aInstruction;
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
}
