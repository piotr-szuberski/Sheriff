package DzikiZachod.Gry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PulaAkcji {
    List<Akcja> talia;
    List<Akcja> odrzucone;

    public PulaAkcji() {
        talia = new ArrayList<>();
        odrzucone = new ArrayList<>();
    }

    public void dodaj(Akcja typ, int liczba) {
        for (int i = 0; i < liczba; i++) {
            talia.add(typ);
        }
    }

    public void tasuj() {
        Collections.shuffle(talia);
    }

    public Akcja dobierz() {
        if (talia.size() == 0) {
            talia = odrzucone;
            odrzucone = new ArrayList<>();
            tasuj();
        }
        return talia.remove(talia.size() -1);
    }

    public void odrzuc(Akcja akcja) {
        if (akcja != Akcja.DYNAMIT) {
            odrzucone.add(akcja);
        }
    }

}
