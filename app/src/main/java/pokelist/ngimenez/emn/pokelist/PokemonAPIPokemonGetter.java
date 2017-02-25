package pokelist.ngimenez.emn.pokelist;

import java.util.List;

import pokelist.ngimenez.emn.pokelist.fragments.PokemonListFragment;
import pokelist.ngimenez.emn.pokelist.models.PokeAPICallResult;
import pokelist.ngimenez.emn.pokelist.models.Pokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * Created by ferrilata on 23/02/17.
 */

public class PokemonAPIPokemonGetter {

    public static void getPokemons(List<PokeAPICallResult> pokemonACRs,
                                            PokemonListFragment.PokemonAdapter adapter) {
        for (PokeAPICallResult pACR : pokemonACRs) {
            getPokemon(pACR, adapter);
        }
    }

    public static void getPokemon(PokeAPICallResult pokemonACR, final PokemonListFragment.PokemonAdapter adapter) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(pokemonACR.getUrl())
                .addConverterFactory(PokemonGsonConverterBuilder.buildGsonConverter())
                .build();

        PokeAPIService pokeAPI = retrofit.create(PokeAPIService.class);

        Call<Pokemon> call = pokeAPI.getPokemon();


        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                Timber.i(response.body().getName());
                adapter.updateList(response.body());
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) { Timber.i(t); }
        });
    }

}
