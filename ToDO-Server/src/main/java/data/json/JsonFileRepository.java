package data.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class JsonFileRepository<T> implements SaveLoad<T> {

    protected final ObjectMapper mapper = new ObjectMapper();
    protected final File file;
    protected final Class<T[]> arrayType;

    protected JsonFileRepository(String filePath, Class<T[]> arrayType) {
        this.file = new File(filePath);
        this.arrayType = arrayType;
        this.file.getParentFile().mkdirs();
    }

    @Override
    public List<T> loadAll() throws IOException {
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        T[] data = mapper.readValue(file, arrayType);
        return new ArrayList<>(Arrays.asList(data));
    }

    @Override
    public void saveAll(List<T> data) throws IOException {
        String json = mapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(data);

        try (FileWriter fw = new FileWriter(file)) {
            fw.write(json);
            fw.flush();
        }
    }
}
