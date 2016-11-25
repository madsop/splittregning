package sum;

import lombok.Data;
import sum.Sum;
import tur.Deltakar;

@Data
public class Betaling {
    private final Deltakar deltakar;
    private final Sum sum;
}
