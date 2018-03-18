package DzikiZachod.Strategie.Szeryfa;


import DzikiZachod.Gracze.Gracz;

import java.util.*;

/**
 * Created by pjotrekk on 25.05.17.
 */
public class StrategiaSzeryfaDomyslna extends StrategiaSzeryfa {

    @Override
    public Gracz wybierzOfiare(Set<Gracz> opcje) {
        List<Gracz> kandydaci = new ArrayList<>(widok.wrogowieSzeryfa().podajWrogow());
        kandydaci.retainAll(opcje);
        if (kandydaci.isEmpty())
            kandydaci = new ArrayList<>(opcje);

       Collections.shuffle(kandydaci);
       return kandydaci.get(0);
    }

}
