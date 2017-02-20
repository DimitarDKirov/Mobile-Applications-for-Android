package mitko.liveworkshopui.models;

/**
 * Created by dimki on 18.02.2017 г..
 */
public class Superhero {
    private String name;
    private  String identity;

    public Superhero(String name, String identity) {
        this.name = name;
        this.identity = identity;
    }

    public String getIdentity() {
        return identity;
    }

    public String getName() {
        return name;
    }
}
