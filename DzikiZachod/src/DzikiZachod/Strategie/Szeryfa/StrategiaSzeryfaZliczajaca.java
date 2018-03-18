package DzikiZachod.Strategie.Szeryfa;


import DzikiZachod.Gracze.Gracz;

import java.util.*;

/**
 * Created by pjotrekk on 25.05.17.
 */
public class StrategiaSzeryfaZliczajaca extends StrategiaSzeryfa {

    @Override
    public Gracz wybierzOfiare(Set<Gracz> opcje) {
        List<Gracz> kandydaci = new ArrayList<>();
        for(Gracz gracz: opcje) {
            if (raczejBandyta(gracz))
                kandydaci.add(gracz);
        }
        Collections.shuffle(kandydaci);
        return kandydaci.isEmpty() ? null : kandydaci.get(0);
    }
}
