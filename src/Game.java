import java.text.DecimalFormat;
import java.util.*;

/**
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.  Users
 * can walk around some scenery. That's all. It should really be extended
 * to make it more interesting!
 * <p>
 * To play this game, create an instance of this class and call the "play"
 * method.
 * <p>
 * This main class creates and initialises all the others: it creates all
 * rooms, creates the parser and starts the game.  It also evaluates and
 * executes the commands that the parser returns.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game {
    private Parser parser;
    private Player player;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        Room LosAngeles, DeathValley, MexicanBorder, Vancouver, PacificRoute, MountStHelens, GhostTown, LA_Beach, Vancouver_Beach, LA_UnderWater, VANCOUVER_UnderWater, Forest;
        Item gun, magicCookie, waterBottle, oxygenMask, magicBracelet;
        Person harry, johnny, horse;

        // create the items
        gun = new PowerItem("gun", "this is a powerfun handgun", 3, 2);
        magicCookie = new Edible("magicCookie", "your max load capacity will double if you eat this.", 1);
        waterBottle = new Item("waterBottle", "this item is ideal for surviving in hot places", 2);
        oxygenMask = new Item("oxygenMask", "this item lets you breath underwater", 5);
        magicBracelet = new PowerItem("magicBracelet", "this bracelet increases your power", 0, 3);

        // create the rooms
        LosAngeles = new Room("are in Los Angeles. The largest city in the West");
        DeathValley = new SpecialRoom("are in Death Valley, one of the hottest places on earth", waterBottle);
        MexicanBorder = new Room("are at the Mexican Border");
        Vancouver = new Room("are in Vancouver");
        PacificRoute = new Room("are on the Pacific Route");
        MountStHelens = new Room("are on top of Mount St. Helens, an ancient Vulcano");
        GhostTown = new Room("are in a GhostTown, this place looks deserted...");
        LA_Beach = new Room("are at LA Beach");
        Vancouver_Beach = new Room("are at Vancouver Beach");
        LA_UnderWater = new SpecialRoom("are underwater", oxygenMask);
        VANCOUVER_UnderWater = new SpecialRoom("are underwater", oxygenMask);
        Forest = new Room("are in the forest. You can smell the fresh air from the trees");


        // initialise room exits
        LosAngeles.setExit("north", PacificRoute);
        LosAngeles.setExit("south", MexicanBorder);
        LosAngeles.setExit("west", LA_Beach);
        LosAngeles.setExit("east", DeathValley);
        GhostTown.setExit("north", DeathValley);
        DeathValley.setExit("south", GhostTown);
        DeathValley.setExit("west", LosAngeles);
        MexicanBorder.setExit("north", LosAngeles);
        LA_Beach.setExit("down", LA_UnderWater);
        LA_Beach.setExit("east", LosAngeles);
        LA_UnderWater.setExit("up", LA_Beach);
        LA_UnderWater.setExit("north", VANCOUVER_UnderWater);
        PacificRoute.setExit("south", LosAngeles);
        PacificRoute.setExit("north", Vancouver);
        Vancouver.setExit("south", PacificRoute);
        Vancouver.setExit("west", Vancouver_Beach);
        Vancouver.setExit("up", MountStHelens);
        Vancouver.setExit("east", Forest);
        Vancouver_Beach.setExit("east", Vancouver);
        MountStHelens.setExit("down", Vancouver);
        VANCOUVER_UnderWater.setExit("up", Vancouver_Beach);
        VANCOUVER_UnderWater.setExit("south", LA_UnderWater);
        Forest.setExit("west", Vancouver);

        //create persons
        harry = new Person("Harry");
        harry.addItem(magicBracelet);
        johnny = new PowerPerson("Johnny", 15, 100);
        horse = new Person("horse");


        //initialise items and persons
        VANCOUVER_UnderWater.addItem(gun);
        PacificRoute.addItem(magicCookie);
        LosAngeles.addItem(waterBottle);
        MexicanBorder.addItem(oxygenMask);
        PacificRoute.addPerson(harry);
        GhostTown.addPerson(johnny);
        Vancouver_Beach.addPerson(horse);


        this.player = new Player("player", LosAngeles);
    }

    /**
     * Main play routine.  Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye!");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("\n" +
                "░██████╗░██╗░░░██╗███████╗░██████╗████████╗  ████████╗██████╗░░█████╗░██╗░░░██╗░██████╗░██╗░░██╗\n" +
                "██╔═══██╗██║░░░██║██╔════╝██╔════╝╚══██╔══╝  ╚══██╔══╝██╔══██╗██╔══██╗██║░░░██║██╔════╝░██║░░██║\n" +
                "██║██╗██║██║░░░██║█████╗░░╚█████╗░░░░██║░░░  ░░░██║░░░██████╔╝██║░░██║██║░░░██║██║░░██╗░███████║\n" +
                "╚██████╔╝██║░░░██║██╔══╝░░░╚═══██╗░░░██║░░░  ░░░██║░░░██╔══██╗██║░░██║██║░░░██║██║░░╚██╗██╔══██║\n" +
                "░╚═██╔═╝░╚██████╔╝███████╗██████╔╝░░░██║░░░  ░░░██║░░░██║░░██║╚█████╔╝╚██████╔╝╚██████╔╝██║░░██║\n" +
                "░░░╚═╝░░░░╚═════╝░╚══════╝╚═════╝░░░░╚═╝░░░  ░░░╚═╝░░░╚═╝░░╚═╝░╚════╝░░╚═════╝░░╚═════╝░╚═╝░░╚═╝\n" +
                "\n" +
                "████████╗██╗░░██╗███████╗  ░██╗░░░░░░░██╗███████╗░██████╗████████╗\n" +
                "╚══██╔══╝██║░░██║██╔════╝  ░██║░░██╗░░██║██╔════╝██╔════╝╚══██╔══╝\n" +
                "░░░██║░░░███████║█████╗░░  ░╚██╗████╗██╔╝█████╗░░╚█████╗░░░░██║░░░\n" +
                "░░░██║░░░██╔══██║██╔══╝░░  ░░████╔═████║░██╔══╝░░░╚═══██╗░░░██║░░░\n" +
                "░░░██║░░░██║░░██║███████╗  ░░╚██╔╝░╚██╔╝░███████╗██████╔╝░░░██║░░░\n" +
                "░░░╚═╝░░░╚═╝░░╚═╝╚══════╝  ░░░╚═╝░░░╚═╝░░╚══════╝╚═════╝░░░░╚═╝░░░");
        System.out.println();
        System.out.println("Quest Trough The West is a text-based adventure game.");
        System.out.println();
        System.out.println("To start, please enter your name:");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        String playerName = scanner.nextLine();
        player.setName(playerName);
        System.out.println();
        System.out.println("Please select your difficulty level: (easy/medium/hard)");
        System.out.print("> ");
        String playerPower = scanner.nextLine();
        if(playerPower.equals("easy")){
            player.setPower(50);
            player.setHealth(100);
        }else if(playerPower.equals("medium")){
            player.setPower(5);
            player.setHealth(80);
        }else if(playerPower.equals("hard")){
            player.setPower(0);
            player.setHealth(60);
        }
        System.out.println();
        System.out.println("Please enter your gender: (male/female)");
        System.out.print("> ");
        String playerGender = scanner.nextLine();
        String playerGenderLower = playerGender.toLowerCase();
        if(playerGenderLower.equals("male") || playerGenderLower.equals("female")){
            player.setGender(playerGender);
        }else {
            player.setGender("male");
        }
        if(player.hasGender()){
            System.out.println();
            System.out.println("Hello " + playerName + ", not long ago you were part of a outlaw group called: 'The Pissed Off Bastards of Bloomington'.");
        }
        if(playerGenderLower.equals("male")){
            System.out.println("But after many years of looting and traveling trough the West you found your true love Lauren.");
        }
        if(playerGenderLower.equals("female")){
            System.out.println("But after many years of looting and traveling trough the West you found your true love William.");
        }
        if(player.hasGender()){
            System.out.println("Not long after that you decided to leave the group to start a normal life with your lover.");
            System.out.println("But, the leader 'Johnny O' Jackson' was furious, and he decided to take revenge...");
            System.out.println();
            System.out.println("He and the rest of those bastards kidnapped your significant other!");
            System.out.println("Now it's all up to you to find your partner...");
            System.out.println("");
            System.out.println("Possible actions:");
            System.out.println(parser.showCommands());
            System.out.println();
            System.out.println("Example: Type 'go north' to go north!");
            System.out.println();
            System.out.println("Type '" + CommandWord.HELP.toString() + "' if you need help.");
            System.out.println();
            printLocationInfo();
        }
    }

    private void printLocationInfo() {
        Room r = player.getCurrentRoom();
        System.out.println(r.getLongDescription());
        System.out.println(player.getLongDescription());
        if(r.hasPerson()){
            Person p = r.getPerson();
            if(p.getName().equals("Harry")){
                personHarry();
            }
            if(p.getName().equals("Johnny")){
                personJohnny();
            }
        }
        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     *
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;
        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;
            case HELP:
                printHelp();
                break;
            case LOOK:
                look();
                break;
            case EAT:
                eat(command);
                break;
            case TAKE:
                take(command);
                break;
            case DROP:
                drop(command);
                break;
            case STATS:
                stats();
                break;
            case GO:
                goRoom(command);
                break;
            case BACK:
                back(); //kleine bug eerste keer moet je twee keer back gebruiken
                break;
            case QUIT:
                wantToQuit = quit(command);
                break;
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp() {
        System.out.println("Player " + player.getName() + " is lost and alone, and wanders");
        System.out.println("around at the West.");
        System.out.println();
        System.out.println("Possible command words are:   " + parser.showCommands());
        System.out.println();
    }

    private void look() {
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    private void eat(Command command) {
        if(!command.hasSecondWord()){
            System.out.println("Eat what?");
        }
        String edible = command.getSecondWord();
        if(player.hasItem(edible)){
            if(player.eat(edible)){
                player.setMaxWeight(15);
                System.out.println("Your carry load has increased to " + player.getMaxWeight() + " kg!");
            }else{
                System.out.println("This is not eatable.");
            }
        }else{
            System.out.println("You don't have this item...");
        }
    }

    /**
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no room here!");
        }else if(nextRoom instanceof SpecialRoom){
            SpecialRoom lockedRoom = (SpecialRoom) nextRoom;
            Item key = lockedRoom.getKey();
            if(player.hasKey(key)){
                System.out.println("You have a " + key.getName() + "!");
                player.setCurrentRoom(nextRoom);
                player.addRoomToStack(nextRoom);
                printLocationInfo();
            }else{
                System.out.println("You need a " + key.getName() + " to enter.");
            }
        }else {
            player.setCurrentRoom(nextRoom);
            player.addRoomToStack(nextRoom);
            printLocationInfo();
        }
    }

    private void take(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take...
            System.out.println("Take what?");
            return;
        }
        String itemName = command.getSecondWord();
        if (player.take(itemName)) {
            printLocationInfo();
        } else {
            System.out.println("There is no item here with the name " + itemName);
        }
    }

    private void drop(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Drop what?");
            return;
        }
        String itemName = command.getSecondWord();
        if (player.drop(itemName)) {
            printLocationInfo();
        } else {
            System.out.println("There is no item here with the name " + itemName);
        }
    }


    private void stats(){
        System.out.println("Name: " + player.getName() + "; Power: " + player.getPower() + "; health: " + player.getHealth() + "; Carry-load: " + player.getMaxWeight());
    }


    private void back(){
        if(player.stackEmpty()){
            System.out.println("You are at the beginning");
        }else{
//            if(player.getCurrentRoom().equals(player.lookBack())){
//                player.setCurrentRoom(player.goBack());
//                back();
//            }
            player.setCurrentRoom(player.goBack());
            printLocationInfo();
        }
    }

    private void personHarry(){
        Room r = player.getCurrentRoom();
        Person p = r.getPerson();
        if (p.hasItems()){
            p.harryText();
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            if(answer.equals("yes")){
                p.harryRiddle();
                String answerHarry = scanner.nextLine();
                String answerLower = answerHarry.toLowerCase();
                if(answerLower.equals("president") || answerLower.equals("the president")){
                    p.harryAnswer();
                    r.addItem(p.dropItem("magicBracelet"));
                    player.take("magicBracelet");
                }else{
                    p.harryWrong();
                }
            }
            look();
        }
    }

    private boolean fight(PowerPerson pp){
        DecimalFormat df = new DecimalFormat("###.#");
        int playerHealth = player.getHealth();
        int ppHealth = pp.getHealth();
        boolean notDead = (playerHealth > 0 && ppHealth > 0);

        while (notDead){
            double r = (Math.random())+0.5;
            double playerAttack = (player.getPower())*r;
            double ppAttack = (pp.getPower())*r;
            System.out.println("Press f to fight: (f)");
            System.out.println();
            System.out.print("> ");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            if(answer.equals("f")){
                ppHealth -= playerAttack;
                System.out.println("--------------------------------");
                System.out.println("You hit! --- " + df.format(playerAttack) + " damage!");
                System.out.println(pp.getName() + " " + df.format(ppHealth) + " health");
                System.out.println("--------------------------------");
                if(ppHealth <= 0){
                    return true;
                }else{
                    playerHealth -= ppAttack;
                    System.out.println("--------------------------------");
                    System.out.println(pp.getName() + " hit! --- " + df.format(ppAttack) + " damage!");
                    System.out.println(player.getName() + " " + df.format(playerHealth) + " health");
                    System.out.println("--------------------------------");

                }
            }
            if(!answer.equals("f")){
                personJohnny();
            }
            notDead = (playerHealth > 0 && ppHealth > 0);
        }

        if(playerHealth > ppHealth){
            return true; //won
        }return false; //lost
    }


    private void personJohnny(){
        Room r = player.getCurrentRoom();
        Person p = r.getPerson();
        ((PowerPerson) p).johnnyText();
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        if(answer.equals("yes")){
            if(fight((PowerPerson) p)){
                System.out.println("You defeated " + p.getName() + "!!!");
                System.out.println("The rest off the gang fled...");
                System.out.println("...");
                if(player.getGender().equals("male")){
                    System.out.println("You saved Lauren!!!");
                }else{
                    System.out.println("You saved William!!!");
                }
                System.out.println("THE END!");
                System.out.println("Made by Ward Van Steenwinkel");
                System.out.println();
                System.out.println("Press to end game: (quit)");
            }else{
                System.out.println("You lost");
                System.out.println("Press to end game: (quit)");
            }
        }else if(answer.equals("no")){
            System.out.println("??");
        }
    }




    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     *
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;  // signal that we want to quit
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
