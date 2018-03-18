package DzikiZachod.Gry.Obserwatorzy;


import DzikiZachod.Gracze.Gracz;

/**
 * Created by pjotrekk on 27.05.17.
 */
public interface Obserwator {
    public void postrzelenie(Gracz kto, Gracz kogo);
    public void rozpoczecieTury();
}
