package pokelist.ngimenez.emn.pokelist.models;

/**
 * Created by ferrilata on 23/02/17.
 */

public class PokeAPICallResult {

    private String url;
    private String name;

    public PokeAPICallResult(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
