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
        return getTotaltBruktGivenFilter(x -> deltakar.equals(x.getDeltakar()));
    }

    Sum getTotaltBruktMedFelles(Deltakar deltakar) {
        Predicate<Rett> rettPredicate = x -> deltakar.equals(x.getDeltakar()) ||  x.getDeltakar() == null;
        return maaltider.stream().map(x -> x.getBruktFor(deltakar)).reduce(new Sum(0, 0), Sum::pluss);
    }

    private Sum getTotaltBruktGivenFilter(Predicate<Rett> rettPredicate) {
        return maaltider.stream()
                .flatMap(x -> x.getRetter().stream())
                .filter(rettPredicate)
                .map(Rett::getBeloep)
                .reduce(new Sum(0, 0), Sum::pluss);
    }

    Sum getTotaltBetaltFelles(Deltakar deltakar) {
        return maaltider.stream().map(x -> x.getBetaltFelles(deltakar)).reduce(new Sum(0, 0), Sum::pluss);
    }
}
