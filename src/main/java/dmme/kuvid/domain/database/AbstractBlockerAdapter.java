package dmme.kuvid.domain.database;

import java.lang.reflect.Type;

import com.google.gson.InstanceCreator;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import dmme.kuvid.domain.GameObjects.Molecules.MovementStrategy;
import dmme.kuvid.domain.GameObjects.ReactionBlocker.ReactionBlocker;

public class AbstractBlockerAdapter implements JsonSerializer<ReactionBlocker>, JsonDeserializer<ReactionBlocker> {
    @Override
    public JsonElement serialize(ReactionBlocker src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("class", new JsonPrimitive(src.getClass().getSimpleName()));
        result.add("values", context.serialize(src, src.getClass()));
        result.addProperty("strategyName", src.getPattern().getClass().getSimpleName());
        result.add("pattern", context.serialize(src.getPattern(),src.getPattern().getClass()));
 
        return result;
    }
 
    @Override
    public ReactionBlocker deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("class").getAsString();
        JsonElement element = jsonObject.get("values");
        JsonElement strat=jsonObject.get("pattern");
        String pType=jsonObject.get("strategyName").getAsString();
 
        try {
        	MovementStrategy st=context.deserialize(strat, Class.forName("dmme.kuvid.domain.GameObjects.Molecules."+pType));
        	ReactionBlocker Block=context.deserialize(element, Class.forName("dmme.kuvid.domain.GameObjects.ReactionBlocker." + type));
            Block.setPattern(st);
        	return Block;
        } catch (ClassNotFoundException cnfe) {
            throw new JsonParseException("Unknown element type: " + type, cnfe);
        }
    }

}
