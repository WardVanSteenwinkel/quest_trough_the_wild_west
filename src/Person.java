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

    public void generateText(){
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
        System.out.println();
        System.out.println("Harry: If a green man lives in a green house, a purple man lives in a purple house, a blue man lives in a blue house,");
        System.out.println("Harry: Who lives in a White house? hehehehe");
        System.out.println();
        System.out.print("> ");
    }

    public void harryAnswer(){
        System.out.println();
        System.out.println("Harry: YES!!! HEHEHEHEHE");
        System.out.println("Harry: As a reward I will give my Magic Bracelet");
        System.out.println("Harry: It increases your power by 3");
        System.out.println("Harry: hehehe");
        System.out.println();
    }

    public void harryWrong(){
        System.out.println();
        System.out.println("Harry: WRONG!!! HEHEHEHE");
        System.out.println();
    }
}
