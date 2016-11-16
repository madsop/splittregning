class NOK extends Sum<NOK> {

    NOK(int heltall, int fraction) {
        super(heltall, fraction);
    }

    NOK(double verdi) {
        super(verdi);
    }

    @Override
    NOK create(double verdi) {
        return new NOK(verdi);
    }

    @Override
    String getSymbol() {
        return "NOK";
    }

    @Override
    double getKurs1SaannTilNOK() {
        return 1;
    }

    static NOK createNullSum() {
        return new NOK(0, 0);
    }
}
