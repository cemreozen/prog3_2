
/**
 * akzeptiert gültige Variablennamen in C
 * @author Doro
 */
public class VariablennameAutomat implements EndlicherAutomat {
//ein gültiger Variablenname beginnt mit einem Buchstaben zwischen A und Z bzw. a und z oder einem Unterstrich.
// danach sind wieder buchstaben zw A-Z bzw a-z der Unterstrich oder Ziffern erlaubt.

	public static final int STARTZUSTAND = 0;
	private int aktuellerZustand;
	private static final int ENDZUSTAND = 1;
	private char[] zeichenketteAlsArray;

	public void setZeichenketteAlsArray(char[] zeichenketteAlsArray) throws NullPointerException {
		if (zeichenketteAlsArray == null) throw new NullPointerException("Eingabe ist null");
		this.zeichenketteAlsArray = zeichenketteAlsArray;
	}

	@Override
	public void startAutomat(String zeichenkette) {
		setZeichenketteAlsArray(zeichenkette.toCharArray());
		aktuellerZustand = STARTZUSTAND;
	}

	@Override
	public boolean isAkzeptierend() {
		return aktuellerZustand == ENDZUSTAND;
	}

	@Override
	public void wechselZustand() {
		int index = 0;
		if (aktuellerZustand == STARTZUSTAND
			&& (isBuchstabe(zeichenketteAlsArray[index]) || isUnderscore(zeichenketteAlsArray[index]))) {
			index++;
			aktuellerZustand = 1;
		} else {
			aktuellerZustand = 2;
			return;
		}
		while (index < zeichenketteAlsArray.length && aktuellerZustand == 1) {
			if (!(isBuchstabe(zeichenketteAlsArray[index]) ||
				isZiffer(zeichenketteAlsArray[index]) ||
				isUnderscore(zeichenketteAlsArray[index]))) {
				aktuellerZustand = 2;
				return;
			}
			index++;
			if (index == zeichenketteAlsArray.length -1 && isUnderscore(zeichenketteAlsArray[index])) {
				aktuellerZustand = 2;
				return;
			}
		}
	}

	public boolean isBuchstabe(char c) {
		// überprüft, ob der char ein gültiges ASCII zeichen ist. zwischen A-Z und a-z.
		// Umlaute werden ausgeschlossen.
		if (c < 'A' || c > 'z' || (c > 90 && c < 97))
			return false;
		return true;
	}

	public boolean isUnderscore(char c) {
		return (int) c == 95;
	}

	public boolean isZiffer(char c) {
		return Character.isDigit(c);
	}
}


