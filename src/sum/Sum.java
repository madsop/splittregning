package sum;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import valuta.EuroValuta;
import valuta.NOKValuta;
import valuta.Valuta;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value=Euro.class, name = "Euro"), @JsonSubTypes.Type(value = NOK.class, name = "NOK")})
@JsonIgnoreProperties(value={ "originalAndNOK" }, allowGetters=true)
public abstract class Sum<T extends Valuta> {
    @Getter
    protected double verdi;

    private T valuta;

    Sum(double verdi, T valuta) {
        this.verdi = verdi;
        this.valuta = valuta;
    }

    Sum(int heltall, int fraction, T valuta) {
        if (heltall == 0 && fraction < 0) {
            verdi = Double.valueOf("-0." + Math.abs(fraction));
            return;
        }
        verdi = Double.parseDouble(heltall + "." + fraction);
        this.valuta = valuta;
    }

    abstract Sum<T> create(double verdi);

    public Sum<T> pluss(Sum<? extends T> b) {
        return create(verdi + b.getVerdi());
    }

    public Sum<T> minus(Sum<T> b) {
        return create(verdi - b.getVerdi());
    }

    public Sum<T> delPaa(long antallDeltakarar) {
        return create(verdi / antallDeltakarar);
    }

    public Sum<T> ganger(int i) {
        return create(verdi*i);
    }

    @Override
    public String toString() {
        return String.format("%.2f", verdi) +" " +valuta.getSymbol();
    }

    public String getOriginalAndNOK() {
        return toString() +" ( " +convertToNOK() +")";
    }

    public NOK convertToNOK() {
        return new NOK(this.verdi * valuta.getKurs1SaannTilNOK());
    }

    public static <U extends Valuta> Sum createNull(Class<U> clazz){
        if (clazz.equals(NOKValuta.class)) {
            return NOK.createNullSum();
        }
        else if (clazz.equals(EuroValuta.class)) {
            return Euro.createNullSum();
        }
        return null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sum<T> sum = (Sum<T>) o;

        return Math.abs(sum.verdi - verdi) < 0.001;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(verdi);
        return (int) (temp ^ (temp >>> 32));
    }

    public abstract Class<T> figureValuta();
}
