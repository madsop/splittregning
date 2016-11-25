package maaltid;

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

    public Rett(String namn, Sum beloep, Deltakar... deltakarar) {
        this.namn = namn;
        this.beloep = beloep;
        this.deltakarar = HashSet.of(deltakarar);
    }

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