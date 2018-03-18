package DzikiZachod.Strategie.Bandyty;


import DzikiZachod.Gracze.Gracz;

import java.util.Set;

/**
 * Created by pjotrekk on 25.05.17.
 */
public class StrategiaBandytyCierpliwa extends StrategiaBandyty  {

    @Override
    public Gracz wybierzOfiare(Set<Gracz> opcje) {
        if (opcje.contains(widok.kolejka().szeryf()))
            return widok.kolejka().szeryf();
        else
            return null;
    }

}
