package DzikiZachod.Strategie.Pomocnikow;


import DzikiZachod.Gracze.Gracz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by pjotrekk on 25.05.17.
 */
public class StrategiaPomocnikaSzeryfaDomyslna extends StrategiaPomocnikaSzeryfa {

    @Override
    public Gracz wybierzOfiare(Set<Gracz> opcje) {
        List<Gracz> pozaSzeryfem = new ArrayList<>(opcje);
        pozaSzeryfem.remove(widok.kolejka().szeryf());
        Collections.shuffle(pozaSzeryfem);
        return pozaSzeryfem.isEmpty() ? null : pozaSzeryfem.get(0);
    }
}
