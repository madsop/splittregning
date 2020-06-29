package sum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import tur.Deltakar;

@EqualsAndHashCode
@Getter
@ToString
public class Betaling {
    private final Deltakar deltakar;
    private final Sum sum;

    @JsonCreator
    public Betaling(@JsonProperty("deltakar") Deltakar deltakar, @JsonProperty("sum") Sum sum) {
        this.deltakar = deltakar;
        this.sum = sum;
    }
}
