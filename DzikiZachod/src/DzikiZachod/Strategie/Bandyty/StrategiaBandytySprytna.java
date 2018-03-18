package DzikiZachod.Strategie.Bandyty;

import DzikiZachod.Gry.Akcja;
import DzikiZachod.Gracze.Gracz;
import DzikiZachod.Gry.Obserwatorzy.Obserwator;

import java.util.Set;

/**
 * Created by pjotrekk on 25.05.17.
 */
public class StrategiaBandytySprytna extends StrategiaBandytyDomyslna implements Obserwator {

    private boolean bandytaZabityWTejTurze = false;

    @Override
    public Gracz wybierzOfiare(Set<Gracz> opcje) {
        if (opcje.contains(widok.kolejka().szeryf()))
            return widok.kolejka().szeryf();
        if (bandytaZabityWTejTurze)
            return super.wybierzOfiare(opcje);

        Gracz najslabszy = null;
        for (Gracz kandydat: opcje) {
            if (widok.bandyci().contains(kandydat) &&
                    (najslabszy == null || najslabszy.punktyZycia() > kandydat.punktyZycia()))
                najslabszy = kandydat;
        }
        if (najslabszy == null || najslabszy.punktyZycia() > gracz.liczbaKart(Akcja.STRZEL))
            return super.wybierzOfiare(opcje);

        if (najslabszy.punktyZycia() == 1)
            bandytaZabityWTejTurze = true;
        return najslabszy;
    }


    @Override
    protected void inicjalizuj() {
        widok.rejestrujObserwatora(this);
    }

    @Override
    public void postrzelenie(Gracz kto, Gracz kogo) {

    }

    @Override
    public void rozpoczecieTury() {
        bandytaZabityWTejTurze = false;
    }
}
