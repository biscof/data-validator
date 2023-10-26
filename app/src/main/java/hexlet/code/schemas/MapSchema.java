package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {
    private int requiredSize;
    private Map<String, BaseSchema> shapeMap;
    private boolean sizeDefined;
    private boolean shapeMapDefined;

    @Override
    public boolean check(Object obj) {
        boolean isValid = isMap(obj) || obj == null;

        if (required) {
            isValid = isMap(obj);
        }
        if (sizeDefined) {
            isValid = isOfSize(obj, requiredSize);
        }
        if (shapeMapDefined && isMap(obj)) {
            isValid = checkShape(obj, shapeMap);
        }
        return isValid;
    }

    public MapSchema required() {
        this.required = true;
        return this;
    }

    public MapSchema sizeof(Integer size) {
        this.requiredSize = size;
        this.sizeDefined = true;
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> map) {
        this.shapeMap = map;
        this.shapeMapDefined = true;
        return this;
    }

    private boolean isMap(Object obj) {
        return obj instanceof Map;
    }

    private boolean isOfSize(Object obj, int mapSize) {
        return isMap(obj) && ((Map<?, ?>) obj).size() == mapSize;
    }

    private boolean checkShape(Object obj, Map<String, BaseSchema> shape) {
        if (!isMap(obj)) {
            return false;
        }

        Map<?, ?> validatedMap = (Map<?, ?>) obj;

        for (String key : shape.keySet()) {
            if (validatedMap.containsKey(key) && !shape.get(key).isValid(validatedMap.get(key))) {
                return false;
            }
        }

        return true;
    }
}
