package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {
    private int mapSize;
    private Map<String, BaseSchema> shapeMap;
    private boolean sizeofDefined;
    private boolean shapeMapDefined;

    @Override
    public boolean check(Object obj) {
        boolean isValid = obj instanceof Map
                || obj == null;

        if (required) {
            isValid = obj instanceof Map;
        }
        if (sizeofDefined) {
            isValid = obj instanceof Map && ((Map<?, ?>) obj).size() == mapSize;
        }

        if (shapeMapDefined && obj instanceof Map) {
            isValid = checkShape(obj);
        }
        return isValid;
    }

    private boolean checkShape(Object obj) {
        boolean isValid;
        for (Map.Entry<String, Object> e : ((Map<String, Object>) obj).entrySet()) {
            if (shapeMap.containsKey(e.getKey())) {
                isValid = shapeMap.get(e.getKey()).isValid(e.getValue());
                if (!isValid) {
                    return false;
                }
            }
        }
        return true;
    }

    public MapSchema required() {
        this.required = true;
        return this;
    }

    public MapSchema sizeof(Integer size) {
        this.mapSize = size;
        this.sizeofDefined = true;
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> map) {
        this.shapeMap = map;
        this.shapeMapDefined = true;
        return this;
    }
}
