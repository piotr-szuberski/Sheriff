package DzikiZachod.Gry;

import DzikiZachod.Drukarka;
import DzikiZachod.Gracze.*;
import DzikiZachod.Gry.Obserwatorzy.Obserwator;
import DzikiZachod.Gry.Obserwatorzy.WrogowieSzeryfa;
import DzikiZachod.Gry.Obserwatorzy.Zabojstwa;

import java.util.*;

/**
 * Created by pjotrekk on 26.05.17.
 */
public class Gra implements Widok, WidokBandyty {

    private int zakonczoneTury = 0;
    private int maxTur = 42;

    private boolean jestDynamit = false;
    private StanGry stanGry = StanGry.GraTrwa;
    private PulaAkcji pulaAkcji;
    private Kolejka kolejka;

    private Random k6 = new Random();

    private ArrayList<Obserwator> obserwujacy = new ArrayList<>();
    private WrogowieSzeryfa wrogowieSzeryfa;
    private Zabojstwa zabojstwaPomocnikow;
    private Zabojstwa zabojstwaBandytow;

    private Drukarka drukarka = new Drukarka();

    public void rozgrywka(List<Gracz> gracze, PulaAkcji pulaAkcji) {
        this.wrogowieSzeryfa = new WrogowieSzeryfa();
        this.zabojstwaBandytow = new Zabojstwa(TypGracza.Bandyta);
        this.zabojstwaPomocnikow = new Zabojstwa(TypGracza.Pomocnik);
        rejestrujObserwatora(wrogowieSzeryfa);
        rejestrujObserwatora(zabojstwaBandytow);
        rejestrujObserwatora(zabojstwaPomocnikow);

        this.kolejka = new Kolejka(gracze);
        this.pulaAkcji = pulaAkcji;
        this.pulaAkcji.tasuj();
        for (Gracz g : this.kolejka.gracze()) {
            g.przekazGre(this);
        }

        drukarka.poczatek(kolejka);
        while (!stanGry.koniecGry()) {
            drukarka.tura(zakonczoneTury + 1);
            wykonajTure();
        }
        drukarka.koniec(stanGry);
    }

    private void wykonajTure() {
        for (Obserwator obserwator: obserwujacy)
            obserwator.rozpoczecieTury();

        Gracz poczatkowyGracz = kolejka.obecnyGracz();
        do {
            wykonajRuch(kolejka.obecnyGracz());
            if (stanGry.koniecGry())
                return;
            kolejka.ustawKolejnegoGracza();
        } while (poczatkowyGracz != kolejka.obecnyGracz());

        zakonczoneTury += 1;
        if (zakonczoneTury >= maxTur)
            this.stanGry = StanGry.Remis;
    }

    private void wykonajRuch(Gracz gracz) {

        drukarka.naglowekGracza(gracz);
        if (gracz.punktyZycia() == 0)
            return;

        while (gracz.liczbaWszystkichKart() < 5)
            gracz.dodajKarte(pulaAkcji.dobierz());
        drukarka.listaAkcji(gracz);

        if (jestDynamit && (k6.nextInt(6)+1 == 1)) {
            gracz.wybuch();
            aktualizujStanGry(gracz);
            drukarka.dynamitWybuchl(gracz);
            if (stanGry.koniecGry() || gracz.punktyZycia() <= 0)
                return;
        }
        else if (jestDynamit) {
            drukarka.dynamitPrzechodzi();
        }

        int karty = gracz.liczbaKart(Akcja.ULECZ);
        for (int i = 0; i < karty; i++) {
            if (!sprobujUleczyc(gracz))
                break;
        }

        karty = gracz.liczbaKart(Akcja.ZASIEG_PLUS_DWA);
        for (int i = 0; i < karty; i++) {
            if (!sprobujZwiekszycZasieg(gracz, Akcja.ZASIEG_PLUS_DWA, 2))
                break;
        }

        karty = gracz.liczbaKart(Akcja.ZASIEG_PLUS_JEDEN);
        for (int i = 0; i < karty; i++) {
            if (!sprobujZwiekszycZasieg(gracz, Akcja.ZASIEG_PLUS_JEDEN, 1))
                break;
        }

        karty = gracz.liczbaKart(Akcja.STRZEL);
        for (int i = 0; i < karty; i++) {
            if (!sprobujStrzelic(gracz))
                break;
            else if (stanGry.koniecGry())
                return;
        }

        karty = gracz.liczbaKart(Akcja.DYNAMIT);
        for (int i = 0; i < karty; i++) {
            if (!sprobujOdpalicDynamit(gracz))
                break;
        }

        drukarka.pustaLinia();
        drukarka.listaGraczy(kolejka);
    }


    private boolean sprobujUleczyc(Gracz gracz) {
        Set<Gracz> moznaLeczyc = this.moznaLeczyc(gracz);
        Gracz leczony = gracz.podajStrategie().wybierzLeczonego(moznaLeczyc);
        if (leczony == null)
            return false;

        if (!moznaLeczyc.contains(leczony))
            throw new RuntimeException("nie mozna leczyc danego gracza!");
        leczony.ulecz();
        gracz.usunKarte(Akcja.ULECZ);
        pulaAkcji.odrzuc(Akcja.ULECZ);
        drukarka.ulecz(gracz, leczony);
        return true;
    }

    private boolean sprobujZwiekszycZasieg(Gracz gracz, Akcja wzrostZasiegu, int zmianaZasiegu) {
        if (gracz.podajStrategie().czyZwiekszycZasieg(zmianaZasiegu)) {
            gracz.zwiekszZasieg(zmianaZasiegu);
            gracz.usunKarte(wzrostZasiegu);
            pulaAkcji.odrzuc(wzrostZasiegu);
            drukarka.wykonaj_akcje(wzrostZasiegu);
            return true;
        }
        return false;
    }

    private boolean sprobujStrzelic(Gracz gracz) {
        Set<Gracz> moznaStrzelac = this.moznaStrzelac(gracz);
        Gracz postrzelony = gracz.podajStrategie().wybierzOfiare(moznaStrzelac);
        if (postrzelony == null)
            return false;

        if (!moznaStrzelac.contains(postrzelony))
            throw new RuntimeException("nie mozna postrzelic danego gracza!");
        postrzelony.postrzel();
        aktualizujStanGry(postrzelony);
        gracz.usunKarte(Akcja.STRZEL);
        pulaAkcji.odrzuc(Akcja.STRZEL);

        if (!stanGry.koniecGry()) {
            for (Obserwator obserwator: obserwujacy)
                obserwator.postrzelenie(gracz, postrzelony);
        }
        drukarka.strzal(postrzelony);
        return true;
    }


    private boolean sprobujOdpalicDynamit(Gracz gracz) {
        if (gracz.podajStrategie().czyRzucicDynamit()) {
            this.jestDynamit = true;
            gracz.usunKarte(Akcja.DYNAMIT);
            pulaAkcji.odrzuc(Akcja.DYNAMIT);
            drukarka.wykonaj_akcje(Akcja.DYNAMIT);
            return true;
        }
        return false;
    }


    private Set<Gracz> moznaLeczyc(Gracz leczacy) {
        Set<Gracz> wZasiegu = kolejka.graczeWZasiegu(leczacy, 1, true);
        Set<Gracz> ranni = new HashSet<>();

        for (Gracz gracz: wZasiegu) {
            if (gracz.punktyZycia() < gracz.maxZycia())
                ranni.add(gracz);
        }
        return ranni;
    }

    private Set<Gracz> moznaStrzelac(Gracz strzelajacy) {
        return kolejka.graczeWZasiegu(strzelajacy, strzelajacy.zasieg(), false);
    }

    private void aktualizujStanGry(Gracz ranny) {
        if (ranny.punktyZycia() <= 0) {
            for(Akcja oddane: ranny.oddajKarty()) {
                pulaAkcji.odrzuc(oddane);
            }
        }

        if (kolejka.szeryf().punktyZycia() <= 0)
            stanGry = StanGry.BandyciWygrali;

        boolean ktorysZyje = false;
        for (Gracz bandyta: kolejka.bandyci()) {
            if (bandyta.punktyZycia() > 0) {
                ktorysZyje = true;
                break;
            }
        }

        if (!ktorysZyje)
            stanGry = StanGry.SzeryfWygral;
    }

    @Override
    public void rejestrujObserwatora(Obserwator obserwator) {
        this.obserwujacy.add(obserwator);
    }

    @Override
    public Kolejka kolejka() {
        return kolejka;
    }

    @Override
    public Set<Gracz> bandyci() {
        return kolejka.bandyci();
    }

    @Override
    public Zabojstwa zabojstwaBandytow() {
        return zabojstwaBandytow;
    }

    @Override
    public Zabojstwa zabojstwaPomocnikow() {
        return zabojstwaPomocnikow;
    }

    @Override
    public WrogowieSzeryfa wrogowieSzeryfa() {
        return wrogowieSzeryfa;
    }


    public static void main(String[] args) {
        List<Gracz> gracze = new ArrayList<>();
        gracze.add(new Szeryf());
        for (int i = 0; i < 15; i++) gracze.add(new PomocnikSzeryfa());
        for (int i = 0; i < 10; i++) gracze.add(new Bandyta());

        PulaAkcji pulaAkcji = new PulaAkcji();
        pulaAkcji.dodaj(Akcja.ULECZ, 100);
        pulaAkcji.dodaj(Akcja.STRZEL, 60);
        pulaAkcji.dodaj(Akcja.ZASIEG_PLUS_JEDEN, 3);
        pulaAkcji.dodaj(Akcja.ZASIEG_PLUS_DWA, 1);
        pulaAkcji.dodaj(Akcja.DYNAMIT, 1);

        Gra gra = new Gra();
        gra.rozgrywka(gracze, pulaAkcji);
    }

}
