package DzikiZachod.Gracze;

import DzikiZachod.Gry.Gra;
import DzikiZachod.Strategie.Strategia;
import DzikiZachod.Strategie.Szeryfa.StrategiaSzeryfa;
import DzikiZachod.Strategie.Szeryfa.StrategiaSzeryfaDomyslna;

/**
 * Created by pjotrekk on 26.05.17.
 */
public class Szeryf extends Gracz {

    private final StrategiaSzeryfa strategia;

    public Szeryf(StrategiaSzeryfa s) {
        super(5);
        this.strategia = s;
    }

    public Szeryf() {
        this(new StrategiaSzeryfaDomyslna());
    }

    @Override
    public TypGracza podajTyp() {
        return TypGracza.Szeryf;
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
