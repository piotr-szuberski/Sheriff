package DzikiZachod.Gry;


import DzikiZachod.Gracze.Gracz;

import java.util.*;

public class Kolejka implements WidokKolejki {

    private List<Gracz> gracze = new ArrayList<>();
    private Set<Gracz> bandyci = new HashSet<>();
    private Gracz szeryf;
    private Gracz obecnyGracz;

    public Kolejka(List<Gracz> gracze) {
        this.gracze = new ArrayList<>(gracze);
        Collections.shuffle(this.gracze);

        for (int i = 0; i < this.gracze.size(); i++) {
            Gracz g = this.gracze.get(i);
            g.ustawIndeks(i);

            switch (g.podajTyp()) {
                case Szeryf:
                    if (szeryf != null)
                        throw new RuntimeException("wiecej niz 1 szeryf");
                    this.szeryf = g;
                    break;
                case Bandyta:
                    bandyci.add(g);
                    break;
            }
        }
        if (szeryf == null)
            throw new RuntimeException("nie ma wrogowieSzeryfa");
        this.obecnyGracz = szeryf;

    }

    public Set<Gracz> bandyci() {
        return bandyci;
    }

    public List<Gracz> gracze() {
        return gracze;
    }

    public Gracz szeryf() {
        return szeryf;
    }

    public Gracz obecnyGracz() {
        return obecnyGracz;
    }

    public void ustawKolejnegoGracza() {
        this.obecnyGracz = nastepny(obecnyGracz);
    }


    public Gracz nastepny(Gracz ten) {
        return gracze.get((ten.wezIndeks() + 1) % this.gracze.size());
    }

    public Gracz poprzedni(Gracz ten) {
        return gracze.get((ten.wezIndeks() - 1 + this.gracze.size()) % this.gracze.size());
    }

    @Override
    public Gracz nastepnyZywy(Gracz ten) {
        int idx;
        Gracz gracz = ten;
        do {
            idx = (gracz.wezIndeks() + 1) % this.gracze.size();
            gracz = gracze.get(idx);
            if (gracz == ten) return ten;
        } while (gracz.punktyZycia() == 0);
        return gracz;
    }

    @Override
    public Gracz poprzedniZywy(Gracz ten) {
        int idx;
        Gracz gracz = ten;
        do {
            idx = (gracz.wezIndeks() - 1 + this.gracze.size()) % this.gracze.size();
            gracz = gracze.get(idx);
            if (gracz == ten) return ten;
        } while (gracz.punktyZycia() == 0);
        return gracz;
    }

    public Set<Gracz> graczeWZasiegu(Gracz srodkowy, int zasieg, boolean wlacznieZ) {
        Set<Gracz> wZasiegu = new HashSet<>();

        Gracz kolejny = srodkowy;
        for (int i = 0; i < zasieg; i++) {
            kolejny = nastepnyZywy(kolejny);
            wZasiegu.add(kolejny);
        }

        kolejny = srodkowy;
        for (int i = 0; i < zasieg; i++) {
            kolejny = poprzedniZywy(kolejny);
            wZasiegu.add(kolejny);
        }

        if (wlacznieZ)
            wZasiegu.add(srodkowy);
        else
            wZasiegu.remove(srodkowy);
        return wZasiegu;
    }
}
