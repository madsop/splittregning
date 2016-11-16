import javaslang.collection.List;
import lombok.Getter;

import java.util.function.Predicate;

class Tur {

    @Getter
    private List<Maaltid> maaltider;
    private List<Deltakar> deltakarar;

    Tur(Deltakar... deltakarar) {
        this.maaltider = List.empty();
        this.deltakarar = List.of(deltakarar);
    }

    void addMaaltid(Maaltid maaltid) {
        maaltider = maaltider.append(maaltid);
    }

    Sum getSum() {
        return maaltider.map(Maaltid::getSum).fold(Euro.createNullSum(), Sum::pluss);
    }

    Sum getUtestaaende(Deltakar deltakar) {
        return maaltider.map(d -> d.getUtestaaende(deltakar)).fold(Euro.createNullSum(), Sum::pluss);
    }

    Sum sumOffset() {
        return deltakarar.map(this::getUtestaaende).fold(Euro.createNullSum(), Sum::pluss);
    }

    Sum getTotaltBetalt(Deltakar deltakar) {
        List<Betaling> stream = maaltider
                .flatMap(Maaltid::getBetalingar);
        return stream
                .filter(x -> x.getDeltakar().equals(deltakar))
                .map(Betaling::getSum)
                .fold(Euro.createNullSum(), Sum::pluss);
    }

    Sum getTotaltBruktUtenFelles(Deltakar deltakar) {
        return getTotaltBruktGivenFilter(x -> x.harDeltakar(deltakar));
    }

    Sum getTotaltBruktMedFelles(Deltakar deltakar) {
        return maaltider.map(maaltid -> maaltid.getBruktFor(deltakar)).fold(Euro.createNullSum(), Sum::pluss);
    }

    Sum getTotaltBruktKunFelles(Deltakar deltakar) {
        return maaltider.map(maaltid -> maaltid.getBetaltFelles(deltakar)).fold(Euro.createNullSum(), Sum::pluss);
    }

    private Sum getTotaltBruktGivenFilter(Predicate<Rett> rettPredicate) {
        return maaltider.flatMap(Maaltid::getRetter).filter(rettPredicate)
                .map(Rett::getBeloepPerPerson)
                .fold(Euro.createNullSum(), Sum::pluss);
    }

    NOK getSumINOK() {
        return maaltider
                .map(Maaltid::getSum)
                .map(Sum::convertToNOK)
                .fold(NOK.createNullSum(), NOK::pluss);
    }
}
