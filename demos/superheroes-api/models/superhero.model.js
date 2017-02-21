/* globals module */

const props = ["name", "secretIdentity", "imgUrl", "story", "factions", "powers", "_id"];
const defaultImageUrl="https://www.fjackets.com/product_images/uploaded_images/superhero-costumes-for-men.jpg";

class Superhero {
    constructor(name, secretIdentity, imgUrl, story, factions, powers, id = null) {
        this.name = name;
        this.secretIdentity = secretIdentity;
        this.story = story;
        this.factions = factions;
        this.imgUrl = imgUrl || defaultImageUrl;
        this.powers = powers;
        this.id = id;
    }

    static fromModel(model) {
        const values =
            props.reduce((a, prop) => {
                a.push(model[prop]);
                return a;
            }, []);
        return new Superhero(...values);
    }

    static getSearchProperties() {
        return ["name", "secretIdentity", "story"];
    }
}

module.exports = Superhero;