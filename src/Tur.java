import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
}
