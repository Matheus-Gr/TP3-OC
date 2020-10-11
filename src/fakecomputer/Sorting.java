package fakecomputer;


public class Sorting {

	public int[] addTags;
	public int[] counters;
	void ordering(MemoryBlock[] MEM,int size) {
		addTags = new int [size];
		counters = new int [size];
		for (int i = 0; i < size; i++) {
			addTags[i] = MEM[i].getAddBlock();
			counters[i] = MEM[i].getCont();
		}
		for (int i = 0; i <size  - 1; i++) {

			for (int j = 0; j <size  - 1 - i; j++) {

				if (counters[j] < counters[j + 1]) {
					int aux = counters[j];
					int addaux = addTags[j];
					MemoryBlock cacaux = MEM[j];
					counters[j] = counters[j + 1];
					addTags[j] = addTags[j + 1];
					MEM[j] = MEM[j + 1];
					counters[j + 1] = aux;
					addTags[j + 1] = addaux;
					MEM[j + 1] = cacaux;
				}
			}
		}

		for(int a=0;a<size;a++) {
//			System.out.println("address" + addTags[a]);
		}
	}
}
