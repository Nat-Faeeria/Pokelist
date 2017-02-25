package pokelist.ngimenez.emn.pokelist;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import pokelist.ngimenez.emn.pokelist.models.Pokemon;
import timber.log.Timber;

/**
 * Created by ferrilata on 23/02/17.
 */

public class PokemonDeserializer implements JsonDeserializer<Pokemon> {

    @Override
    public Pokemon deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject obj = json.getAsJsonObject();

        List<String> abilities = new ArrayList<>();
        for (JsonElement el : obj.get("abilities").getAsJsonArray()) {
            abilities.add(el.getAsJsonObject()
                    .get("ability").getAsJsonObject()
                    .get("name").getAsString());
        }

        List<String> moves = new ArrayList<>();
        for (JsonElement el : obj.get("moves").getAsJsonArray()) {
            moves.add(el.getAsJsonObject()
                    .get("move").getAsJsonObject()
                    .get("name").getAsString());
        }

        List<String> stats = new ArrayList<>();
        for (JsonElement el : obj.get("stats").getAsJsonArray()) {
            JsonObject stat = el.getAsJsonObject();
            String theStat = stat.get("stat").getAsJsonObject().get("name").getAsString()
                    + " : "+stat.get("base_stat")
                    + ' '+stat.get("effort");
            stats.add(theStat);
        }

        List<String> types = new ArrayList<>();
        for (JsonElement el : obj.get("types").getAsJsonArray()) {
            types.add(el.getAsJsonObject()
                    .get("type").getAsJsonObject()
                    .get("name").getAsString());
        }

        return new Pokemon(
                obj.get("id").getAsInt(),
                obj.get("name").getAsString(),
                obj.get("sprites").getAsJsonObject().get("front_default").getAsString(),
                obj.get("base_experience").getAsInt(),
                obj.get("height").getAsInt(),
                obj.get("order").getAsInt(),
                obj.get("weight").getAsInt(),
                obj.get("species").getAsJsonObject().get("name").getAsString(),
                abilities,
                moves,
                types,
                stats
        );
    }
}