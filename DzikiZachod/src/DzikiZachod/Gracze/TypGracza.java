package DzikiZachod.Gracze;

public enum TypGracza {
    Bandyta, Szeryf, Pomocnik;
    public String toString() {
        switch (this) {
            case Bandyta:
                return "Bandyta";
            case Pomocnik:
                return "Pomocnik Szeryfa";
            case Szeryf:
                return "Szeryf";
            default:
                throw new RuntimeException("niezdefiniowany typ gracza");
        }
    }
}
