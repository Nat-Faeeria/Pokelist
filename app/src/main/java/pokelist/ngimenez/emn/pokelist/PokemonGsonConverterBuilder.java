package pokelist.ngimenez.emn.pokelist;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pokelist.ngimenez.emn.pokelist.models.Pokemon;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ferrilata on 23/02/17.
 */

public class PokemonGsonConverterBuilder {

    public static GsonConverterFactory buildGsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(Pokemon.class, new PokemonDeserializer());
        Gson myGson = gsonBuilder
                .setLenient()
                .create();

        return GsonConverterFactory.create(myGson);
    }

}
