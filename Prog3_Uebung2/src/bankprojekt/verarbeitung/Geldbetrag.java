package bankprojekt.verarbeitung;

import bankprojekt.geld.Waehrung;

/**
 * Ein Geldbetrag mit Währung
 */
public class Geldbetrag implements Comparable<Geldbetrag>{
	/**
	 * Betrag in der in waehrung angegebenen Währung
	 */
	private double betrag;
	/**
	 * Die Währung
	 */
	private Waehrung waehrung;
	
	/**
	 * 0 €
	 */
	public static final Geldbetrag NULL_EURO = new Geldbetrag(0);

	/**
	 * erstellt einen Geldbetrag in der Währung Euro
	 * @param betrag Betrag in €
	 * @throws IllegalArgumentException wenn betrag unendlich oder NaN ist
	 */
	public Geldbetrag(double betrag)
	{
		if(!Double.isFinite(betrag))
			throw new IllegalArgumentException("Betrag ungueltig.");
		this.betrag = betrag;
		this.waehrung = Waehrung.EUR;
	}

	/**
	 * erstellt einen Geldbetrag in der angegebenen Währung
	 * @param betrag Betrag in der angegebenen Währung
	 * @param w die Währung
	 * @throws IllegalArgumentException wenn betrag unendlich oder NaN ist
	 * @throws NullPointerException wenn w null ist
	 */
	public Geldbetrag(double betrag, Waehrung w) throws IllegalArgumentException, NullPointerException {
		this(betrag);
		if (w == null)
			throw new NullPointerException("Währung ist null");
		this.waehrung = w;
	}

	/**
	 * Betrag von this
	 * @return Betrag in der Währung von this
	 */
	public double getBetrag() {
		return betrag;
	}
	
	/**
	 * rechnet this + summand
	 * @param summand zu addierender Betrag
	 * @return this + summand in der Währung von this
	 * @throws IllegalArgumentException wenn summand negativ ist
	 * @throws NullPointerException wenn summand null ist
	 */
	public Geldbetrag plus(Geldbetrag summand) throws IllegalArgumentException, NullPointerException
	{
		if(summand == null)
			throw new NullPointerException("Der Summand ist null");
		//summand ist negativ, das kann bei Konto-Transaktionen Fehler verursachen
		if (summand.isNegativ())
			throw new IllegalArgumentException("Der Summand ist negativ. Bitte die minus-Methode verwenden.");
		Geldbetrag konvertierterSummand = summand;
		if (this.waehrung != summand.waehrung)
			konvertierterSummand = summand.umrechnen(this.waehrung);
		return new Geldbetrag(this.betrag + konvertierterSummand.betrag, this.waehrung);
	}
	
	/**
	 * rechnet this - subtrahend
	 * @param subtrahend abzuziehender Betrag
	 * @return this - subtrahend in der Währung von this
	 * @throws IllegalArgumentException wenn subtrahend negativ ist
	 * @throws NullPointerException wenn subtrahend null ist
	 */
	public Geldbetrag minus(Geldbetrag subtrahend) throws IllegalArgumentException, NullPointerException
	{
		if(subtrahend == null)
			throw new NullPointerException("Der Subtrahend ist null");
		if (subtrahend.isNegativ())
			throw new IllegalArgumentException("Der Subtrahend ist negativ. Bitte die plus-Methode verwenden.");
		Geldbetrag konvertierterSubtrahend = subtrahend;
		if (this.waehrung != subtrahend.waehrung)
			konvertierterSubtrahend = subtrahend.umrechnen(this.waehrung);
		if (this.betrag < subtrahend.betrag)
			throw new IllegalArgumentException("Die Differenz ist negativ");
		return new Geldbetrag(this.betrag - konvertierterSubtrahend.betrag, this.waehrung);
	}

	/**
	 * multipliziert this mit faktor
	 * @param faktor Faktor der Multiplikation
	 * @return das faktor-Fache von this
	 * @throws IllegalArgumentException wenn faktor nicht finit ist
	 */
	public Geldbetrag mal(double faktor) throws IllegalArgumentException
	{
		if(!Double.isFinite(faktor))
			throw new IllegalArgumentException("Der Faktor ist ungueltig");
		return new Geldbetrag(this.betrag * faktor, this.waehrung);
	}

	/**
	 * vergleicht this mit o
	 * @param o das Objekt, mit dem this verglichen wird.
	 * @return 0, wenn this und o gleich sind, 1, wenn this > o und -1, wenn this < o
	 * @throws NullPointerException wenn o null ist
	 */
	@Override
	public int compareTo(Geldbetrag o) throws NullPointerException {
		if (o == null)
			throw new NullPointerException("Der eingegebene Geldbetrag ist null");

		Geldbetrag konvertierterO = o;
		if (this.waehrung != o.waehrung)
			konvertierterO = o.umrechnen(this.waehrung);
		return Double.compare(this.betrag, konvertierterO.betrag);
	}

	/**
	 * Ueberprueft ob this und o dem gleichen Geldbetrag entsprechen
	 * @param o das Objekt, mit dem this verglichen wird
	 * @return true, wenn this und o dem gleichen Geldbetrag entsprechen
	 * @throws NullPointerException wenn o null ist
	 */
	@Override
	public boolean equals(Object o) throws NullPointerException
	{
		if(o == null) throw new NullPointerException("Der Eingabe ist null.");
		if(!(o instanceof Geldbetrag)) return false;
		if(o == this) return true;

		return this.compareTo((Geldbetrag) o) == 0;
	}
	
	/**
	 * prüft, ob this einen negativen Betrag darstellt
	 * @return true, wenn this negativ ist
	 */
	public boolean isNegativ()
	{
		return this.betrag < 0;
	}

	public Geldbetrag umrechnen(Waehrung zielwaehrung) throws NullPointerException {
		if (zielwaehrung == null)
			throw new NullPointerException("Zielwährung ist null.");

		if (this.waehrung == zielwaehrung)
			return this;

		if (this.waehrung == Waehrung.EUR)
			return new Geldbetrag(this.getBetrag() * zielwaehrung.getWechselkurs());

		//Falls weder this.waehrung noch die Zielwährung Euro sind,
		//wird erst this in Euro umgerechnet und dann in die Zielwährung.
		return new Geldbetrag(this.getBetrag() / this.waehrung.getWechselkurs()
				* zielwaehrung.getWechselkurs(), zielwaehrung);
	}

	@Override
	public String toString()
	{
		return String.format("%,.2f %s", this.betrag, this.waehrung.toString());
	}
}