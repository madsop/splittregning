import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Maaltid {

    @Getter
    private List<Rett> retter;

    private Deltakar betaler;
    @Getter
    private Set<Betaling> betalingar;

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

    Maaltid(Rett... rett) {
        retter = new ArrayList<>();
        betalingar = new HashSet<>();
        Arrays.stream(rett).forEach(retter::add);
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

    private boolean deltokIkkePaaDetteMaaltidet(Deltakar deltakar) {
        return retter.stream().noneMatch(x -> deltakar.equals(x.getDeltakar()));
    }

    Sum getBruktFor(Deltakar deltakar) {
        return retter.stream()
                .filter(x -> deltakar.equals(x.getDeltakar()))
                .map(Rett::getBeloep)
                .reduce(new Sum(0,0), Sum::pluss)
                .pluss(getSumPerPersonForFellesRett());
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

    private Sum getSumPerPersonForFellesRett() {
        return retter.stream()
                .filter(x -> x.getDeltakar() == null)
                .map(Rett::getBeloep)
                .reduce(new Sum(0, 0), Sum::pluss)
                .delPaa(getAntallDeltakarar());
    }

    private long getAntallDeltakarar() {
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
        return getSumPerPersonForFellesRett();
    }
}
