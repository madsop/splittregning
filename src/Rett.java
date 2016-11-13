import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
class Rett {
    private final String namn;
    private final Sum beloep;
    private final Set<Deltakar> deltakarar;

    Rett(int heltall, int fraction, Deltakar deltakar, String namn) {
        this(namn, new Sum(heltall, fraction), deltakar);
    }

    Rett(String namn, Sum beloep, Deltakar... deltakarar) {
        this.namn = namn;
        this.beloep = beloep;
        this.deltakarar = new HashSet<>();
        Arrays.stream(deltakarar).filter(Objects::nonNull).forEach(this.deltakarar::add);
    }

    Sum getBeloepPerPerson() {
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