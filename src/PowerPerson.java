public class PowerPerson extends Person{
    private int power;
    private int health;

    public PowerPerson(String name, int power, int health){
        super(name);
        this.power = power;
        this.health = health;
    }

    public int getPower() {
        return power;
    }

    public int getHealth() {
        return health;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void johnnyText(){
        if(this.getName().equals("Johnny")){
            System.out.println();
            System.out.println("Johnny:");
            System.out.println("Well well ... look who's here.");
            System.out.println("After all those years you abandoned us.");
            System.out.println("Now you will get what you deserve...");
            System.out.println("Let's fight this out...  (yes/no)");
            System.out.println();
            System.out.print("> ");
        }
    }

    public void bearText(){
        if(this.getName().equals("bear")){
            System.out.println();
            System.out.println("A wild bear appears... (continue)");
            System.out.println();
            System.out.print("> ");
        }
    }
}
