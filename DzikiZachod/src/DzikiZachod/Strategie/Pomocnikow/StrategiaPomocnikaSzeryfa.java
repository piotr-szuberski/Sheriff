package DzikiZachod.Strategie.Pomocnikow;

import DzikiZachod.Gracze.Gracz;
import DzikiZachod.Gry.Gra;
import DzikiZachod.Gry.Widok;
import DzikiZachod.Strategie.Strategia;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by pjotrekk on 26.05.17.
 */
public abstract class StrategiaPomocnikaSzeryfa extends Strategia {
    protected Widok widok;

    @Override
    public final void przyjmijGre(Gra gra) {
        this.widok = gra;
    }

    @Override
    public Gracz wybierzLeczonego(Set<Gracz> ranni) {
        if (ranni.contains(widok.kolejka().szeryf())) return widok.kolejka().szeryf();
        return super.wybierzLeczonego(ranni);
    }


    @Override
    public boolean czyRzucicDynamit() {
        List<Gracz> doSzeryfa = new ArrayList<>();
        Gracz przedSzeryfem = widok.kolejka().nastepnyZywy(gracz);
        while (przedSzeryfem != widok.kolejka().szeryf()) {
            doSzeryfa.add(przedSzeryfem);
            przedSzeryfem = widok.kolejka().nastepnyZywy(przedSzeryfem);
        }

        if (doSzeryfa.size() <=3)
            return false;

        int raczejBandyci = 0;
        for (Gracz potencjalnyBandyta: doSzeryfa) {
            if (raczejBandyta(potencjalnyBandyta))
                raczejBandyci += 1;
        }
        return raczejBandyci * 3 >= doSzeryfa.size() * 2;
    }

    protected boolean raczejBandyta(Gracz gracz) {
        return widok.wrogowieSzeryfa().jestWrogiem(gracz) ||
                widok.zabojstwaBandytow().ileZabil(gracz) < widok.zabojstwaPomocnikow().ileZabil(gracz);
    }
}
