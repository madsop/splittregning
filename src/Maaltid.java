import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Maaltid {
    @Getter
    private String namn;

    @Getter
    private List<Rett> retter;

    private Deltakar betaler;
    @Getter
    private Set<Betaling> betalingar;

    Maaltid(String namn, Rett... rettar) {
        this(rettar);
        this.namn = namn;
    }

    Maaltid(Rett... rett) {
        retter = new ArrayList<>();
        betalingar = new HashSet<>();
        Arrays.stream(rett).forEach(retter::add);
    }

    @Override
    public String toString() {
        return namn + " (" + getSum() + ")";
    }

    void addBetaling(Deltakar deltakar, Sum sum) {
        if (betaler != null) {
            throw new RuntimeException("Motstridande data");
        }

        betalingar.add(new Betaling(deltakar, sum));
    }

    void setBetaler(Deltakar deltakar) {
        this.betaler = deltakar;
        betalingar.add(new Betaling(deltakar, getSum()));
    }

    Sum getUtestaaende(Deltakar deltakar) {
        if (deltokIkkePaaDetteMaaltidet(deltakar)) {
            return Sum.empty;
        }
        Sum betalt = betalingar.stream().filter(betaling -> deltakar.equals(betaling.getDeltakar()))
                .map(Betaling::getSum).reduce(new Sum(0, 0), Sum::pluss);
        Sum skalBetaleFor = getBruktFor(deltakar);
        return betalt.minus(skalBetaleFor).ganger(-1);

    }

    boolean deltokIkkePaaDetteMaaltidet(Deltakar deltakar) {
        return retter.stream().noneMatch(x -> deltakar.equals(x.getDeltakar()));
    }

    Sum getBruktFor(Deltakar deltakar) {
        return retter.stream()
                .filter(x -> deltakar.equals(x.getDeltakar()))
                .map(Rett::getBeloep)
                .reduce(new Sum(0,0), Sum::pluss)
                .pluss(getSumPerPersonForFellesRett(deltakar));
    }

    Sum getSum() {
        Set<Deltakar> deltakarSet = retter.stream().map(Rett::getDeltakar)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    //    Sum reduce = deltakarSet.stream().map(this::getUtestaaende).reduce(new Sum(0, 0), Sum::pluss);
      //  if (!reduce.equals(Sum.empty)) {
       //TODO fiks     System.out.println(reduce);
        //}
        return retter.stream().map(Rett::getBeloep).reduce(new Sum(0,0), Sum::pluss);
    }

    private Sum getSumPerPersonForFellesRett(Deltakar deltakar) {
        if (deltokIkkePaaDetteMaaltidet(deltakar)) {
            return Sum.empty;
        }
        return retter.stream()
                .filter(x -> x.getDeltakar() == null)
                .map(Rett::getBeloep)
                .reduce(new Sum(0, 0), Sum::pluss)
                .delPaa(getAntallDeltakarar());
    }

    long getAntallDeltakarar() {
        return getDeltakarar().count();
    }

    private Stream<Deltakar> getDeltakarar() {
        return retter.stream().map(Rett::getDeltakar).filter(Objects::nonNull).distinct();
    }

    void print() {
        System.out.println(
                "------------------------------------------- \n" +
                "Måltid for " + getDeltakarar().map(Deltakar::getNamn).collect(Collectors.joining(", ")) +"\n"
                + betalingar.stream().map(Betaling::toString).reduce("", (a, b) -> a + "\n" +b)
                        + "\n betalte totalsummen på " + getSum() + ".\n\n"
                + "Rettane var: \n"
                + retter.stream().map(Object::toString).collect(Collectors.joining("\n"))
                + "\n"
                + "------------------------------------------- \n"
        );
    }

    Sum getBetaltFelles(Deltakar deltakar) {
        if (deltokIkkePaaDetteMaaltidet(deltakar)) {
            return Sum.empty;
        }
        return getSumPerPersonForFellesRett(deltakar);
    }

    List<Rett> getRetterFor(Deltakar deltakar) {
        return retter.stream().filter(x -> deltakar.equals(x.getDeltakar())).collect(Collectors.toList());
    }

    List<Rett> getRetterFelles(Deltakar deltakar) {
        if (deltokIkkePaaDetteMaaltidet(deltakar)) {
            return Collections.emptyList();
        }
        return retter.stream().filter(x -> x.getDeltakar() == null).collect(Collectors.toList());
    }
}
