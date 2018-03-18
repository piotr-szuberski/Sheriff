package DzikiZachod.Gracze;

import DzikiZachod.Gry.Gra;
import DzikiZachod.Strategie.Pomocnikow.StrategiaPomocnikaSzeryfaDomyslna;
import DzikiZachod.Strategie.Strategia;
import DzikiZachod.Strategie.Pomocnikow.StrategiaPomocnikaSzeryfa;

import java.util.Random;


/**
 * Created by pjotrekk on 26.05.17.
 */
public class PomocnikSzeryfa extends Gracz {
    private final StrategiaPomocnikaSzeryfa strategia;

    public PomocnikSzeryfa(StrategiaPomocnikaSzeryfa s) {
        super(new Random().nextInt(2)+3);
        this.strategia = s;
    }

    public PomocnikSzeryfa() {
        this(new StrategiaPomocnikaSzeryfaDomyslna());
    }


    @Override
    public TypGracza podajTyp() {
        return TypGracza.Pomocnik;
    }

    @Override
    public Strategia podajStrategie() {
        return strategia;
    }

    @Override
    public void przekazGre(Gra gra) {
        strategia.przyjmijGre(gra);
        strategia.przyjmijGracza(this);
    }

}
