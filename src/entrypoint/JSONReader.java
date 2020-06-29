package entrypoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javaslang.jackson.datatype.JavaslangModule;
import lombok.NoArgsConstructor;
import tur.Tur;

import java.io.File;
import java.io.IOException;

@NoArgsConstructor
public class JSONReader {

    public Tur readTur(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.registerModule(new JavaslangModule());
        Object object = mapper.readValue(new File(filename), Object.class);
        return mapper.convertValue(object, Tur.class);
    }
}
