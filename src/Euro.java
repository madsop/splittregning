class Euro extends Sum<Euro> {
    static final Euro empty = new Euro(0, 0);

    Euro(double verdi) {
        super(verdi);
    }

    Euro(int heltall, int fraction) {
        super(heltall, fraction);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Euro sum = (Euro) o;

        return Math.abs(sum.verdi - verdi) < 0.001;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(verdi);
        return (int) (temp ^ (temp >>> 32));
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
}
