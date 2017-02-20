package mitko.liveworkshopui.models;

/**
 * Created by dimki on 18.02.2017 г..
 */
public class Superhero {
    private String name;
    private  String secretIdentity;

    public Superhero(String name, String identity) {
        this.name = name;
        this.secretIdentity = identity;
    }

    public String getSecretIdentity() {
        return secretIdentity;
    }

    public String getName() {
        return name;
    }
}
