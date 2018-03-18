package DzikiZachod.Gry.Obserwatorzy;


import DzikiZachod.Gracze.Gracz;
import DzikiZachod.Gracze.TypGracza;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by pjotrekk on 27.05.17.
 */
public class WrogowieSzeryfa implements Obserwator {

    public final Set<Gracz> wrogowie = new HashSet<>();

    @Override
    public void postrzelenie(Gracz kto, Gracz kogo) {
        if (kogo.podajTyp() == TypGracza.Szeryf)
            wrogowie.add(kto);
    }

    @Override
    public void rozpoczecieTury() {

    }

    public Set<Gracz> podajWrogow() {
        return wrogowie;
    }

    public boolean jestWrogiem(Gracz gracz) {
        return wrogowie.contains(gracz);
    }
}
