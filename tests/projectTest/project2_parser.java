
public class project2_parser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testing();
	}
	
	static public void testing() throws ExceptionName, IOException {

		int a  = 1l;
		
		float b = 310.0f;
		
		double c = 4.5;
		
		long d = 2l;
		
		a = 1 / 2;
		
		a = 2 % 3;
		
		boolean d = 3 > 4;
		
		d = 4 < 5;
		
		d = 5 >= 6;
		
		d = 6 <= 7;
		
		a = a & 8;
		
		a = a | 9;
		
		a = a ^ 10;
		
		a = a ~ 11;
		
		a = a << 12;
		
		a = a >> 13;
		
		a = a >>> 14;
		
		d = (true && false);
		
		d = (true || false);
		
		a += 15;
		
		a += 16;
		
		a -= 17;
		
		a *= 18;
		
		a /= 19;
		
		a %= 20;
		
		a <<= 21;
		
		a >>= 22;
		
		a &= 23;
		
		a ^= 24;
		
		a |= 25;
		
		a = a < 26 ? 27 : 28;
		
		while (a < 29) {
			int b = 2;
		}
		
		for (a = 30; a < 31;) {
			a++;
		}
		
		int[] e = new int[32];

		for (int i : e) {
			i++;
		}
		
		switch (a) {
		case 33:
			a++;
			break;
		case 34:
			a++;
			break;
		}
		
		switch (a) {
		case 35:
			a++;
			break;
		default:
			a++;
			break;
		}
		
		try {
			a = 36;
		} catch (Exception e) {
			a = 37;
		}
		
		try {
			a = 38;
		} catch (Exception e) {
			a = 39;
			throw e;
		} finally {
			a = 40;
		}

		
		while (a < 50) {
			a *= 2;
		}
		
		do {
			a *= 2;
		} until (a > 100);
	}
	
	void doSomething(int... a) {
		
	}
}
