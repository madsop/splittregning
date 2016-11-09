import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class Sum {
    static final Sum empty = new Sum(0, 0);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sum sum = (Sum) o;

        if (heltall != sum.heltall) return false;
        return fraction == sum.fraction || this.minus(sum).equals(Sum.empty);
    }

    @Override
    public int hashCode() {
        int result = heltall;
        result = 31 * result + fraction;
        return result;
    }

    @Getter
    private final int heltall;
    private final int fraction;

    private int getPresisjonsnivaaOere() {
        return 100;
    }

    Sum pluss(Sum b) {
        int newHeltall = this.heltall + b.getHeltall();
        int newFraction = this.heltall < 0 ? this.fraction - b.getFraction() :
                this.fraction + b.getFraction();
        while (newFraction >= getPresisjonsnivaaOere()) {
            newHeltall++;
            newFraction -= getPresisjonsnivaaOere();
        }
        while (newFraction < 0) {
            if (newHeltall<0) {
                newHeltall++;
            }
            else {
                newHeltall--;
            }

            newFraction += getPresisjonsnivaaOere();
        }
        return new Sum(newHeltall, newFraction);
    }

    Sum minus(Sum b) {
        int newHeltall = this.heltall - b.getHeltall();

        int thisFraction = this.fraction;
        int bFraction = b.getFraction();
        int lengthDiff = String.valueOf(bFraction).length() - String.valueOf(thisFraction).length();
        if (lengthDiff == 1) {
            thisFraction *= 10;
        }
        if (lengthDiff == -1) {
            bFraction *= 10;
        }

        int newFraction = thisFraction - bFraction;
        while (newFraction <= -getPresisjonsnivaaOere() || (newFraction < 0 && newHeltall >= 0)) {
            newHeltall--;
            newFraction += getPresisjonsnivaaOere();
        }
        newFraction = Math.abs(newFraction);
        return new Sum(newHeltall, newFraction);
    }

    private int getFraction() {
        return fraction;
    }

    Sum delPaa(long antallDeltakarar) {
        int heltall = (int) (this.heltall / antallDeltakarar);
        int gjenstaaendeHeltall = (int) (this.heltall % antallDeltakarar);
        int newFractionTotal = (gjenstaaendeHeltall * getPresisjonsnivaaOere()) + this.fraction;
        int newFractionReturn = (int) (newFractionTotal/antallDeltakarar);
        int toFractionDiff = newFractionTotal % newFractionReturn;
        return new Sum(heltall, newFractionTotal);
    }

    Sum ganger(int i) {
        return new Sum(heltall*i, fraction);
    }

    @Override
    public String toString() {
        return heltall + "." + fraction;
    }
}
