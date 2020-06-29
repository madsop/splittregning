package tur;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class Deltakar {

    private final String namn;

    @JsonCreator
    public Deltakar(@JsonProperty("namn") String namn) {
        this.namn = namn;
    }

    @Override
    public String toString() {
        return namn;
    }
}
