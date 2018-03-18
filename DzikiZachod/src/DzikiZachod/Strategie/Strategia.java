package DzikiZachod.Strategie;


import DzikiZachod.Gracze.Gracz;
import DzikiZachod.Gry.Gra;

import java.util.Set;

/**
 * Created by pjotrekk on 26.05.17.
 */
public abstract class Strategia {

    protected Gracz gracz;
    public void przyjmijGracza(Gracz gracz) {
        this.gracz = gracz;
    }
    public abstract void przyjmijGre(Gra gra);

    public Gracz wybierzLeczonego(Set<Gracz> opcje) {
        return opcje.contains(gracz) ? gracz : null;
    }
    public boolean czyZwiekszycZasieg(int ile) {
        return true;
    }
    public abstract Gracz wybierzOfiare(Set<Gracz> opcje);
    public abstract boolean czyRzucicDynamit();

}
