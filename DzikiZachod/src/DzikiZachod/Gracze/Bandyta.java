package DzikiZachod.Gracze;

import DzikiZachod.Gry.Gra;
import DzikiZachod.Strategie.Bandyty.StrategiaBandytyDomyslna;
import DzikiZachod.Strategie.Strategia;
import DzikiZachod.Strategie.Bandyty.StrategiaBandyty;

import java.util.Random;

/**
 * Created by pjotrekk on 26.05.17.
 */
public class Bandyta extends Gracz {

    private final StrategiaBandyty strategia;

    public Bandyta(StrategiaBandyty s) {
        super(new Random().nextInt(2)+3);
        this.strategia = s;
    }

    public Bandyta() {
        this(new StrategiaBandytyDomyslna());
    }

    @Override
    public TypGracza podajTyp() {
        return TypGracza.Bandyta;
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
