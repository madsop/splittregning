package valuta;

public class EuroValuta extends Valuta {

    @Override
    public double getKurs1SaannTilNOK() {
        return 9.33;
    }

    @Override
    public String getSymbol() {
        return "€";
    }

    @Override
    public String toString() {
        return "Euro (" +getSymbol() +")";
    }
}
