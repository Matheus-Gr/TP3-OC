package fakecomputer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class TESTFIELD {
	Random r;
	Computer comp;

	final int NV_MAX = 10;
	final int NV2_MAXCALL = 3;

	int nv2x = 0;

	void plateBalancer(Memories mem, boolean showOps, int level) {
		r = new Random();
		comp = new Computer();

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("ss");
		int time = Integer.parseInt(sdf.format(cal.getTime()));
		if ((time % 10) == 0) {
			if (level == 2) {
				nv2x++;
				if (nv2x <= NV2_MAXCALL) {
					System.out.println(nv2x);
					Instructions ins1 = new Instructions();
					ins1.interuptions(51);
					comp.run3(mem, ins1, showOps, level);
				}
			} else if (level > 2 && level <= NV_MAX) {
				if (r.nextInt(101) > 70) {
					Instructions ins1 = new Instructions();
					ins1.interuptions(51);
					comp.run3(mem, ins1, showOps, level);
				}
			}
		}
	}
}
