package sum;

import valuta.EuroValuta;

public class Euro extends Sum<EuroValuta> {
    public static final Euro empty = new Euro(0, 0);

    public Euro(double verdi) {
        super(verdi, new EuroValuta());
    }

    public Euro(int heltall, int fraction) {
        super(heltall, fraction, new EuroValuta());
    }


    @Override
    Euro create(double verdi) {
        return new Euro(verdi);
    }

    @Override
    public Class<EuroValuta> getValuta() {
        return EuroValuta.class;
    }

    public static Euro createNullSum() {
        return new Euro(0, 0);
    }
}
