package sum;

import valuta.NOKValuta;

public class NOK extends Sum<NOKValuta> {

    public NOK(int heltall, int fraction) {
        super(heltall, fraction, new NOKValuta());
    }

    NOK(double verdi) {
        super(verdi, new NOKValuta());
    }

    @Override
    NOK create(double verdi) {
        return new NOK(verdi);
    }

    @Override
    public Class<NOKValuta> getValuta() {
        return NOKValuta.class;
    }

    public static NOK createNullSum() {
        return new NOK(0, 0);
    }
}
