package ClassTest;

public class Pokemon {
    public String name;
    public int health, strength;

    Pokemon(String name) {
        this.name = name;
        this.health = 10;
        this.strength = 10;
    }

    public void damage(int damage) {
        this.health -= damage;
    }
}
