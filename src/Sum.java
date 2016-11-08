import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode
@RequiredArgsConstructor
class Sum {
    static final Sum empty = new Sum(0, 0);

    @Getter
    private final int heltall;
    private final int fraction;

    Sum pluss(Sum b) {
        int newHeltall = this.heltall + b.getHeltall();
        int newFraction = this.fraction + b.getFraction();
        while (newFraction >= 100) {
            newHeltall++;
            newFraction -= 100;
        }
        return new Sum(newHeltall, newFraction);
    }

    Sum minus(Sum b) {
        int newHeltall = this.heltall - b.getHeltall();
        int newFraction = this.fraction - b.getFraction();
        while (newFraction < 0) {
            newHeltall--;
            newFraction += 100;
        }
        return new Sum(newHeltall, newFraction);
    }

    private int getFraction() {
        return fraction;
    }

    Sum delPaa(long antallDeltakarar) {
        int heltall = (int) (this.heltall / antallDeltakarar);
        int gjenstaaendeHeltall = (int) (this.heltall % antallDeltakarar);
        int newFraction = (gjenstaaendeHeltall * 100) + this.fraction;
        newFraction = (int) (newFraction/antallDeltakarar);
        return new Sum(heltall, newFraction);
    }

    Sum ganger(int i) {
        return new Sum(heltall*i, fraction);
    }

    @Override
    public String toString() {
        return heltall + "." + fraction;
    }
}
