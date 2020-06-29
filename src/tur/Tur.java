package tur;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import javaslang.Tuple;
import javaslang.collection.List;
import javaslang.collection.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import maaltid.Maaltid;
import maaltid.Rett;
import sum.Betaling;
import sum.Euro;
import sum.NOK;
import sum.Sum;
import valuta.Valuta;

import java.util.function.Predicate;

@NoArgsConstructor
@JsonIgnoreProperties(value={ "oppgjer", "sumINOK" }, allowGetters=true)
public class Tur {

    @Getter @Setter
    private List<Maaltid> maaltider;
    @Getter @Setter
    private List<Deltakar> deltakarar;

    public Tur(Deltakar... deltakarar) {
        this.maaltider = List.empty();
        this.deltakarar = List.of(deltakarar);
    }

    public void addMaaltid(Maaltid maaltid) {
        maaltider = maaltider.append(maaltid);
    }

    @JsonIgnore
    public Sum getSum() {
        return maaltider.map(Maaltid::getSum).fold(Euro.createNullSum(), Sum::pluss);
    }

    @JsonIgnore
    public Sum getUtestaaende(Deltakar deltakar) {
        return maaltider.map(d -> d.getUtestaaende(deltakar)).fold(Euro.createNullSum(), Sum::pluss);
    }

    public Sum sumOffset() {
        return deltakarar.map(this::getUtestaaende).fold(Euro.createNullSum(), Sum::pluss);
    }

    @JsonIgnore
    public Sum getTotaltBetalt(Deltakar deltakar) {
        return maaltider
                .flatMap(Maaltid::getBetalingar)
                .filter(x -> x.getDeltakar().equals(deltakar))
                .map(Betaling::getSum)
                .fold(Euro.createNullSum(), Sum::pluss);
    }

    @JsonIgnore
    public Sum getTotaltBruktUtenFelles(Deltakar deltakar) {
        return getTotaltBruktGivenFilter(x -> x.harDeltakar(deltakar));
    }

    @JsonIgnore
    public Sum getTotaltBruktMedFelles(Deltakar deltakar) {
        return maaltider.map(maaltid -> maaltid.getBruktFor(deltakar)).fold(Euro.createNullSum(), Sum::pluss);
    }

    @JsonIgnore
    public Sum getTotaltBruktKunFelles(Deltakar deltakar) {
        return maaltider.map(maaltid -> maaltid.collectBetaltFelles(deltakar)).fold(Euro.createNullSum(), Sum::pluss);
    }

    private Sum getTotaltBruktGivenFilter(Predicate<Rett> rettPredicate) {
        return maaltider.flatMap(Maaltid::getRetter).filter(rettPredicate)
                .map(Rett::getBeloepPerPerson)
                .fold(Euro.createNullSum(), Sum::pluss);
    }

    public <U extends Valuta> Sum<U> getSumINOK() {
        return maaltider
                .map(Maaltid::getSum)
                .map(Sum::convertToNOK)
                .map(x -> (Sum) x)
                .fold(NOK.createNullSum(), Sum::pluss);
    }

    @JsonSetter
    public Map<Deltakar, Sum> getOppgjer() {
        return deltakarar.toMap(deltakar -> Tuple.of(deltakar, getUtestaaende(deltakar)));
    }
}
