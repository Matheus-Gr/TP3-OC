package fakecomputer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

public class Memories {
	// TP1
	Random r;
	public static int ramSize = 40;
	int RAM[] = null;

	// TP2
	static int ramSize2 = 1000;
	public static int cache1Size = 8, cache2Size = 16, cache3Size = 32;
	public MemoryBlock[] RAM2 = new MemoryBlock[ramSize2];
	MemoryBlock[] cache1 = new MemoryBlock[cache1Size];
	MemoryBlock[] cache2 = new MemoryBlock[cache2Size];
	MemoryBlock[] cache3 = new MemoryBlock[cache3Size];
	public final static int qWordBlock = 4;
	public static Sorting sortt;
	int hitPoint = -1;

	// TP3
	static int HDSize = 10000;
	int totalRe =0;

	void buildRam() {
		r = new Random();
		RAM = new int[ramSize];
		for (int i = 0; i < ramSize; i++) {
			RAM[i] = r.nextInt(1000);
		}
	}

	void printMemory() {
		for (int i = 0; i < ramSize; i++) {
			System.out.println("RAM " + i + " igual a: " + RAM[i]);
		}
	}

	void printResult() {
		System.out.println("Result: " + RAM[1]);
	}

	// TP2
	void buildRam2() {
		r = new Random();
		for (int i = 0; i < ramSize2; i++) {
			MemoryBlock aux = new MemoryBlock();
			aux.setAddBlock(i);
			int[] words = new int[qWordBlock];
			for (int j = 0; j < qWordBlock; j++) {
				words[j] = r.nextInt(1000);
			}
			aux.setWords(words);
			RAM2[i] = aux;
		}
	}

	// TP3
	void buildHD() {
		r = new Random();
		String fileName = "HD.bin";
		try {
			FileOutputStream fileOs = new FileOutputStream(fileName);
			ObjectOutputStream os = new ObjectOutputStream(fileOs);
			for (int i = 0; i < HDSize; i++) {
				os.writeInt(i);
				for (int j = 0; j < qWordBlock; j++) {
					os.writeInt(r.nextInt(1000));
				}
				os.writeBoolean(false);
				os.writeInt(0);
				os.writeInt(0);
				os.writeInt(0);
			}
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void printHD() {
		System.out.println("_________________________[ HD ]_________________________");
		String fileName = "HD.bin";
		try {
			FileInputStream fileOs = new FileInputStream(fileName);
			ObjectInputStream is = new ObjectInputStream(fileOs);
			for (int i = 0; i < HDSize; i++) {
				int raddblock = is.readInt();
				System.out.println("Block [" + raddblock + "]     |    Tag->" + raddblock + "     [HD]");
				for (int j = 0; j < qWordBlock; j++) {
					int rword = is.readInt();
					System.out.println("Word " + j + " = " + rword);
				}
				boolean rrefreshed = is.readBoolean();
				System.out.println("Is refreshed: " + rrefreshed);
				@SuppressWarnings("unused")
				int rcachehit = is.readInt();
//				System.out.println("cachehit: " + rcachehit);
				int rcont = is.readInt();
				System.out.println("cont: " + rcont);
				@SuppressWarnings("unused")
				int rcost = is.readInt();
//				System.out.println("cost: " + rcost);
				System.out.println("");
			}
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void buildNullCache(int cacheSize, MemoryBlock[] cache) {
		for (int i = 0; i < cacheSize; i++) {
			MemoryBlock aux = new MemoryBlock();
			aux.setAddBlock(-1);
			int[] words = new int[qWordBlock];
			for (int j = 0; j < qWordBlock; j++) {
				words[j] = Integer.MIN_VALUE;
			}
			aux.setWords(words);
			cache[i] = aux;
		}
	}

	void printMemory2(MemoryBlock[] mem, int size, String type) {
		System.out.println("_________________________[" + type + "]_________________________");
		for (int i = 0; i < size; i++) {
			System.out.println("Block [" + i + "]    |    cont:" + mem[i].getCont() + "    |    Tag->"
					+ mem[i].getAddBlock() + "     [" + type + "]");
			for (int j = 0; j < qWordBlock; j++) {
				System.out.println("Word " + j + " = " + mem[i].getWords()[j]);
			}
			System.out.println("Is Refreshed: " + mem[i].isRefreshed());
			System.out.println("");
		}

	}

	// TP3
	void setOnHD(MemoryBlock HDaux) {
		int[] words = new int[qWordBlock];
		String HD = "HD.bin";
		String AUX = "temp.bin";

		try {
			FileOutputStream fileOs = new FileOutputStream(AUX);
			ObjectOutputStream os = new ObjectOutputStream(fileOs);
			FileInputStream fileIs = new FileInputStream(HD);
			ObjectInputStream is = new ObjectInputStream(fileIs);
			for (int i = 0; i < HDSize; i++) {
				int raddblock = is.readInt();

				if (raddblock == HDaux.getAddBlock()) {
					os.writeInt(HDaux.getAddBlock());
					for (int j = 0; j < qWordBlock; j++) {
						is.readInt();
						os.writeInt(HDaux.getWords()[j]);
					}
					is.readBoolean();
					os.writeBoolean(HDaux.isRefreshed());
					is.readInt();
					os.writeInt(HDaux.getCacheHit());
					is.readInt();
					os.writeInt(HDaux.getCont());
					is.readInt();
					os.writeInt(HDaux.getCost());
				} else {

					os.writeInt(raddblock);
					for (int j = 0; j < qWordBlock; j++) {
						words[j] = is.readInt();
						os.writeInt(words[j]);
					}
					boolean rrefreshed = is.readBoolean();
					os.writeBoolean(rrefreshed);
					int rcachehit = is.readInt();
					os.writeInt(rcachehit);
					int rcont = is.readInt();
					os.writeInt(rcont);
					int rcost = is.readInt();
					os.writeInt(rcost);
				}
			}
			os.close();
			is.close();

			FileOutputStream fileOs2 = new FileOutputStream(HD);
			ObjectOutputStream os2 = new ObjectOutputStream(fileOs2);
			FileInputStream fileIs2 = new FileInputStream(AUX);
			ObjectInputStream is2 = new ObjectInputStream(fileIs2);
			for (int i = 0; i < HDSize; i++) {
				int raddblock = is2.readInt();
				os2.writeInt(raddblock);
				for (int j = 0; j < qWordBlock; j++) {
					words[j] = is2.readInt();
					os2.writeInt(words[j]);
				}
				boolean rrefreshed = is2.readBoolean();
				os2.writeBoolean(rrefreshed);
				int rcachehit = is2.readInt();
				os2.writeInt(rcachehit);
				int rcont = is2.readInt();
				os2.writeInt(rcont);
				int rcost = is2.readInt();
				os2.writeInt(rcost);
			}
			os2.close();
			is2.close();
			File file = new File(AUX);
			file.delete();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	MemoryBlock seekOnHD(int addBlock) {
		MemoryBlock HDaux = new MemoryBlock();
		int[] words = new int[qWordBlock];
		String fileName = "HD.bin";
		try {
			FileInputStream fileIs = new FileInputStream(fileName);
			@SuppressWarnings("resource")
			ObjectInputStream is = new ObjectInputStream(fileIs);
			for (int i = 0; i < HDSize; i++) {
				int raddBlock = is.readInt();
				for (int j = 0; j < qWordBlock; j++) {
					words[j] = is.readInt();
				}
				boolean rrefreshed = is.readBoolean();
				int rcachehit = is.readInt();
				int rcont = is.readInt();
				int rcost = is.readInt();

				if (raddBlock == addBlock) {
					HDaux.setAddBlock(raddBlock);
					HDaux.setWords(words);
					HDaux.readRefreshed(rrefreshed);
					HDaux.setCacheHit(rcachehit);
					HDaux.setCont(rcont);
					HDaux.reCost(rcost);
					return HDaux;
				}
			}
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return HDaux;
	}

	void moveHDToRAM(int addBlock, MemoryBlock HD) {
		MemoryBlock aux = new MemoryBlock();
		sortt = new Sorting();
		sortt.ordering(RAM2, ramSize2);

		if (RAM2[ramSize2 - 1].isRefreshed() == true) {
			aux = RAM2[ramSize2 - 1];
			RAM2[ramSize2 - 1] = HD;
			HD = seekOnHD(aux.getAddBlock());
			HD = aux;
			setOnHD(HD);
		} else {
			RAM2[ramSize2 - 1] = HD;
		}
	}

	void moveBetweenMem(MemoryBlock[] from, int fromSize, MemoryBlock[] to, int toSize, int addBlock) {
		MemoryBlock aux = new MemoryBlock();
		sortt = new Sorting();
		sortt.ordering(to, toSize);
		for (int i = 0; i < fromSize; i++) {
			if (from[i].getAddBlock() == addBlock) {
				hitPoint = i;
				break;
			}
		}

		aux = to[toSize - 1];
		to[toSize - 1] = from[hitPoint];
		from[hitPoint] = aux;

		for (int i = 0; i < toSize; i++) {
			if (to[i].getAddBlock() == addBlock) {
				hitPoint = i;
				break;
			}
		}
	}

	MemoryBlock findInMemories(Address add) {
		MemoryBlock HD = new MemoryBlock();
		int addBlock = add.getAddBlock();

		for (int i = 0; i < cache1Size; i++) {
			if (cache1[i].getAddBlock() == addBlock) {
				cache1[i].setCost(1);
				cache1[i].incCont(1);
				cache1[i].setCacheHit(1);
				return cache1[i];
			}
		}

		for (int i = 0; i < cache2Size; i++) {
			if (cache2[i].getAddBlock() == addBlock) {
				cache2[i].setCost(11);
				cache2[i].incCont(1);
				cache2[i].setCacheHit(2);
				moveBetweenMem(cache2, cache2Size, cache1, cache1Size, addBlock);
				return cache1[hitPoint];
			}
		}

		for (int i = 0; i < cache3Size; i++) {
			if (cache3[i].getAddBlock() == addBlock) {
				cache3[i].setCost(111);
				cache3[i].incCont(1);
				cache3[i].setCacheHit(3);
				moveBetweenMem(cache3, cache3Size, cache2, cache2Size, addBlock);
				moveBetweenMem(cache2, cache2Size, cache1, cache1Size, addBlock);
				return cache1[hitPoint];
			}
		}

		for (int i = 0; i < ramSize2; i++) {
			if (RAM2[i].getAddBlock() == addBlock) {
				RAM2[i].setCost(1111);
				RAM2[i].incCont(1);
				RAM2[i].setCacheHit(4);
				moveBetweenMem(RAM2, ramSize2, cache3, cache3Size, addBlock);
				moveBetweenMem(cache3, cache3Size, cache2, cache2Size, addBlock);
				moveBetweenMem(cache2, cache2Size, cache1, cache1Size, addBlock);
				return cache1[hitPoint];
			}
		}

		HD = seekOnHD(addBlock);
		HD.setCost(101111);
		HD.incCont(1);
		HD.setCacheHit(5);
		setOnHD(HD);
		moveHDToRAM(addBlock, HD);
		moveBetweenMem(RAM2, ramSize2, cache3, cache3Size, addBlock);
		moveBetweenMem(cache3, cache3Size, cache2, cache2Size, addBlock);
		moveBetweenMem(cache2, cache2Size, cache1, cache1Size, addBlock);
		return cache1[hitPoint];

	}

	void saveAllrefreshed() {
		System.out.println("");
		System.out.println(">Wait for system save all data on hard disk... (Please be patient)");
		System.out.println("");
		int addBlock;
		
		ramToHD();
		
		for (int i = 0; i < cache3Size; i++) {
			if (cache3[i].isRefreshed() == true) {
				addBlock = cache3[i].getAddBlock();
				moveBetweenMem(cache3, cache3Size, RAM2, ramSize2, addBlock);
				ramToHD();
				cache3[i].unsetRefreshed();
			}
		}
		for (int i = 0; i < cache2Size; i++) {
			if (cache2[i].isRefreshed() == true) {
				addBlock = cache2[i].getAddBlock();
				moveBetweenMem(cache2, cache2Size, cache3, cache3Size, addBlock);
				moveBetweenMem(cache3, cache3Size, RAM2, ramSize2, addBlock);
				ramToHD();
				cache2[i].unsetRefreshed();
			}
		}
		for (int i = 0; i < cache1Size; i++) {
			if (cache1[i].isRefreshed() == true) {
				addBlock = cache1[i].getAddBlock();
				moveBetweenMem(cache1, cache1Size, cache2, cache2Size, addBlock);
				moveBetweenMem(cache2, cache2Size, cache3, cache3Size, addBlock);
				moveBetweenMem(cache3, cache3Size, RAM2, ramSize2, addBlock);
				ramToHD();
				cache1[i].unsetRefreshed();
			}
		}

	}
	
	void ramToHD() {
		MemoryBlock HD = new MemoryBlock();
		for (int i = 0; i < ramSize2; i++) {
			if (RAM2[i].isRefreshed() == true) {
				HD = seekOnHD(RAM2[i].getAddBlock());
				HD = RAM2[i];
				setOnHD(HD);
				RAM2[i].unsetRefreshed();
			}
		}
	}
}
