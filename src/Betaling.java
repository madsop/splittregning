import lombok.Data;

@Data
class Betaling<T extends Sum> {
    private final Deltakar deltakar;
    private final Sum<T> sum;
}
