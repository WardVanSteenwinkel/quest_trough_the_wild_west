public class PowerItem extends Item{
    private int power;

    public PowerItem(String name, String description, double weight, int power){
        super(name, description, weight);
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
