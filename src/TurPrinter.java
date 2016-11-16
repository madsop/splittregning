import javaslang.collection.List;
import lombok.RequiredArgsConstructor;

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
                        .filter(maaltid -> !maaltid.deltokIkkePaaDetteMaaltidet(deltakar))
                        .map(maaltid -> "Måltid " +maaltid +": " +maaltid.getRetterFor(deltakar))
                        .mkString("\n")

        );
        stringBuilder.append("\n Pluss delar i fellesrettar/rettar eg ikkje har klart å spore tilbake \n").append(
                tur.getMaaltider()
                        .filter(maaltid -> !maaltid.deltokIkkePaaDetteMaaltidet(deltakar))
                        .filter(maaltid -> !maaltid.getRetterFelles(deltakar).isEmpty())
                        .map(maaltid -> "Måltid " +maaltid +": " + getRettarForFellesPrint(deltakar, maaltid))
                        .mkString("\n")
        );
        print(stringBuilder);
    }

    private List<String> getRettarForFellesPrint(Deltakar deltakar, Maaltid maaltid) {
        return maaltid.getRetterFelles(deltakar)
                .map(rett -> rett.getNamn() + ", andel " +rett.getBeloep().delPaa(maaltid.getAntallDeltakarar()));
    }
}
