import javaslang.collection.HashSet;
import javaslang.collection.Set;
import lombok.Getter;

@Getter
class Rett {
    private final String namn;
    private final Sum beloep;
    private final Set<Deltakar> deltakarar;

    Rett(String namn, Sum beloep, Deltakar... deltakarar) {
        this.namn = namn;
        this.beloep = beloep;
        this.deltakarar = HashSet.of(deltakarar);
    }

    Sum getBeloepPerPerson() {
        return beloep.delPaa(deltakarar.size());
    }

    @Override
    public String toString() {
        return namn + "(" + deltakarar.map(Object::toString).mkString (", ")  +", " +beloep +")";
    }

    boolean harDeltakar(Deltakar deltakar) {
        return deltakarar.contains(deltakar);
    }

    boolean isFellesRett() {
        return deltakarar.isEmpty();
    }
}