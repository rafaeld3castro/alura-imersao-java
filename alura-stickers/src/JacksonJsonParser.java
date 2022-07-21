import java.util.ArrayList;
import java.util.Iterator;
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
            JsonNode nodeArray = getNodeArray(mapper.readTree(json));
            dados = mapper.readValue(nodeArray.toString(), new TypeReference<List<Map<String, String>>>(){});
        } catch (Exception e) {
            throw new IllegalArgumentException("Não encontrou items.");
        }
        
        return dados;
    }

    private JsonNode getNodeArray(JsonNode node) {
        if (node.isArray()) {
            return node;
        } else {
            Iterator<JsonNode> iterator = node.iterator();
            JsonNode array = null;
            iteratorLoop:
            while(iterator.hasNext()) {
                JsonNode next = iterator.next();
                if (next.isArray()) {
                    array = next;
                    break iteratorLoop;
                }
            }
            return array;
        }
    }
    
}
