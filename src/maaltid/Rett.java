package maaltid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javaslang.collection.HashSet;
import javaslang.collection.Set;
import lombok.Getter;
import tur.Deltakar;
import sum.Sum;

@Getter
public class Rett {
    private final String namn;
    private final Sum beloep;
    private final Set<Deltakar> deltakarar;

    @JsonCreator
    public Rett(@JsonProperty("namn") String namn, @JsonProperty("beloep") Sum beloep, @JsonProperty("deltakarar") Deltakar... deltakarar) {
        this.namn = namn;
        this.beloep = beloep;
        this.deltakarar = HashSet.of(deltakarar);
    }

    @JsonIgnore
    public Sum getBeloepPerPerson() {
        return beloep.delPaa(deltakarar.size());
    }

    @Override
    public String toString() {
        return namn + "(" + deltakarar.map(Object::toString).mkString (", ")  +", " +beloep +")";
    }

    public boolean harDeltakar(Deltakar deltakar) {
        return deltakarar.contains(deltakar);
    }

    boolean isFellesRett() {
        return deltakarar.isEmpty();
    }
}