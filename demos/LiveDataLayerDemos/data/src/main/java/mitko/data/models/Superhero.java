package mitko.data.models;

/**
 * Created by dimki on 21.02.2017 г..
 */

public class Superhero {
    private String id;
    private String name;
    private String secretIdentity;
    private String imgUrl;

    public Superhero() {
    }

    public Superhero(String id, String name, String secretIdentity, String imgUrl) {
        this();
        this.id = id;
        this.name = name;
        this.secretIdentity = secretIdentity;
        this.imgUrl = imgUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecretIdentity() {
        return secretIdentity;
    }

    public void setSecretIdentity(String secretIdentity) {
        this.secretIdentity = secretIdentity;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
