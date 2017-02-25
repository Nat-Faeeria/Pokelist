package pokelist.ngimenez.emn.pokelist;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import pokelist.ngimenez.emn.pokelist.fragments.PokemonDetailFragment;
import pokelist.ngimenez.emn.pokelist.fragments.PokemonListFragment;
import pokelist.ngimenez.emn.pokelist.models.Pokemon;
import timber.log.Timber;

public class PokelistActivity extends AppCompatActivity implements PokemonListFragment.OnPokemonClickListener {

    private Fragment fragment;
    private final String FRAGMENT_NAME = "Pokemon List";
    private boolean dualPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Timber.plant(new Timber.DebugTree());
        dualPane = findViewById(R.id.containerDetailLayout) != null;
        if (savedInstanceState!=null) {
            Timber.i("OnCreate and saved");
             fragment = getSupportFragmentManager()
                    .getFragment(savedInstanceState,
                            FRAGMENT_NAME);
        } else {
            fragment = new PokemonListFragment();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerFrameLayout,
                        fragment,
                        FRAGMENT_NAME)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Timber.i("on saved instance state");

        getSupportFragmentManager().putFragment(outState,
                FRAGMENT_NAME,
                fragment);
    }

    @Override
    public void openPokemonDetails(Pokemon pokemon) {
        PokemonDetailFragment pDF = new PokemonDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("pokemon", pokemon);
        pDF.setArguments(args);
        int id;
        if (dualPane) {
            id = R.id.containerDetailLayout;
        } else {
            id = R.id.containerFrameLayout;
        }
        getSupportFragmentManager().beginTransaction()
                .replace(id,
                        pDF,
                        pokemon.getName())
                .addToBackStack(pokemon.getName())
                .commit();
    }

    /*@Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
            getSupportFragmentManager().beginTransaction()
                    .attach(fragment)
                    .commit();
        }
    }*/
}
