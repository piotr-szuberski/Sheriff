package DzikiZachod.Gry;


import DzikiZachod.Gracze.Gracz;

import java.util.List;

/**
 * Created by pjotrekk on 27.05.17.
 */
public interface WidokKolejki {
    Gracz szeryf();
    List<Gracz> gracze();
    Gracz nastepnyZywy(Gracz gracz);
    Gracz poprzedniZywy(Gracz gracz);
}
