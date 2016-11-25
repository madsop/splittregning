package tur;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode
@Getter
@RequiredArgsConstructor
public class Deltakar {

    private final String namn;

    @Override
    public String toString() {
        return namn;
    }
}
