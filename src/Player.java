import java.util.ArrayList;
import java.util.Stack;

public class Player {
    private String name;
    private String gender;
    private Room currentRoom;
    private ArrayList<Item> items;
    private double maxWeight = 10;
    private Stack<Room> history;
    private int power;

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
