package DzikiZachod.Gry;


import DzikiZachod.Gry.Obserwatorzy.Obserwator;
import DzikiZachod.Gry.Obserwatorzy.WrogowieSzeryfa;
import DzikiZachod.Gry.Obserwatorzy.Zabojstwa;

/**
 * Created by pjotrekk on 26.05.17.
 */
public interface Widok {
    Zabojstwa zabojstwaBandytow();
    Zabojstwa zabojstwaPomocnikow();
    WrogowieSzeryfa wrogowieSzeryfa();
    WidokKolejki kolejka();
    void rejestrujObserwatora(Obserwator obserwator);
}
