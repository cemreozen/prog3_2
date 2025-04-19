package bankprojekt.geld;

/**
 * Enum für einige Waehrungen,
 * die mit einem festen Umrechnungskurs zum Euro gebunden sind.
 * @author Cemre
 */
public enum Waehrung {

    /**
     * Euro
     */
    EUR(1),
    /**
     * Escudo
     */
    ESCUDO(109.8269),
    /**
     * Dobra
     */
    DOBRA(24304.7429),
    /**
     * Franc
     */
    FRANC(490.92);

    /**
     * Private Enum-Konstruktor
     * @param wechselkurs der Umrechnungskurs zum Euro
     */
    private Waehrung(double wechselkurs) {
        this.wechselkurs = wechselkurs;
    }

    /**
     * liefert den Umrechnungskurs zum Euro
     * @return den Umrechnungskurs
     */
    public double getWechselkurs() {
        return wechselkurs;
    }

    private double wechselkurs;

    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }
}