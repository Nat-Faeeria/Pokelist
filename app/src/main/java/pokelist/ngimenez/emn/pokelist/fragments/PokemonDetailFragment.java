package pokelist.ngimenez.emn.pokelist.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import pokelist.ngimenez.emn.pokelist.R;
import pokelist.ngimenez.emn.pokelist.models.Pokemon;


/**
 * Created by ferrilata on 24/02/17.
 */
public class PokemonDetailFragment extends Fragment {

    private Pokemon pokemon;

    public PokemonDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon_detail, container, false);
        Bundle args = getArguments();
        pokemon = args.getParcelable("pokemon");

        ImageView sprite = (ImageView) view.findViewById(R.id.detail_sprite);
        TextView name = (TextView) view.findViewById(R.id.detail_name);
        TextView types = (TextView) view.findViewById(R.id.detail_types);
        TextView baseXP = (TextView) view.findViewById(R.id.detail_base_experience);
        TextView height = (TextView) view.findViewById(R.id.detail_height);
        TextView weight = (TextView) view.findViewById(R.id.detail_weight);
        TextView species = (TextView) view.findViewById(R.id.detail_species);
        TextView stats = (TextView) view.findViewById(R.id.detail_stats);
        TextView abilities = (TextView) view.findViewById(R.id.detail_abilities);
        TextView moves = (TextView) view.findViewById(R.id.detail_moves);

        String tempStuff = "";

        Glide.with(view.getContext().getApplicationContext())
                .load(pokemon.getSprite())
                .fitCenter()
                .crossFade()
                .into(sprite);

        name.setText(pokemon.getName());

        StringBuilder type = new StringBuilder();
        type.append("Types : ");
        for (String s : pokemon.getTypes()) {
            type.append(s).append("/");
        }
        types.setText(type.toString().substring(0, type.length()-1));

        tempStuff = "Base XP : "+String.valueOf(pokemon.getBaseExperience());
        baseXP.setText(tempStuff);

        tempStuff = "Height : "+String.valueOf(pokemon.getHeight());
        height.setText(tempStuff);

        tempStuff = "Weight : "+String.valueOf(pokemon.getWeight());
        weight.setText(tempStuff);

        tempStuff = "Species : "+pokemon.getSpecies();
        species.setText(tempStuff);

        StringBuilder stat = new StringBuilder();
        stat.append("Stats : \n");
        for (String s : pokemon.getStats()) {
            stat.append("  ").append(s).append("\n");
        }
        stats.setText(stat.toString());

        StringBuilder ability = new StringBuilder();
        ability.append("Abilities : \n");
        for (String s : pokemon.getAbilities()) {
            ability.append("  ").append(s).append("\n");
        }
        abilities.setText(ability.toString());

        StringBuilder move = new StringBuilder();
        move.append("Moves : \n");
        for (String s : pokemon.getMoves()) {
            move.append("  ").append(s).append("\n");
        }
        moves.setText(move.toString());

        return view;
    }
}
