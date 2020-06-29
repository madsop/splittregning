package maaltid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javaslang.collection.HashSet;
import javaslang.collection.List;
import javaslang.collection.Set;
import lombok.Getter;
import sum.Betaling;
import tur.Deltakar;
import sum.Euro;
import sum.Sum;

import java.util.Objects;

public class Maaltid {

    @Getter
    private String namn;

    @Getter
    private List<Rett> retter;

    @Getter
    private Set<Betaling> betalingar;

    @JsonCreator
    public Maaltid(@JsonProperty("namn") String namn, @JsonProperty("retter") Rett... rettar) {
        this.namn = namn;
        retter = List.of(rettar);
        betalingar = HashSet.empty();
    }

    @Override
    public String toString() {
        return namn + " (" + getSum() + ")";
    }

    public void addBetaling(Deltakar deltakar, Sum sum) {
        betalingar = betalingar.add(new Betaling(deltakar, sum));
    }

    public void setBetaler(Deltakar deltakar) {
        betalingar = betalingar.add(new Betaling(deltakar, getSum()));
    }

    public Sum getUtestaaende(Deltakar deltakar) {
        if (deltokIkkePaaDetteMaaltidet(deltakar)) {
            return Euro.empty;
        }
        Sum betalt = betalingar.filter(betaling -> deltakar.equals(betaling.getDeltakar()))
                .map(Betaling::getSum).fold(new Euro(0, 0), Sum::pluss);
        Sum skalBetaleFor = getBruktFor(deltakar);
        return betalt.minus(skalBetaleFor).ganger(-1);

    }

    public boolean deltokIkkePaaDetteMaaltidet(Deltakar deltakar) {
        return !retter.exists(x -> x.harDeltakar(deltakar));
    }

    public Sum getBruktFor(Deltakar deltakar) {
        return retter
                .filter(x -> x.harDeltakar(deltakar))
                .map(Rett::getBeloepPerPerson)
                .fold(new Euro(0,0), Sum::pluss)
                .pluss(getSumPerPersonForFellesRett(deltakar));
    }

    @JsonIgnore
    public Sum getSum() {
        return retter.map(Rett::getBeloep).fold(Sum.createNull(retter.iterator().next().getBeloep().figureValuta()), Sum::pluss);
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

    @JsonIgnore
    public long getAntallDeltakarar() {
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

    public Sum collectBetaltFelles(Deltakar deltakar) {
        if (deltokIkkePaaDetteMaaltidet(deltakar)) {
            return Euro.empty;
        }
        return getSumPerPersonForFellesRett(deltakar);
    }

    public List<Rett> listRetterFor(Deltakar deltakar) {
        return retter.filter(rett -> rett.harDeltakar(deltakar));
    }

    public List<Rett> listRetterFelles(Deltakar deltakar) {
        if (deltokIkkePaaDetteMaaltidet(deltakar)) {
            return List.empty();
        }
        return retter.filter(Rett::isFellesRett);
    }
}
