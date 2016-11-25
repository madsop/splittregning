import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import tur.Deltakar;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class JSONConverterTest {

    @Test
    public void test() throws JsonProcessingException {
        Deltakar mads = new Deltakar("mads");
        ObjectMapper mapper = new ObjectMapper();
        String valueAsString = mapper.writeValueAsString(mads);
        assertThat(valueAsString, is("{\"namn\":\"mads\"}"));
    }
}
