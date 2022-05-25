import java.util.ArrayList;

public class Player {
    private String name;
    private String gender;
    private Room currentRoom;
    private ArrayList<Item> items;

    public Player(String name, Room currentRoom) {
        this.name = name;
        this.currentRoom = currentRoom;
        items = new ArrayList<>();
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

    private boolean hasItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) return true;
        }
        return false;
    }

    public boolean take(String itemName) {
        if (currentRoom.hasItem(itemName)) {
            items.add(currentRoom.getItem(itemName));
            return true;
        }
        return false;
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

}
