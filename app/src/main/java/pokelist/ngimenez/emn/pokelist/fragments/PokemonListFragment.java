package pokelist.ngimenez.emn.pokelist.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import pokelist.ngimenez.emn.pokelist.services.PokeAPIService;
import pokelist.ngimenez.emn.pokelist.utils.PokemonAPIPokemonGetter;
import pokelist.ngimenez.emn.pokelist.R;
import pokelist.ngimenez.emn.pokelist.models.Pokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnPokemonClickListener} interface
 * to handle interaction events.
 */
public class PokemonListFragment extends Fragment {

    private OnPokemonClickListener mListener;
    private RecyclerView recyclerView;
    private PokemonAdapter adapter;

    public PokemonListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.pokelist);
        if (savedInstanceState == null && adapter == null) {
            adapter = new PokemonAdapter(new ArrayList<Pokemon>());
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://pokeapi.co/api/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            PokeAPIService pokeAPI = retrofit.create(PokeAPIService.class);


            Call<PokeAPIService.Result> call = pokeAPI.listPokemons();


            call.enqueue(new Callback<PokeAPIService.Result>() {
                @Override
                public void onResponse(Call<PokeAPIService.Result> call, Response<PokeAPIService.Result> response) {
                    PokemonAPIPokemonGetter.getPokemons(response.body().results, adapter);
                    recyclerView.setAdapter(adapter);
                    Timber.i("Api call successful");
                }

                @Override
                public void onFailure(Call<PokeAPIService.Result> call, Throwable t) {
                    Timber.i(t);
                    Toast.makeText(PokemonListFragment.this.getContext(),
                            "Failing to retrieve pokemons",
                            Toast.LENGTH_SHORT);
                }
            });
        } else if (savedInstanceState != null) {
            adapter = new PokemonAdapter(
                    savedInstanceState.<Pokemon>getParcelableArrayList("pokemons")
            );
        }
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPokemonClickListener) {
            mListener = (OnPokemonClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnPokemonClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outstate) {
        Timber.i("OSIS");
        outstate.putParcelableArrayList("pokemons", adapter.pokemons);
        super.onSaveInstanceState(outstate);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Timber.i("OAC");
        if (savedInstanceState != null) {
            adapter =
                    new PokemonAdapter(
                            savedInstanceState.<Pokemon>getParcelableArrayList("pokemons")
                    );
            this.recyclerView.setAdapter(adapter);
        }
    }

    public interface OnPokemonClickListener {
        void openPokemonDetails(Pokemon pokemon);
    }

    public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonHolder> {

        public ArrayList<Pokemon> pokemons;

        public PokemonAdapter(ArrayList<Pokemon> pokemons) {
            this.pokemons = pokemons;
        }

        @Override
        public PokemonAdapter.PokemonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_view_pokemon, parent, false);
            return new PokemonAdapter.PokemonHolder(view);
        }

        @Override
        public void onBindViewHolder(PokemonAdapter.PokemonHolder holder, int position) {
            holder.bind(pokemons.get(position));
        }

        @Override
        public int getItemCount() {
            return pokemons.size();
        }

        public void updateList(Pokemon pokemon) {
            pokemons.add(pokemon);
            notifyDataSetChanged();
        }

        public class PokemonHolder extends RecyclerView.ViewHolder {

            private Pokemon pokemon;

            private final View view;
            private final ImageView spriteView;
            private final TextView nameView;

            public PokemonHolder(View itemView) {
                super(itemView);
                view = itemView;
                spriteView = (ImageView) view.findViewById(R.id.poke_detail_sprite);
                nameView = (TextView) view.findViewById(R.id.poke_detail_name);
            }

            public void bind(final Pokemon pokemon) {
                this.pokemon = pokemon;
                nameView.setText(this.pokemon.getName());
                Glide.with(view.getContext().getApplicationContext())
                        .load(this.pokemon.getSprite())
                        .fitCenter()
                        .crossFade()
                        .into(this.spriteView);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.openPokemonDetails(pokemon);
                    }
                });
            }
        }

    }
}
