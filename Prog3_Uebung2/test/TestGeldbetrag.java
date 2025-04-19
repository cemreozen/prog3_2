import bankprojekt.geld.Waehrung;
import bankprojekt.verarbeitung.Geldbetrag;
import bankprojekt.verarbeitung.Girokonto;
import bankprojekt.verarbeitung.Konto;
import bankprojekt.verarbeitung.Kunde;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;

public class TestGeldbetrag {

    public static void main(String[] args) {
        Geldbetrag test1 = new Geldbetrag(100.0, Waehrung.DOBRA);
        Geldbetrag test2 = new Geldbetrag(1435134581, Waehrung.ESCUDO);

        Geldbetrag test5 = new Geldbetrag(100.0, Waehrung.EUR);

        Geldbetrag test4 = test1.umrechnen(Waehrung.ESCUDO);
        Geldbetrag test3 = test2.plus(test1);

        System.out.println(test1);
        System.out.println(test2);
        System.out.println(test3);
        System.out.println(test4);
        System.out.println(test5);



        Konto testkonto = new Girokonto(new Kunde("Cemre", "Ozen", "Berlin", LocalDate.of(1999, 2, 2)), 123456789, new Geldbetrag(1990, Waehrung.EUR));
        testkonto.waehrungswechsel(null);
        testkonto.ausgeben();

    }

}
