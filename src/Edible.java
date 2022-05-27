public class Edible extends Item{
    private int health; // 0 --> no health

    public Edible(String name, String description, double weight, int health){
        super(name, description, weight);
        this.health = health;
    }

    public int getHealth() {
        return health;
    }
}
