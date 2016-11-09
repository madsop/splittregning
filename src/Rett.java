import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
class Rett {
    private final Sum beloep;
    private final Deltakar deltakar;

    Rett(int heltall, int fraction, Deltakar deltakar) {
        this(new Sum(heltall, fraction), deltakar);
    }

    Rett(Sum beloep, Deltakar deltakar) {
        this.beloep = beloep;
        this.deltakar = deltakar;
    }
}