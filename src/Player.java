import java.util.ArrayList;
import java.util.Stack;

public class Player {
    private String name;
    private String gender;
    private Room currentRoom;
    private ArrayList<Item> items;
    private double maxWeight = 8;
    private Stack<Room> history;
    private int power;
    private int health;

    public Player(String name, Room currentRoom) {
        this.name = name;
        this.currentRoom = currentRoom;
        items = new ArrayList<>();
        history = new Stack<>();
        history.add(currentRoom);
    }

    public String getGender() {
        return gender;
    }

    public boolean hasGender(){
        if(gender.equals("male") || gender.equals("female")){
            return true;
        }else{
            return false;
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public boolean hasItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) return true;
        }
        return false;
    }

    public boolean take(String itemName) {
        Item i = currentRoom.getItem(itemName);
        if(i instanceof PowerItem){
            power += ((PowerItem) i).getPower();
            System.out.println("Your power has increased to " + power);
            items.add(i);
            currentRoom.removeItem(i);
            return true;
        }else if (currentRoom.hasItem(itemName) && playerWeightChecker(i)) {
            items.add(i);
            currentRoom.removeItem(i);
            return true;
        }else{
            System.out.println("Item to heavy!");
            return false;
        }
    }

    public boolean drop(String itemName) {
        if (this.hasItem(itemName)) {
            for (Item item : items) {
                if (item.getName().equals(itemName)) {
                    if (items.remove(item)) {
                        if(item instanceof PowerItem){
                            power -= ((PowerItem) item).getPower();
                            System.out.println("Power decreased to " + power);
                        }
                        currentRoom.addItem(item);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String getLongDescription() {
        String desc = "Player " + name + " has at the moment ";
        if (items.isEmpty()) {
            desc += "no items in the bag";
        } else {
            desc += "following items in the bag: ";
            for (Item item : items) {
                desc += "\n   " + item.getLongDescription();
            }
        }
        return desc;
    }

    public boolean playerWeightChecker(Item item){
        double totalWeight = 0;
        for(Item i : items){
            totalWeight += i.getWeight();
        }
        if((item.getWeight() + totalWeight) <= maxWeight){
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public boolean eat(String itemName){
        if (this.hasItem(itemName)){
            for(Item i : items){
                if(i.getName().equals(itemName)){
                    if(i instanceof Edible){
                        Edible e = (Edible) i;
                        if(e.getHealth() > 0){
                            health += e.getHealth();
                            System.out.println("Health increased by " + e.getHealth());
                        }else if(e.getHealth() == 0){
                            maxWeight += 5;
                            System.out.println("Load capacity has increased by 5");
                        }else{
                            health += e.getHealth();
                            System.out.println("Health decreased by " + e.getHealth());
                        }
                        items.remove(i);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean stackEmpty(){
        return history.isEmpty();
    }

    public void addRoomToStack(Room room){
        history.add(room);
    }

    public Room goBack(){
        return history.pop();
    }

    public Room lookBack(){
        return history.peek();
    }

    public boolean hasKey(Item item) {
        for (Item i : items) {
            if (i.equals(item)) return true;
        }
        return false;
    }

}
