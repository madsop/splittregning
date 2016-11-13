import lombok.Getter;

class Sum {
    static final Sum empty = new Sum(0, 0);

    @Getter
    private double verdi;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sum sum = (Sum) o;

        return Math.abs(sum.verdi - verdi) < 0.001;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(verdi);
        return (int) (temp ^ (temp >>> 32));
    }

    Sum pluss(Sum b) {
        return new Sum(verdi + b.getVerdi());
    }

    Sum minus(Sum b) {
        return new Sum(verdi - b.getVerdi());
    }

    Sum delPaa(long antallDeltakarar) {
        return new Sum(verdi / antallDeltakarar);
    }

    Sum ganger(int i) {
        return new Sum(verdi*i);
    }

    @Override
    public String toString() {
        return String.format("%.2f", verdi);
    }
}
