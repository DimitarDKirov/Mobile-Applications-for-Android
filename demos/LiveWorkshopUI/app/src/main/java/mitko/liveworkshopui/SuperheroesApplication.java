package mitko.liveworkshopui;

import android.app.Application;

import java.util.ArrayList;

import mitko.liveworkshopui.models.Superhero;

/**
 * Created by dimki on 18.02.2017 г..
 */
public class SuperheroesApplication extends Application {
    private static final String API_BASE_URL = "http://192.168.0.104:3001/";
    //private static final String API_BASE_URL = "http://10.82.200.82:3001/";
    private ArrayList<Superhero> superheroes;

    public SuperheroesApplication() {
        super();
       this.superheroes = new ArrayList<>();
//        this.superheroes.add(new Superhero("Ironman", "Tony Stark"));
//        this.superheroes.add(new Superhero("The Hulk", "Bruce"));
//        this.superheroes.add(new Superhero("Thor", "Thor"));
//        this.superheroes.add(new Superhero("Dr. Strange", "Stephen"));
//        this.superheroes.add(new Superhero("Ironman", "Tony Stark"));
//        this.superheroes.add(new Superhero("The Hulk", "Bruce"));
//        this.superheroes.add(new Superhero("Thor", "Thor"));
//        this.superheroes.add(new Superhero("Dr. Strange", "Stephen"));
//        this.superheroes.add(new Superhero("Ironman", "Tony Stark"));
//        this.superheroes.add(new Superhero("The Hulk", "Bruce"));
//        this.superheroes.add(new Superhero("Thor", "Thor"));
//        this.superheroes.add(new Superhero("Dr. Strange", "Stephen"));
//        this.superheroes.add(new Superhero("Ironman", "Tony Stark"));
//        this.superheroes.add(new Superhero("The Hulk", "Bruce"));
//        this.superheroes.add(new Superhero("Thor", "Thor"));
//        this.superheroes.add(new Superhero("Dr. Strange", "Stephen"));
//        this.superheroes.add(new Superhero("Ironman", "Tony Stark"));
//        this.superheroes.add(new Superhero("The Hulk", "Bruce"));
//        this.superheroes.add(new Superhero("Thor", "Thor"));
//        this.superheroes.add(new Superhero("Dr. Strange", "Stephen"));
//        this.superheroes.add(new Superhero("Ironman", "Tony Stark"));
//        this.superheroes.add(new Superhero("The Hulk", "Bruce"));
//        this.superheroes.add(new Superhero("Thor", "Thor"));
//        this.superheroes.add(new Superhero("Dr. Strange", "Stephen"));


    }

    public String getApiBasUrl() {
        return API_BASE_URL;
    }

    public ArrayList<Superhero> getSuperheroes() {
        return this.superheroes;
    }
}
