package pokelist.ngimenez.emn.pokelist.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by ferrilata on 23/02/17.
 */

public class Pokemon implements Parcelable, Comparable<Pokemon> {

    private int id;
    private String name;
    private String sprite;
    private int baseExperience;
    private int height;
    private int order;
    private int weight;
    private String species;
    private List<String> abilities;
    private List<String> moves;
    private List<String> types;
    private List<String> stats;

    public Pokemon(int id, String name, String sprite, int baseExperience,
                   int height, int order, int weight, String species,
                   List<String> abilities, List<String> moves,
                   List<String> types, List<String> stats) {
        this.id = id;
        this.name = name;
        this.sprite = sprite;
        this.baseExperience = baseExperience;
        this.height = height;
        this.order = order;
        this.weight = weight;
        this.abilities = abilities;
        this.moves = moves;
        this.species = species;
        this.types = types;
        this.stats = stats;
    }

    protected Pokemon(Parcel in) {
        id = in.readInt();
        name = in.readString();
        sprite = in.readString();
        baseExperience = in.readInt();
        height = in.readInt();
        order = in.readInt();
        weight = in.readInt();
        species = in.readString();
        abilities = in.createStringArrayList();
        moves = in.createStringArrayList();
        types = in.createStringArrayList();
        stats = in.createStringArrayList();
    }

    public static final Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
        @Override
        public Pokemon createFromParcel(Parcel in) {
            return new Pokemon(in);
        }

        @Override
        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getStats() {
        return stats;
    }

    public void setStats(List<String> stats) {
        this.stats = stats;
    }

    public List<String> getMoves() {
        return moves;
    }

    public void setMoves(List<String> moves) {
        this.moves = moves;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(sprite);
        dest.writeInt(baseExperience);
        dest.writeInt(height);
        dest.writeInt(order);
        dest.writeInt(weight);
        dest.writeString(species);
        dest.writeStringList(abilities);
        dest.writeStringList(moves);
        dest.writeStringList(types);
        dest.writeStringList(stats);
    }

    @Override
    public int compareTo(Pokemon o) {
        return order;
    }
}


