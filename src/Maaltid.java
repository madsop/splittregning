import javaslang.collection.HashSet;
import javaslang.collection.List;
import javaslang.collection.Set;
import lombok.Getter;

import java.util.Objects;

class Maaltid {

    @Getter
    private String namn;

    @Getter
    private List<Rett> retter;

    private Deltakar betaler;

    @Getter
    private Set<Betaling> betalingar;

    Maaltid(String namn, Rett... rettar) {
        this.namn = namn;
        retter = List.of(rettar);
        betalingar = HashSet.empty();
    }

    @Override
    public String toString() {
        return namn + " (" + getSum() + ")";
    }

    void addBetaling(Deltakar deltakar, Sum sum) {
        if (betaler != null) {
            throw new RuntimeException("Motstridande data");
        }

        betalingar = betalingar.add(new Betaling(deltakar, sum));
    }

    void setBetaler(Deltakar deltakar) {
        this.betaler = deltakar;
        betalingar = betalingar.add(new Betaling(deltakar, getSum()));
    }

    Sum getUtestaaende(Deltakar deltakar) {
        if (deltokIkkePaaDetteMaaltidet(deltakar)) {
            return Euro.empty;
        }
        Sum betalt = betalingar.filter(betaling -> deltakar.equals(betaling.getDeltakar()))
                .map(Betaling::getSum).fold(new Euro(0, 0), Sum::pluss);
        Sum skalBetaleFor = getBruktFor(deltakar);
        return betalt.minus(skalBetaleFor).ganger(-1);

    }

    boolean deltokIkkePaaDetteMaaltidet(Deltakar deltakar) {
        return !retter.exists(x -> x.harDeltakar(deltakar));
    }

    Sum getBruktFor(Deltakar deltakar) {
        return retter
                .filter(x -> x.harDeltakar(deltakar))
                .map(Rett::getBeloepPerPerson)
                .fold(new Euro(0,0), Sum::pluss)
                .pluss(getSumPerPersonForFellesRett(deltakar));
    }

    Sum getSum() {
        return retter.map(Rett::getBeloep).fold(Sum.createNull(retter.iterator().next().getBeloep().getClass()), Sum::pluss);
    }

    private Sum getSumPerPersonForFellesRett(Deltakar deltakar) {
        if (deltokIkkePaaDetteMaaltidet(deltakar)) {
            return Euro.empty;
        }
        return retter
                .filter(Rett::isFellesRett)
                .map(Rett::getBeloep)
                .fold(new Euro(0, 0), Sum::pluss)
                .delPaa(getAntallDeltakarar());
    }

    long getAntallDeltakarar() {
        return getDeltakarar().size();
    }

    private List<Deltakar> getDeltakarar() {
        return retter.flatMap(Rett::getDeltakarar).filter(Objects::nonNull).distinct();
    }

    void print() {
        System.out.println(
                "------------------------------------------- \n" +
                "Måltid for " + getDeltakarar().map(Deltakar::getNamn).intersperse(", ").fold("", String::concat)
                +"\n"
                + betalingar.map(Betaling::toString).mkString("\n")
                        + "\n betalte totalsummen på " + getSum() + ".\n\n"
                + "Rettane var: \n"
                + retter.map(Object::toString).intersperse("\n").fold("", String::concat)
                + "\n"
                + "------------------------------------------- \n"
        );
    }

    Sum getBetaltFelles(Deltakar deltakar) {
        if (deltokIkkePaaDetteMaaltidet(deltakar)) {
            return Euro.empty;
        }
        return getSumPerPersonForFellesRett(deltakar);
    }

    List<Rett> getRetterFor(Deltakar deltakar) {
        return retter.filter(rett -> rett.harDeltakar(deltakar));
    }

    List<Rett> getRetterFelles(Deltakar deltakar) {
        if (deltokIkkePaaDetteMaaltidet(deltakar)) {
            return List.empty();
        }
        return retter.filter(Rett::isFellesRett);
    }
}
