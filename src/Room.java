import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 * <p>
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * A "Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits.  The exits are labelled north,
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Room {
    private String description;
    private HashMap<String, Room> exits;
    private ArrayList<Item> items;
    private ArrayList<Person> persons;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     *
     * @param description The room's description.
     */
    public Room(String description) {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<>();
        persons = new ArrayList<>();
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void addPerson(Person person){persons.add(person);}

    public boolean hasPerson(){
        if(!persons.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * Define an exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     *
     * @param direction direction for which to add exit
     * @param neighbor  The exit
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return string met alle aanwezige uitgangen
     * bvb. "Exits: north west".
     */
    public String getExitString() {
        String returnString = "Exits: ";
        for (String direction : exits.keySet()) {
            returnString += " " + direction;
        }
        return returnString;
    }

    public String getItemsString() {
        if (!items.isEmpty()) {
            String returnString = "; it contains items:\n";
            for (Item item : items) {
                returnString += "   " + item.getLongDescription() + "\n";
            }
            return returnString;
        }
        return "";
    }

    public boolean hasItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) return true;
        }
        return false;
    }

    public Item getItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public Person getPerson(){
        return persons.get(0);
    }

    public String getLongDescription() {
        return "You are " + description + getItemsString() + "\n"  + getExitString();
    }

    public void removeItem(Item item){
        items.remove(item);
    }
}
