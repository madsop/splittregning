package valuta;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public abstract class Valuta {
    public abstract double getKurs1SaannTilNOK();
    public abstract String getSymbol();
}
