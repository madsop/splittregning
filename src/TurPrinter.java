import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
class TurPrinter {
    private final Tur tur;


    private void printRapportFor(Deltakar deltakar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(deltakar).append(" har totalt betalt ").append(tur.getTotaltBetalt(deltakar))
                .append("\n").append(deltakar).append(" har totalt brukt ").append(tur.getTotaltBruktUtenFelles(deltakar))
                .append(", ").append("pluss andel fellesutgifter på ").append(tur.getTotaltBruktKunFelles(deltakar))
                .append(", som gir totale utgifter ").append(tur.getTotaltBruktMedFelles(deltakar))
                .append(", som gir totalt uteståande for ").append(deltakar).append(" på ").append(tur.getUtestaaende(deltakar));
        print(stringBuilder);
    }

    private void print(Object text) {
        System.out.println(text);
    }

    void printRapportMedRettarFor(Deltakar deltakar) {
        printRapportFor(deltakar);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Dette var fordelt på \n").append(
                tur.getMaaltider()
                        .filter(x -> !x.deltokIkkePaaDetteMaaltidet(deltakar))
                        .map(x -> "Måltid " +x +": " +x.getRetterFor(deltakar))
                        .intersperse("\n")
                        .fold("", String::concat)
        );
        stringBuilder.append("\n Pluss delar i fellesrettar/rettar eg ikkje har klart å spore tilbake \n").append(
                tur.getMaaltider()
                        .filter(maaltid -> !maaltid.deltokIkkePaaDetteMaaltidet(deltakar))
                        .filter(maaltid -> !maaltid.getRetterFelles(deltakar).isEmpty())
                        .map(maaltid -> "Måltid " +maaltid +": " + getRettarForFellesPrint(deltakar, maaltid))
                        .intersperse("\n")
                        .fold("", String::concat)
        );
        print(stringBuilder);
    }

    private List<String> getRettarForFellesPrint(Deltakar deltakar, Maaltid x) {
        Stream<Rett> stream = x.getRetterFelles(deltakar).stream();
        return stream
                .map(y ->
                        y.getNamn() + ", andel " +
                                y.getBeloep().delPaa(x.getAntallDeltakarar())).collect(Collectors.toList());
    }
}
