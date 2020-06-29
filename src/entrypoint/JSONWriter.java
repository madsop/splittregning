package entrypoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javaslang.jackson.datatype.JavaslangModule;
import lombok.NoArgsConstructor;
import tur.Tur;

import java.io.File;
import java.io.IOException;

@NoArgsConstructor
public class JSONWriter {

    public void writeFile(Tur tur, String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.registerModule(new JavaslangModule());
        mapper.writer().writeValue(new File(filename), tur);
    }
}
