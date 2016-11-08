import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class Tur {
    private List<Maaltid> maaltider;

    Tur() {
        maaltider = new ArrayList<>();
    }

    Sum getUtestaaende(Deltakar deltakar) {
        return reduce(maaltider.stream().map(d -> d.getUtestaaende(deltakar)));
    }

    void addMaaltid(Maaltid maaltid) {
        maaltider.add(maaltid);
    }

    Sum getSum() {
//        maaltider.stream().flatMap(x -> x.getUtestaaende())
        return  reduce(maaltider.stream().map(Maaltid::getSum));
    }

    private Sum reduce(Stream<Sum> s) {
        return s.reduce(new Sum(0, 0), Sum::pluss);
    }
}
