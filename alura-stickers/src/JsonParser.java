import java.util.List;
import java.util.Map;

public interface JsonParser {
    List<Map<String, String>> parse(String json);
}
