import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Tur {
    private List<Maaltid> maaltider;
    private List<Deltakar> deltakarar;

    Tur() {
        deltakarar = new ArrayList<>();
        maaltider = new ArrayList<>();
    }

    void addDeltakarar(Deltakar... deltakarar) {
        Arrays.stream(deltakarar).forEach(deltakar -> this.deltakarar.add(deltakar));
    }

    Sum getUtestaaende(Deltakar deltakar) {
        return reduce(maaltider.stream().map(d -> d.getUtestaaende(deltakar)));
    }

    void addMaaltid(Maaltid maaltid) {
        maaltider.add(maaltid);
    }

    Sum getSum() {
        return reduce(maaltider.stream().map(Maaltid::getSum));
    }

    private Sum reduce(Stream<Sum> s) {
        return s.reduce(new Sum(0, 0), Sum::pluss);
    }

    Sum sumOffset() {
        Stream<Sum> stream = deltakarar.stream().map(this::getUtestaaende);
        List<Sum> collect = stream.collect(Collectors.toList());
        Sum total = new Sum(0, 0);
        for (Sum s : collect) {
            total = total.pluss(s);
        }
        return total;
    }

    Sum getTotaltBetalt(Deltakar deltakar) {
        return maaltider.stream()
                .flatMap(x -> x.getBetalingar().stream())
                .filter(x -> x.getDeltakar().equals(deltakar))
                .map(Betaling::getSum)
                .reduce(new Sum(0, 0), Sum::pluss);
    }

    Sum getTotaltBruktUtenFelles(Deltakar deltakar) {
        return getTotaltBruktGivenFilter(x -> x.harDeltakar(deltakar));
    }

    Sum getTotaltBruktMedFelles(Deltakar deltakar) {
        return maaltider.stream().map(x -> x.getBruktFor(deltakar)).reduce(new Sum(0, 0), Sum::pluss);
    }

    private Sum getTotaltBruktGivenFilter(Predicate<Rett> rettPredicate) {
        return maaltider.stream()
                .flatMap(x -> x.getRetter().stream())
                .filter(rettPredicate)
                .map(Rett::getBeloepPerPerson)
                .reduce(new Sum(0, 0), Sum::pluss);
    }

    Sum getTotaltBruktFelles(Deltakar deltakar) {
        return maaltider.stream().map(x -> x.getBetaltFelles(deltakar)).reduce(new Sum(0, 0), Sum::pluss);
    }

    private void printRapportFor(Deltakar deltakar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(deltakar).append(" har totalt betalt ").append(getTotaltBetalt(deltakar))
        .append("\n").append(deltakar).append(" har totalt brukt ").append(getTotaltBruktUtenFelles(deltakar))
        .append(", ").append("pluss andel fellesutgifter på ").append(getTotaltBruktFelles(deltakar))
        .append(", som gir totale utgifter ").append(getTotaltBruktMedFelles(deltakar))
        .append(", som gir totalt uteståande for ").append(deltakar).append(" på ").append(getUtestaaende(deltakar));
        print(stringBuilder);
    }

    private void print(Object text) {
        System.out.println(text);
    }

    void printRapportMedRettarFor(Deltakar deltakar) {
        printRapportFor(deltakar);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Dette var fordelt på \n").append(
                maaltider.stream()
                        .filter(x -> !x.deltokIkkePaaDetteMaaltidet(deltakar))
                        .map(x -> "Måltid " +x +": " +x.getRetterFor(deltakar))
                        .collect(Collectors.joining("\n"))
        );
        stringBuilder.append("\n Pluss delar i fellesrettar/rettar eg ikkje har klart å spore tilbake \n").append(
                maaltider.stream()
                        .filter(maaltid -> !maaltid.deltokIkkePaaDetteMaaltidet(deltakar))
                        .filter(maaltid -> !maaltid.getRetterFelles(deltakar).isEmpty())
                        .map(maaltid -> "Måltid " +maaltid +": " + getRettarForFellesPrint(deltakar, maaltid))
                        .collect(Collectors.joining("\n"))
        );
        print(stringBuilder);
    }

    private List<String> getRettarForFellesPrint(Deltakar deltakar, Maaltid x) {
        return x.getRetterFelles(deltakar).stream()
                .map(y ->
                        y.getNamn() + ", andel " +
                        y.getBeloep().delPaa(x.getAntallDeltakarar())).collect(Collectors.toList());
    }
}
