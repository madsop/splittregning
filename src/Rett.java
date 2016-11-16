import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
class Rett<T extends Sum<T>> {
    private final String namn;
    private final T beloep;
    private final Set<Deltakar> deltakarar;

    Rett(String namn, T beloep, Deltakar... deltakarar) {
        this.namn = namn;
        this.beloep = beloep;
        this.deltakarar = new HashSet<>(Arrays.stream(deltakarar).filter(Objects::nonNull).collect(Collectors.toSet()));
    }

    T getBeloepPerPerson() {
        return beloep.delPaa(deltakarar.size());
    }

    @Override
    public String toString() {
        return namn + "(" + deltakarar.stream().map(Object::toString).collect(Collectors.joining(", ")) +", " +beloep +")";
    }

    boolean harDeltakar(Deltakar deltakar) {
        return deltakarar.contains(deltakar);
    }

    boolean isFellesRett() {
        return deltakarar.isEmpty();
    }
}