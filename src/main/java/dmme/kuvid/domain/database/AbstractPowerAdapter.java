package dmme.kuvid.domain.database;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import dmme.kuvid.domain.GameObjects.Powerup.PowerUp;

public class AbstractPowerAdapter implements JsonSerializer<PowerUp>, JsonDeserializer<PowerUp> {
    @Override
    public JsonElement serialize(PowerUp src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("class", new JsonPrimitive(src.getClass().getSimpleName()));
        result.add("values", context.serialize(src, src.getClass()));
 
        return result;
    }
 
    @Override
    public PowerUp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("class").getAsString();
        JsonElement element = jsonObject.get("values");
 
        try {
            return context.deserialize(element, Class.forName("dmme.kuvid.domain.GameObjects.Powerup." + type));
        } catch (ClassNotFoundException cnfe) {
            throw new JsonParseException("Unknown element type: " + type, cnfe);
        }
    }
}
