import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonJsonParser implements JsonParser {

    @Override
    public List<Map<String, String>> parse(String json) {
        List<Map<String, String>> dados = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode items = mapper.readTree(json).get("items");
            dados = mapper.readValue(items.toString(), new TypeReference<List<Map<String, String>>>(){});
        } catch (Exception e) {
            throw new IllegalArgumentException("Não encontrou items.");
        }
        
        return dados;
    }
    
}
