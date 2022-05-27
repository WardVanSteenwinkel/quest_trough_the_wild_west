import java.util.ArrayList;

public class Person {
    private String name;
    private Room currentRoom;
    private ArrayList<Item> items;

    public Person(String name) {
        this.name = name;
        items = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public boolean hasItems(){
        if(!items.isEmpty()) return true;
        return false;
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

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public Item dropItem(String name){
        for(Item i : items){
            if(i.getName().equals(name)){
                items.remove(i);
                return i;
            }
        }
        return null;
    }

    public void addItem(Item item){
        this.items.add(item);
    }

    public void harryText(){
        if(this.getName().equals("Harry")){
            System.out.println();
            System.out.println("Here at the Pacific Route are a lot off wanderers.");
            System.out.println("One of them is Harry...");
            System.out.println("Harry: 'Hello there, do you want to hear a riddle? hehehe' (yes/no)");
            System.out.println();
            System.out.print("> ");
        }
    }

    public void harryRiddle(){
        if(this.getName().equals("Harry")){
            System.out.println();
            System.out.println("Harry: If a green man lives in a green house, a purple man lives in a purple house, a blue man lives in a blue house,");
            System.out.println("Harry: Who lives in a White house? hehehehe");
            System.out.println();
            System.out.print("> ");
        }
    }

    public void harryAnswer(){
        if(this.getName().equals("Harry")){
            System.out.println();
            System.out.println("Harry: YES!!! HEHEHEHEHE");
            System.out.println("Harry: As a reward I will give my Magic Bracelet");
            System.out.println("Harry: It increases your power by 3");
            System.out.println("Harry: hehehe");
            System.out.println();
        }
    }

    public void harryWrong(){
        if(this.getName().equals("Harry")){
            System.out.println();
            System.out.println("Harry: WRONG!!! HEHEHEHE");
            System.out.println();
        }
    }

    public void magicianText(){
        if(this.getName().equals("magician")){
            System.out.println();
            System.out.println("                 ooO\n" +
                    "                     ooOOOo\n" +
                    "                   oOOOOOOoooo\n" +
                    "                 ooOOOooo  oooo\n" +
                    "                /vvv\\\n" +
                    "               /V V V\\ \n" +
                    "              /V  V  V\\   " +
                    "             /         \\  " +
                    "            /           \\   " +
                    "          /               \\   " +
                    "__       /                 \\    " +
                    "/\\     /                     \\  " +
                    "                                   ");
            System.out.println("Magician: huh?");
            System.out.println("Oh hello visitor, welcome to Mount St Helens.");
            System.out.println("Did you know this is a vulcano?");
            System.out.println("");
            System.out.println("What brings you here? (lover/power/health)");
            System.out.println();
            System.out.print("> ");
        }
    }

    public void magicianRiddle(){
        if(this.getName().equals("magician")){
            System.out.println();
            System.out.println("All men desire power haha...");
            System.out.println("I can give you power, but you have to solve my riddle.");
            System.out.println();
            System.out.println("When I burst of anger my tears scold the earth and my breath darkens the sky. What am I?");
            System.out.println();
            System.out.print("> ");
        }
    }

    public void magicianRidlleAnswer(){
        if(this.getName().equals("magician")){
            System.out.println();
            System.out.println("Yes! Very good.");
            System.out.println("Here's your power.");
            System.out.println();
        }
    }

    public void magicianLover(){
        if(this.getName().equals("magician")){
            System.out.println();
            System.out.println("You lost you lover didn't you?");
            System.out.println("This will help you...");
            System.out.println();
        }
    }
}
