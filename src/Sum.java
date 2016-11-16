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

    NOK convertToNOK() {
        return new NOK(this.verdi * getKurs1SaannTilNOK());
    }

    abstract double getKurs1SaannTilNOK();

    static <U extends Sum<U>> Sum createNull(Class<U> clazz){
        if (clazz.equals(NOK.class)) {
            return NOK.createNullSum();
        }
        else if (clazz.equals(Euro.class)) {
            return Euro.createNullSum();
        }
        return null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        T sum = (T) o;

        return Math.abs(sum.verdi - verdi) < 0.001;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(verdi);
        return (int) (temp ^ (temp >>> 32));
    }

}
