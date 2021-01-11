package dmme.kuvid.domain.database;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.*;
import java.lang.reflect.Type;
import dmme.kuvid.domain.GameObjects.Atoms.Atom;

public class AbstractAtomAdapter implements JsonSerializer<Atom>, JsonDeserializer<Atom> {
    @Override
    public JsonElement serialize(Atom src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("class", new JsonPrimitive(src.getClass().getSimpleName()));
        result.add("values", context.serialize(src, src.getClass()));
 
        return result;
    }
 
    @Override
    public Atom deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("class").getAsString();
        JsonElement element = jsonObject.get("values");
 
        try {
            return context.deserialize(element, Class.forName("dmme.kuvid.domain.GameObjects.Atoms." + type));
        } catch (ClassNotFoundException cnfe) {
            throw new JsonParseException("Unknown element type: " + type, cnfe);
        }
    }

}
