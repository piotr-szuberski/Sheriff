package DzikiZachod.Strategie.Pomocnikow;


import DzikiZachod.Gracze.Gracz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by pjotrekk on 25.05.17.
 */
public class StrategiaPomocnikaSzeryfaZliczajaca extends StrategiaPomocnikaSzeryfa {

    @Override
    public Gracz wybierzOfiare(Set<Gracz> opcje) {
        List<Gracz> kandydaci = new ArrayList<>();
        for (Gracz kandydat: opcje) {
            if (widok.kolejka().szeryf() != kandydat && raczejBandyta(kandydat))
                kandydaci.add(kandydat);
        }
        Collections.shuffle(kandydaci);
        return kandydaci.isEmpty() ? null : kandydaci.get(0);
    }
}
