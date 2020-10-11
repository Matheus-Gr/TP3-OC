package fakecomputer;


public class Computer {
	TESTFIELD pb = new TESTFIELD();

	long tallcost = 0;
	long tmissC1 = 0;
	long thitC1 = 0;
	long tmissC2 = 0;
	long thitC2 = 0;
	long tmissC3 = 0;
	long thitC3 = 0;
	long thitRAM = 0;
	long tmissRAM = 0;
	long thitHD = 0;

	void run(Memories ram, Instructions instruc, boolean showOps) {
		int PC = 0;
		int opcode = Integer.MAX_VALUE;
		while (opcode != -1) {
			opcode = instruc.iMemory[PC][0];
			switch (opcode) {
				case 0: {
					ram.RAM[instruc.iMemory[PC][2]] = instruc.iMemory[PC][1];
					if (showOps) {
						System.out.println(
								"leading to RAM: " + instruc.iMemory[PC][1] + ", on adress " + instruc.iMemory[PC][2]);
					}
					break;
				}
				case 1: {
					int addr1 = instruc.iMemory[PC][1];
					int addr2 = instruc.iMemory[PC][2];
	
					int contentRam1 = ram.RAM[addr1];
					int contentRam2 = ram.RAM[addr2];
	
					int addup = contentRam1 + contentRam2;
	
					int addr3 = instruc.iMemory[PC][3];
					ram.RAM[addr3] = addup;
					if (showOps) {
						System.out.println("adding up... " + contentRam1 + " + " + contentRam2 + " = " + addup
								+ ", saved on adress: " + addr3);
					}
					break;
				}
				case 2: {
					int addr1 = instruc.iMemory[PC][1];
					int addr2 = instruc.iMemory[PC][2];
	
					int contentRam1 = ram.RAM[addr1];
					int contentRam2 = ram.RAM[addr2];
	
					int subtract = contentRam1 - contentRam2;
	
					int addr3 = instruc.iMemory[PC][3];
					ram.RAM[addr3] = subtract;
					if (showOps) {
						System.out.println("subtracting... " + contentRam1 + " - " + contentRam2 + " = " + subtract
								+ ", saved on adress: " + addr3);
					}
					break;
				}
			}

			PC++;
		}

	}

	void run2(Memories mem, Instructions instruc, boolean showOps) {
		int PC = 0;
		int opcode = Integer.MAX_VALUE;
		long allcost = 0;
		int missC1 = 0;
		int hitC1 = 0;
		int missC2 = 0;
		int hitC2 = 0;
		int missC3 = 0;
		int hitC3 = 0;
		int hitRAM = 0;
		int missRAM = 0;
		int hitHD = 0;

		while (opcode != -1) {

			Inst aInstuction = instruc.iMemory2[PC];
			opcode = instruc.iMemory2[PC].getOpcode();
			if (opcode != -1) {

				MemoryBlock memoryData1 = mem.findInMemories(aInstuction.add1);
				MemoryBlock memoryData2 = mem.findInMemories(aInstuction.add2);
				MemoryBlock memoryData3 = mem.findInMemories(aInstuction.add3);

				allcost += (long) memoryData1.getCost();
				allcost += (long) memoryData2.getCost();
				allcost += (long) memoryData3.getCost();

				if (memoryData1.getCacheHit() == 1) {
					hitC1++;
				} else if (memoryData1.getCacheHit() == 2) {
					missC1++;
					hitC2++;
				} else if (memoryData1.getCacheHit() == 3) {
					missC1++;
					missC2++;
					hitC3++;
				} else if (memoryData1.getCacheHit() == 4) {
					missC1++;
					missC2++;
					missC3++;
					hitRAM++;
				} else if (memoryData1.getCacheHit() == 5) {
					missC1++;
					missC2++;
					missC3++;
					missRAM++;
					hitHD++;
				}

				if (memoryData2.getCacheHit() == 1) {
					hitC1++;
				} else if (memoryData2.getCacheHit() == 2) {
					missC1++;
					hitC2++;
				} else if (memoryData2.getCacheHit() == 3) {
					missC1++;
					missC2++;
					hitC3++;
				} else if (memoryData2.getCacheHit() == 4) {
					missC1++;
					missC2++;
					missC3++;
					hitRAM++;
				} else if (memoryData2.getCacheHit() == 5) {
					missC1++;
					missC2++;
					missC3++;
					missRAM++;
					hitHD++;
				}

				if (memoryData3.getCacheHit() == 1) {
					hitC1++;
				} else if (memoryData3.getCacheHit() == 2) {
					missC1++;
					hitC2++;
				} else if (memoryData3.getCacheHit() == 3) {
					missC1++;
					missC2++;
					hitC3++;
				} else if (memoryData3.getCacheHit() == 4) {
					missC1++;
					missC2++;
					missC3++;
					hitRAM++;
				} else if (memoryData3.getCacheHit() == 5) {
					missC1++;
					missC2++;
					missC3++;
					missRAM++;
					hitHD++;
				}

				switch (opcode) {
					case 0: {
						int content1 = memoryData1.getWords()[aInstuction.add1.getAddWords()];
	
						memoryData2.getWords()[aInstuction.add2.getAddWords()] = content1;
						memoryData2.setRefreshed();
						if (showOps) {
							System.out.println("|Leading to RAM |" + content1 + ", on Block: " + memoryData2.getAddBlock()
									+ " Word: " + aInstuction.add2.getAddWords());
						}
						break;
					}
					case 1: {
						int content1 = memoryData1.getWords()[aInstuction.add1.getAddWords()];
						int content2 = memoryData2.getWords()[aInstuction.add2.getAddWords()];
	
						int addUp = content1 + content2;
	
						memoryData3.getWords()[aInstuction.add3.getAddWords()] = addUp;
						memoryData3.setRefreshed();
	
						if (showOps) {
							System.out.println("|Adding up      |" + content1 + " + " + content2 + " = " + addUp);
							System.out.println("                >Saved on Block: " + aInstuction.add3.getAddBlock()
									+ " ,and word :" + aInstuction.add3.getAddWords());
						}
						break;
					}
					case 2: {
						int content1 = memoryData1.getWords()[aInstuction.add1.getAddWords()];
						int content2 = memoryData2.getWords()[aInstuction.add2.getAddWords()];
	
						int subtraction = content1 - content2;
	
						memoryData3.getWords()[aInstuction.add3.getAddWords()] = subtraction;
						memoryData3.setRefreshed();
						
						if (showOps) {
							System.out.println("|Subtracting    |" + content1 + " - " + content2 + " = " + subtraction);
							System.out.println("                >Saved on Block: " + aInstuction.add3.getAddBlock()
									+ " ,and word: " + aInstuction.add3.getAddWords());
						}
						break;
					}
				}
				
				PC++;

			}
		}
		System.out.println("Total cost:   " + allcost);
		System.out.println("Hits & Misses | C1  | Hit: " + hitC1 + "  | Miss: " + missC1 + " | Hit rate: "
				+ ((hitC1 * 100) / (hitC1 + missC1)) + "%  | Use rate: "
				+ ((hitC1 * 100) / (hitC1 + hitC2 + hitC3 + hitRAM + hitHD)) + "%");
		System.out.println("              | C2  | Hit: " + hitC2 + "  | Miss: " + missC2 + " | Hit rate: "
				+ ((hitC2 * 100) / (hitC2 + missC2)) + "%  | Use rate: "
				+ ((hitC2 * 100) / (hitC1 + hitC2 + hitC3 + hitRAM + hitHD)) + "%");
		System.out.println("              | C3  | Hit: " + hitC3 + "  | Miss: " + missC3 + " | Hit rate: "
				+ ((hitC3 * 100) / (hitC3 + missC3)) + "%  | Use rate: "
				+ ((hitC3 * 100) / (hitC1 + hitC2 + hitC3 + hitRAM + hitHD)) + "%");
		System.out.println("              | RAM | Hit: " + hitRAM + "  | Miss: " + missRAM + " | Hit rate: "
				+ ((hitRAM * 100) / (hitRAM + missRAM)) + "%  | Use rate: "
				+ ((hitRAM * 100) / (hitC1 + hitC2 + hitC3 + hitRAM + hitHD)) + "%");
		System.out.println("              | HD  | Hit: " + hitHD + "  | Miss: " + 0 + " | Hit rate: " + 100
				+ "%  | Use rate: " + ((hitHD * 100) / (hitC1 + hitC2 + hitC3 + hitRAM + hitHD)) + "%");
	}

	void run3(Memories mem, Instructions instruc, boolean showOps, int level) {
		int nlevel = level + 1;
		for (int i = 0; i < level; i++) {
			System.out.print("  ");
		}
		System.out.println(">[NV" + level + "](Start)");
		int PC = 0;
		int opcode = Integer.MAX_VALUE;
		long allcost = 0;
		int missC1 = 0;
		int hitC1 = 0;
		int missC2 = 0;
		int hitC2 = 0;
		int missC3 = 0;
		int hitC3 = 0;
		int hitRAM = 0;
		int missRAM = 0;
		int hitHD = 0;

		while (opcode != -1) {

			Inst aInstuction = instruc.iMemory2[PC];
			opcode = instruc.iMemory2[PC].getOpcode();
			if (opcode != -1) {

				MemoryBlock memoryData1 = mem.findInMemories(aInstuction.add1);
				MemoryBlock memoryData2 = mem.findInMemories(aInstuction.add2);
				MemoryBlock memoryData3 = mem.findInMemories(aInstuction.add3);
	
				allcost += (long) memoryData1.getCost();
				allcost += (long) memoryData2.getCost();
				allcost += (long) memoryData3.getCost();
				
				if (memoryData1.getCacheHit() == 1) {
					hitC1++;
				} else if (memoryData1.getCacheHit() == 2) {
					missC1++;
					hitC2++;
				} else if (memoryData1.getCacheHit() == 3) {
					missC1++;
					missC2++;
					hitC3++;
				} else if (memoryData1.getCacheHit() == 4) {
					missC1++;
					missC2++;
					missC3++;
					hitRAM++;
				} else if (memoryData1.getCacheHit() == 5) {
					missC1++;
					missC2++;
					missC3++;
					missRAM++;
					hitHD++;
				}

				if (memoryData2.getCacheHit() == 1) {
					hitC1++;
				} else if (memoryData2.getCacheHit() == 2) {
					missC1++;
					hitC2++;
				} else if (memoryData2.getCacheHit() == 3) {
					missC1++;
					missC2++;
					hitC3++;
				} else if (memoryData2.getCacheHit() == 4) {
					missC1++;
					missC2++;
					missC3++;
					hitRAM++;
				} else if (memoryData2.getCacheHit() == 5) {
					missC1++;
					missC2++;
					missC3++;
					missRAM++;
					hitHD++;
				}

				if (memoryData3.getCacheHit() == 1) {
					hitC1++;
				} else if (memoryData3.getCacheHit() == 2) {
					missC1++;
					hitC2++;
				} else if (memoryData3.getCacheHit() == 3) {
					missC1++;
					missC2++;
					hitC3++;
				} else if (memoryData3.getCacheHit() == 4) {
					missC1++;
					missC2++;
					missC3++;
					hitRAM++;
				} else if (memoryData3.getCacheHit() == 5) {
					missC1++;
					missC2++;
					missC3++;
					missRAM++;
					hitHD++;
				}

				tallcost += allcost;
				tmissC1 += missC1;
				thitC1 += hitC1;
				tmissC2 += missC2;
				thitC2 += hitC2;
				tmissC3 += missC3;
				thitC3 += hitC3;
				thitRAM += hitRAM;
				tmissRAM += missRAM;
				thitHD += hitHD;

				switch (opcode) {
				case 0: {
					int content1 = memoryData1.getWords()[aInstuction.add1.getAddWords()];

					memoryData2.getWords()[aInstuction.add2.getAddWords()] = content1;
					memoryData2.setRefreshed();
					if (showOps) {
						System.out.println("|Leading to RAM |" + content1 + ", on Block: " + memoryData2.getAddBlock()
								+ " Word: " + aInstuction.add2.getAddWords());
					}
					break;
				}
				case 1: {
					int content1 = memoryData1.getWords()[aInstuction.add1.getAddWords()];
					int content2 = memoryData2.getWords()[aInstuction.add2.getAddWords()];

					int addUp = content1 + content2;

					memoryData3.getWords()[aInstuction.add3.getAddWords()] = addUp;
					memoryData3.setRefreshed();

					if (showOps) {
						System.out.println("|Adding up      |" + content1 + " + " + content2 + " = " + addUp);
						System.out.println("                >Saved on Block: " + aInstuction.add3.getAddBlock()
								+ " ,and word :" + aInstuction.add3.getAddWords());
					}
					break;
				}
				case 2: {
					int content1 = memoryData1.getWords()[aInstuction.add1.getAddWords()];
					int content2 = memoryData2.getWords()[aInstuction.add2.getAddWords()];

					int subtraction = content1 - content2;

					memoryData3.getWords()[aInstuction.add3.getAddWords()] = subtraction;
					memoryData3.setRefreshed();

					if (showOps) {
						System.out.println("|Subtracting    |" + content1 + " - " + content2 + " = " + subtraction);
						System.out.println("                >Saved on Block: " + aInstuction.add3.getAddBlock()
								+ " ,and word: " + aInstuction.add3.getAddWords());
					}
					break;
				}
				}

				PC++;
				
				if(level == 10) {
					TP3.stop = true;
				}else if(level == 1) {
					TP3.stop = false;
				}
				
				if(TP3.stop == false) {
					pb.plateBalancer(mem, showOps, nlevel);
				}
			}

		}
		for (int i = 0; i < level; i++) {
			System.out.print("  ");
		}
		System.out.println(">[NV" + level + "](Over)");

		if (showOps) {
			System.out.println("");
			System.out.println("Total cost:   " + allcost);
			System.out.println("Hits & Misses | C1  | Hit: " + hitC1 + "  | Miss: " + missC1 + " | Hit rate: "
					+ ((hitC1 * 100) / (hitC1 + missC1)) + "%  | Use rate: "
					+ ((hitC1 * 100) / (hitC1 + hitC2 + hitC3 + hitRAM + hitHD)) + "%");
			System.out.println("              | C2  | Hit: " + hitC2 + "  | Miss: " + missC2 + " | Hit rate: "
					+ ((hitC2 * 100) / (hitC2 + missC2)) + "%  | Use rate: "
					+ ((hitC2 * 100) / (hitC1 + hitC2 + hitC3 + hitRAM + hitHD)) + "%");
			System.out.println("              | C3  | Hit: " + hitC3 + "  | Miss: " + missC3 + " | Hit rate: "
					+ ((hitC3 * 100) / (hitC3 + missC3)) + "%  | Use rate: "
					+ ((hitC3 * 100) / (hitC1 + hitC2 + hitC3 + hitRAM + hitHD)) + "%");
			System.out.println("              | RAM | Hit: " + hitRAM + "  | Miss: " + missRAM + " | Hit rate: "
					+ ((hitRAM * 100) / (hitRAM + missRAM)) + "%  | Use rate: "
					+ ((hitRAM * 100) / (hitC1 + hitC2 + hitC3 + hitRAM + hitHD)) + "%");
			System.out.println("              | HD  | Hit: " + hitHD + "  | Miss: " + 0 + " | Hit rate: " + 100
					+ "%  | Use rate: " + ((hitHD * 100) / (hitC1 + hitC2 + hitC3 + hitRAM + hitHD)) + "%");
		}

	}

	void printTotalResults() {
		System.out.println("");
		System.out.println("Total cost:   " + tallcost);
		System.out.println("Hits & Misses | C1  | Hit: " + thitC1 + "  | Miss: " + tmissC1 + " | Hit rate: "
				+ ((thitC1 * 100) / (thitC1 + tmissC1)) + "%  | Use rate: "
				+ ((thitC1 * 100) / (thitC1 + thitC2 + thitC3 + thitRAM + thitHD)) + "%");
		System.out.println("              | C2  | Hit: " + thitC2 + "  | Miss: " + tmissC2 + " | Hit rate: "
				+ ((thitC2 * 100) / (thitC2 + tmissC2)) + "%  | Use rate: "
				+ ((thitC2 * 100) / (thitC1 + thitC2 + thitC3 + thitRAM + thitHD)) + "%");
		System.out.println("              | C3  | Hit: " + thitC3 + "  | Miss: " + tmissC3 + " | Hit rate: "
				+ ((thitC3 * 100) / (thitC3 + tmissC3)) + "%  | Use rate: "
				+ ((thitC3 * 100) / (thitC1 + thitC2 + thitC3 + thitRAM + thitHD)) + "%");
		System.out.println("              | RAM | Hit: " + thitRAM + "  | Miss: " + tmissRAM + " | Hit rate: "
				+ ((thitRAM * 100) / (thitRAM + tmissRAM)) + "%  | Use rate: "
				+ ((thitRAM * 100) / (thitC1 + thitC2 + thitC3 + thitRAM + thitHD)) + "%");
		System.out.println("              | HD  | Hit: " + thitHD + "  | Miss: " + 0 + " | Hit rate: " + 100
				+ "%  | Use rate: " + ((thitHD * 100) / (thitC1 + thitC2 + thitC3 + thitRAM + thitHD)) + "%");
	}
}
