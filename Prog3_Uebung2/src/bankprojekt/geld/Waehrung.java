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
     * Escudo, die Waehrung von Kap Verde
     */
    ESCUDO(109.8269),

    /**
     * Dobra, die Waehrung von Sao Tome und Principe
     */
    DOBRA(24304.7429),
    /**
     * Franc, die Waehrung der Komoren
     */
    FRANC(490.92);

    /**
     * Enum-Konstruktor
     * @param wechselkurs der Umrechnungskurs zum Euro
     */
    private Waehrung(double wechselkurs) {
        this.wechselkurs = wechselkurs;
    }

    /**
     * Umrechnungskurs zum Euro
     */
    private final double wechselkurs;


    /**
     * liefert den Umrechnungskurs zum Euro
     * @return den Umrechnungskurs
     */
    public double getWechselkurs() {
        return wechselkurs;
    }

    /**
     * Liefert den umformatierten Namen der Waehrung
     * @return den Namen der Waehrung
     */
    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }
}