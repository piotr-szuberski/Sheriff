package DzikiZachod.Strategie.Szeryfa;

import DzikiZachod.Gracze.Gracz;
import DzikiZachod.Gry.Gra;
import DzikiZachod.Gry.Widok;
import DzikiZachod.Strategie.Strategia;

/**
 * Created by pjotrekk on 26.05.17.
 */
public abstract class StrategiaSzeryfa extends Strategia {
    protected Widok widok;

    @Override
    public final void przyjmijGre(Gra gra) {
        this.widok = gra;
    }

    @Override
    public boolean czyRzucicDynamit() {
        return false;
    }

    protected boolean raczejBandyta(Gracz gracz) {
        return widok.wrogowieSzeryfa().jestWrogiem(gracz) ||
                widok.zabojstwaBandytow().ileZabil(gracz) < widok.zabojstwaPomocnikow().ileZabil(gracz);
    }
}
