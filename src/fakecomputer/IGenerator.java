package fakecomputer;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Random;


public class IGenerator {
	
	static int QTD_INS = Instructions.iSize2; 
	static int TAM_FOR = 15; 
	static int TAM_MEM = Memories.HDSize; 
	static int PROB_FOR = 70; 

	public static void main(String args[]) throws IOException {
		int random;
	
		String separador = ":";
		Random r = new Random();
		ArrayList<String> ins = new ArrayList<>();

		int N = 3;
		int instruc[] = { 6, 6, 6, 6, 6, 6, 6, 6 };			
		
		ArrayList<String> repeat = new ArrayList<>();
		for (int i = 0; i < TAM_FOR; i++) {
			random = r.nextInt(N);
			String s = "";
			for (int j = 0; j < instruc[random]; j++) {
				s += separador + r.nextInt(TAM_MEM);
			}
			repeat.add(random + s);
		}

		for (int i = 0; i < QTD_INS;) {
			random = r.nextInt(100) + 1;
			if (random <= PROB_FOR && i + TAM_FOR < QTD_INS) {
				i = i + TAM_FOR;
				ins.addAll(repeat);
			} else {
				i++;
				random = r.nextInt(N);
				String s = "";
				for (int j = 0; j < instruc[random]; j++) {
					s += separador + r.nextInt(TAM_MEM);
				}
				ins.add(random + s);
			}
		}
		OutputStream os = new FileOutputStream("inst.txt");
		OutputStreamWriter osw = new OutputStreamWriter(os);
		BufferedWriter bw = new BufferedWriter(osw);
		for (int i = 0; i < ins.size(); i++) {
			bw.write(ins.get(i));
			bw.newLine();
		}
		bw.close();
	}
}
