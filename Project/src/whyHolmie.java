
public class whyHolmie {
	private wazzup yo = new wazzup();
	private static int countdown = 0;
	public static void countdown(int a) {
		countdown += a;
	}
	public whyHolmie(wazzup a) {
		System.out.println("Initialized");
		System.out.println(a);
	}
	private int x;
	private int x() {
		return x;
	}
	private void x(int b) {
		x = b;
	}
	public boolean isBeautiful(int a) {
		x(0);
		for (int i = 0; i < a; i++) {
			for (i = 0; i < a; i++) {
				x(x()+1);
				if (i*2 == x() ) {
					return true;
				}
			}
		}
		return false;
	}
	public wazzup returnobject() {
		countdown++;
		return yo;
	}
	public boolean isBeautiful(boolean a) {
		x(0);
		while (x() < 10) {
			return a;
		}
		return false;
	}
	 /** Method that prints out the same amount of asterisks as the number you inputed
	   * precondition:
	   * @param a represents number of asterisks to be printed, must be int that is 0 or greater
	   * postcondition:
	   * @return same amount of asterisks as the number you inputed
	   *  */
	public void statementExecution(int a) {
		int o = 0;
		for (int i = 0; i < a; i++) {
			System.out.print("*" + " ");
			o++;
		}
		System.out.println("The * was printed " + o + " times");
}
	public static int countdowns() {
		return countdown;
	}
	public int maximumInArray(int[] a) {
		int x = Integer.MIN_VALUE;
		for (int i = 0; i < a.length; i++) {
			if (a[i] > x) {
				x = a[i];
			}
		}
		return x;
	}
	public int minimumInArray(int[] a) {
		int x = Integer.MAX_VALUE;
		for (int i = 0; i < a.length; i++) {
			if (a[i] < x) {
				x = a[i];
			}
		}
		return x;
	}
}
