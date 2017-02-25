package pokelist.ngimenez.emn.pokelist;

import java.util.List;

import pokelist.ngimenez.emn.pokelist.models.PokeAPICallResult;
import pokelist.ngimenez.emn.pokelist.models.Pokemon;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ferrilata on 23/02/17.
 */

public interface PokeAPIService {

    class Result {
        int count;
        String previous;
        String next;
        public List<PokeAPICallResult> results;
    }

    @GET("pokemon")
    Call<Result> listPokemons();

    @GET(" ")
    Call<Pokemon> getPokemon();

}
