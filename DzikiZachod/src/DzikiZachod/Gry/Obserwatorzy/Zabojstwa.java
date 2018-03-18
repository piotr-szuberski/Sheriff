package DzikiZachod.Gry.Obserwatorzy;

import DzikiZachod.Gracze.Gracz;
import DzikiZachod.Gracze.TypGracza;

import java.util.*;

/**
 * Created by pjotrekk on 27.05.17.
 */
public class Zabojstwa implements Obserwator {

    private final TypGracza typZabitego;
    public Map<Gracz, Integer> zabici = new HashMap<>();

    public Zabojstwa(TypGracza typZabitego) {
        this.typZabitego = typZabitego;
    }

    @Override
    public void postrzelenie(Gracz kto, Gracz kogo) {
        if (kogo.punktyZycia() > 0)
            return;
        if (kogo.podajTyp() == typZabitego) {
            if (!zabici.containsKey(kto))
                zabici.put(kto, 0);
            zabici.put(kto, zabici.get(kto) + 1);
        }
    }

    @Override
    public void rozpoczecieTury() {

    }

    public int ileZabil(Gracz gracz) {
        return zabici.containsKey(gracz) ? zabici.get(gracz) : 0;
    }
}
