public class VariablennameAutomatTest {
	public static void main(String[] args) {
		VariablennameAutomat automat = new VariablennameAutomat();
		System.out.println(automat.testen("ü"));
		System.out.println(automat.testen("done_deal"));
		System.out.println(automat.testen("_____"));
		System.out.println(automat.testen("_code"));
	}
}