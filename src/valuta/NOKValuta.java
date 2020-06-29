package valuta;

public class NOKValuta extends Valuta {
    @Override
    public double getKurs1SaannTilNOK() {
        return 1.0;
    }

    @Override
    public String getSymbol() {
        return "NOK";
    }

    @Override
    public String toString() {
        return "NOK";
    }
}
