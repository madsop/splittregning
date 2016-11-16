class Euro extends Sum<Euro> {
    static final Euro empty = new Euro(0, 0);

    Euro(double verdi) {
        super(verdi);
    }

    Euro(int heltall, int fraction) {
        super(heltall, fraction);
    }


    @Override
    Euro create(double verdi) {
        return new Euro(verdi);
    }

    @Override
    String getSymbol() {
        return "€";
    }

    @Override
    double getKurs1SaannTilNOK() {
        return 9.33;
    }

    static Euro createNullSum() {
        return new Euro(0, 0);
    }
}
