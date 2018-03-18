package DzikiZachod.Strategie.Bandyty;


import DzikiZachod.Gracze.Gracz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by pjotrekk on 25.05.17.
 */
public class StrategiaBandytyDomyslna extends StrategiaBandyty {

    @Override
    public Gracz wybierzOfiare(Set<Gracz> opcje) {
        Gracz szeryf = widok.kolejka().szeryf();
        if (opcje.contains(szeryf))
            return szeryf;

        List<Gracz> nastepni = new ArrayList<>();
        Gracz nastepny = widok.kolejka().nastepnyZywy(gracz);
        while (nastepny != szeryf) {
            nastepni.add(nastepny);
            nastepny = widok.kolejka().nastepnyZywy(nastepny);
        }
        List<Gracz> poprzednicy = new ArrayList<>();
        Gracz poprzedni = widok.kolejka().poprzedniZywy(gracz);
        while (poprzedni != szeryf) {
            poprzednicy.add(poprzedni);
            poprzedni = widok.kolejka().poprzedniZywy(poprzedni);
        }

        if (nastepni.size() == poprzednicy.size()) {
            ArrayList<Gracz> wszyscy = new ArrayList<>();
            for (Gracz kandydat: nastepni) {
                if (opcje.contains(kandydat) && !widok.bandyci().contains(kandydat))
                    wszyscy.add(kandydat);
            }
            for (Gracz kandydat: poprzednicy) {
                if (opcje.contains(kandydat) && !widok.bandyci().contains(kandydat))
                    wszyscy.add(kandydat);
            }
            Collections.shuffle(wszyscy);
            return wszyscy.isEmpty() ? null : wszyscy.get(0);
        }

        boolean wiecejNast = nastepni.size() > poprzednicy.size();
        List<Gracz> wieksze = wiecejNast ? nastepni : poprzednicy;
        List<Gracz> mniejsze = wiecejNast ? poprzednicy : nastepni;

        List<Gracz> kandydaci = new ArrayList<>();
        for (Gracz kandydat: mniejsze) {
            if (opcje.contains(kandydat) && !widok.bandyci().contains(kandydat))
                kandydaci.add(kandydat);
        }
        if (kandydaci.isEmpty()) {
            for (Gracz kandydat: wieksze) {
                if (opcje.contains(kandydat) && !widok.bandyci().contains(kandydat))
                    kandydaci.add(kandydat);
            }
        }
        Collections.shuffle(kandydaci);
        return kandydaci.isEmpty() ? null : kandydaci.get(0);
    }

}
