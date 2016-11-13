import lombok.Getter;

@Getter
class Rett {
    private final String namn;
    private final Sum beloep;
    private final Deltakar deltakar;

    Rett(int heltall, int fraction, Deltakar deltakar, String namn) {
        this(new Sum(heltall, fraction), deltakar, namn);
    }

    Rett(Sum beloep, Deltakar deltakar, String namn) {
        this.namn = namn;
        this.beloep = beloep;
        this.deltakar = deltakar;
    }

    Rett(String namn, Sum sum, Deltakar deltakar) {
        this(sum, deltakar, namn);
    }

    @Override
    public String toString() {
        return namn + "(" + deltakar +", " +beloep +")";
    }
}