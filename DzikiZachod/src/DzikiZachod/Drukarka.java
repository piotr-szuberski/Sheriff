package DzikiZachod;

import DzikiZachod.Gracze.Gracz;
import DzikiZachod.Gry.Akcja;
import DzikiZachod.Gry.Kolejka;
import DzikiZachod.Gry.StanGry;

import java.io.PrintStream;

/**
 * Created by pjotrekk on 28.05.17.
 */
public class Drukarka {

    private final PrintStream stream = System.out;

    public void opisGracza(Gracz gracz) {
        stream.print("    " + (gracz.wezIndeks() + 1) + ": ");
        String nazwa = gracz.podajTyp().toString();
        if (gracz.punktyZycia() == 0)
            stream.println("X (" + nazwa + ")");
        else
            stream.println(nazwa + " (liczba życ: " + gracz.punktyZycia() + ")");
    }

    public void listaGraczy(Kolejka kolejka) {
        stream.println("  Gracze:");
        for (Gracz g: kolejka.gracze()) {
            opisGracza(g);
        }
        stream.println();
    }

    public void poczatek(Kolejka kolejka) {
        stream.println("** START");
        listaGraczy(kolejka);
    }

    public void naglowekGracza(Gracz gracz) {
        stream.println("  GRACZ " + (gracz.wezIndeks() + 1) + " (" +  gracz.podajTyp() + ")");
        if (gracz.punktyZycia() == 0) {
            stream.println("    MARTWY");
            stream.println();
        }
    }

    public void strzal(Gracz kogo) {
        stream.println("      " + Akcja.STRZEL + " " + (kogo.wezIndeks() + 1));
    }
    public void ulecz(Gracz kto, Gracz kogo) {
        if (kto == kogo)
            stream.println("      ULECZ");
        else
            stream.println("      ULECZ " + (kogo.wezIndeks() + 1));
    }

    public void wykonaj_akcje(Akcja wzrostZasiegu) {
        stream.println("      " + wzrostZasiegu);
    }

    public void tura(int numerTury) {
        stream.println("** TURA " + numerTury);
    }

    public void listaAkcji(Gracz gracz) {
        stream.println("    Akcje: " + gracz.listaAkcji());
    }

    public void dynamitWybuchl(Gracz gracz) {
        stream.println("    Dynamit: WYBUCHŁ");
        stream.println("    Ruchy:");
        if (gracz.punktyZycia() == 0) {
            stream.println("      MARTWY");
            stream.println();
        }
    }

    public void dynamitPrzechodzi() {
        stream.println("    Dynamit: PRZECHODZI DALEJ");
    }

    public void koniec(StanGry stanGry) {
        if (!stanGry.koniecGry())
            throw new RuntimeException("niepoprawny stan gry!");

        stream.println("** KONIEC");
        switch (stanGry) {
            case BandyciWygrali:
                stream.println("  WYGRANA STRONA: bandyci");
                break;
            case SzeryfWygral:
                stream.println("  WYGRANA STRONA: szeryf i pomocnicy");
                break;
            case Remis:
                stream.println("  REMIS - OSIĄGNIĘTO LIMIT TUR");
                break;
        }
    }

    public void pustaLinia() {
        stream.println();
    }
}
