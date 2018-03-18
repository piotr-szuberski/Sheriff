package DzikiZachod.Gracze;

import DzikiZachod.Gry.Akcja;
import DzikiZachod.Gry.Gra;
import DzikiZachod.Strategie.Strategia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pjotrekk on 26.05.17.
 */
public abstract class Gracz {

    private Map<Akcja, Integer> akcje = new HashMap<>();
    private int indeks;
    private int zycie;
    private int maxZycie;
    private int zasieg;

    public Gracz(int zycie) {
        this.zycie = zycie;
        this.maxZycie = zycie;
        this.zasieg = 1;
        for (Akcja a: Akcja.values()) {
            akcje.put(a, 0);
        }
    }

    public int maxZycia() {
        return maxZycie;
    }

    public int punktyZycia() {
        return zycie;
    }

    public void ustawIndeks(int i) {
        this.indeks = i;
    }

    public void dodajKarte(Akcja akcja) {
        akcje.put(akcja, akcje.get(akcja) + 1);
    }

    public int liczbaKart(Akcja typ) {
        return this.akcje.get(typ);
    }

    public int wezIndeks() {
        return indeks;
    }

    public int zasieg() {
        return zasieg;
    }

    public int liczbaWszystkichKart() {
        int wynik = 0;
        for (Integer liczba: akcje.values())
            wynik += liczba;
        return wynik;
    }

    public void usunKarte(Akcja akcja) {
        akcje.put(akcja, akcje.get(akcja) - 1);
        if (akcje.get(akcja) < 0)
            throw new RuntimeException("liczba kart mniejsza od 0");
    }


    public void ulecz() {
        if (zycie == maxZycie)
            throw new RuntimeException("nie mozna leczyc w pelni zywego");
        zycie = zycie + 1;
    }

    public void zwiekszZasieg(int wzrostZasiegu) {
        this.zasieg = wzrostZasiegu;
    }

    public void postrzel() {
        if(this.zycie == 0)
            throw new RuntimeException("nie mozna strzelac do zabitego");
        this.zycie = zycie - 1;
    }

    public void wybuch() {
        if(this.zycie == 0)
            throw new RuntimeException("nie mozna strzelac do zabitego");
        this.zycie = Math.max(0, this.zycie - 3);
    }

    public List<Akcja> oddajKarty() {
        List<Akcja> wszystkie = new ArrayList<>();
        for (Akcja akcja: Akcja.values()) {
            for (int i = 0; i < akcje.get(akcja); i++) {
                wszystkie.add(akcja);
            }
        }
        for (Akcja a: Akcja.values()) {
            akcje.put(a, 0);
        }
        return wszystkie;
    }


    public abstract TypGracza podajTyp();

    public abstract Strategia podajStrategie();

    public abstract void przekazGre(Gra gra);

    public List<Akcja> listaAkcji() {
        List<Akcja> lista = new ArrayList<>();
        for (Akcja akcja: Akcja.values()) {
            for (int i = 0; i < liczbaKart(akcja); i++)
                lista.add(akcja);
        }
        return lista;
    }
}
