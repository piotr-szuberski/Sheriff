package DzikiZachod.Strategie.Bandyty;

import DzikiZachod.Gracze.Gracz;
import DzikiZachod.Gry.Gra;
import DzikiZachod.Gry.WidokBandyty;
import DzikiZachod.Strategie.Strategia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pjotrekk on 26.05.17.
 */
public abstract class StrategiaBandyty extends Strategia {
    protected WidokBandyty widok;

    @Override
    public final void przyjmijGre(Gra gra) {
        this.widok = gra;
        inicjalizuj(); //przyjmij gre = Metoda szablonowa
    }

    protected void inicjalizuj() {

    }

    @Override
    public boolean czyRzucicDynamit() {
        Gracz nastepny = widok.kolejka().nastepnyZywy(gracz);
        Gracz szeryf = widok.kolejka().szeryf();
        return nastepny == szeryf || widok.kolejka().nastepnyZywy(nastepny) == szeryf;
    }
}
