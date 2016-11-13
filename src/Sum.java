import lombok.Getter;

abstract class Sum<T extends Sum> {
    @Getter
    protected double verdi;

    Sum(double verdi) {
        this.verdi = verdi;
    }

    Sum(int heltall, int fraction) {
        if (heltall == 0 && fraction < 0) {
            verdi = Double.valueOf("-0." + Math.abs(fraction));
            return;
        }
        verdi = Double.parseDouble(heltall + "." + fraction);

    }

    abstract T create(double verdi);

    T pluss(T b) {
        return create(verdi + b.getVerdi());
    }

    T minus(T b) {
        return create(verdi - b.getVerdi());
    }

    T delPaa(long antallDeltakarar) {
        return create(verdi / antallDeltakarar);
    }

    T ganger(int i) {
        return create(verdi*i);
    }

    @Override
    public String toString() {
        return String.format("%.3f", verdi) +" " +getSymbol();
    }

    abstract String getSymbol();

    String getOriginalAndNOK() {
        return toString() +" ( " +convertToNOK() +")";
    }

    private NOK convertToNOK() {
        double nokVerdi = verdi * getKurs1SaannTilNOK();
        return new NOK(nokVerdi);
    }

    abstract double getKurs1SaannTilNOK();
}
