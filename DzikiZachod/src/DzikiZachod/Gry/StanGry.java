package DzikiZachod.Gry;

public enum StanGry {
    GraTrwa, BandyciWygrali, SzeryfWygral, Remis;
    public boolean koniecGry() {
        return this != GraTrwa;
    }
}
