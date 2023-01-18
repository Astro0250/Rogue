
public class Main {
	public static void main(String[] args) {
	wazzup man = new wazzup();
	whyHolmie Q = new whyHolmie(man);
	
	System.out.println(Q.isBeautiful(5));
	System.out.println(Q.isBeautiful(true));
	Q.statementExecution(10);
	System.out.println(Q.returnobject());
	System.out.println(Q.returnobject());
	System.out.println(Q.countdowns());
	int[] g = {3, 4, 5, 6, 6};
	wazzup[] f = new wazzup[] {man, man ,man, man, man};
	for (int i = 0; i < g.length; i++) {
		System.out.print(g[i] + " ");
	}
	System.out.println();
	for (wazzup bro : f) {
		System.out.println(bro);
	}
	System.out.println(Q.maximumInArray(g));
	System.out.println(Q.minimumInArray(g));
	}
	
}
