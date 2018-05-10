import java.io.*;
import java.util.*;
public class Game
{
    ArrayList<Item> held;
    Room currentRoom;
    String currentMessage;
    Stack<Room> rooms = new Stack();
    public Game()
    {
        held = new ArrayList();
        createRooms();
        setWelcomeMessage();
    }

    private void createRooms()
    {
        Item[] item = {new Item("a kitchen knife", " a sharp kitchen knife ",20), new Item("a broken pencil", " a broken pencil ", 1), new Item("a can of soup ", " an unopened can of soup ", 5), new Item("a lamp", " a tall lamp missing the shade ", 60), new Item("a baseball bat", " an old baseball bat ",30)};
        Room Garage = new Room(" in a garage", item[4]);
        Room Living = new Room(" in the living room", item[3]);
        Room Kitchen = new Room(" in the kitchen", item[0]);
        Room Pantry = new Room(" in the the pantry", item[2]);
        Room Garden = new Room(" in the garden");
        Room Yard = new Room(" in the yard");
        Room Office = new Room(" in the office", item[1]);

        currentRoom = Garage;

        Garage.addNeighbor("north", Living);

        Living.addNeighbor("north", Kitchen);
        Living.addNeighbor("east", Office);
        Living.addNeighbor("west", Yard);

        Kitchen.addNeighbor("north", Pantry);
        Kitchen.addNeighbor("west", Garden);
        Kitchen.addNeighbor("south", Living);

        Pantry.addNeighbor("south", Kitchen);

        Garden.addNeighbor("east", Kitchen);
        Garden.addNeighbor("south", Yard);

        Yard.addNeighbor("north", Garden);
        Yard.addNeighbor("east", Living);

        Office.addNeighbor("west", Living);

    }

    private void setWelcomeMessage()
    {
        currentMessage = "Welcome to the zombie apocolypse, get out of the house and find any useful items on the way.";

    }

    public String getMessage()
    {
        return currentMessage;
    }

    public void help ()
    {
        currentMessage = "Look for items useful in a zombie apocolypse";
    }

    public void look()
    {
        currentMessage = currentRoom.getLongDescription();
    }

    public void move(String direction)
    {
        Room r = currentRoom.getNeighbor(direction);
        if (r == null){
            currentMessage = "There is no room to the "+ direction;
        }
        else{
            rooms.push(currentRoom);
            currentRoom = r;
            currentMessage = ("Went "+ direction+". "+currentRoom.getLongDescription());
        }
    }

    public boolean gameOver()
    {
        if(currentRoom.getDescription().equals(" in the yard") && (checkForItem("a can of soup ") != null)){
            currentMessage = " Game Over. ";
            return true;
        }
        else{
            return false;
        }
    }

    public void take(){
        Item item = currentRoom.getItem();
        if (item == null)
        {
            currentMessage = " There is No Item in this room. ";
        }
        else if (item.getWeight() >= 50)
        {
            currentMessage = item.getName() + "is too heavy";
        }
        else
        {
            held.add(currentRoom.removeItem());
            currentMessage = "You now have " + item.getName();
        }
    }

    private Item checkForItem (String name)
    {
        for (Item i : held)
        {
            if(i.getName().contains(name))
            {
                return i;
            }
        }
        return null;
    }

    public void drop (String name)
    {
        Item i = checkForItem(name);
        if(i== null)
        {
            currentMessage = "You are not holding "+ name;
        }
        else
        {
            currentRoom.addItem(i);
            held.remove(i);
            currentMessage = "You dropped "+ name;
        }
    }

    public void show()
    {
        currentMessage = held.toString() ;
    }

    public void backup()
    {
        if(rooms.isEmpty())
        {
            currentMessage = "There is no room to backup to.";
        }
        else
            currentRoom = rooms.pop();
        currentMessage = ("backed up. "+currentRoom.getLongDescription());
    }

    public static void main(String[] args)
    {
        Game game = new Game();
        System.out.println(game.getMessage());
        game.help();
        System.out.println(game.getMessage());
        game.take();
        System.out.println(game.getMessage());
        game.move("north");
        System.out.println(game.getMessage());
        game.move("east");
        System.out.println(game.getMessage());
        game.take();
        System.out.println(game.getMessage());
        game.look();
        System.out.println(game.getMessage());
        game.move("west");
        System.out.println(game.getMessage());
        game.drop("a broken pencil");
        System.out.println(game.getMessage());
        game.move("north");
        System.out.println(game.getMessage());
        game.move("north");
        System.out.println(game.getMessage());
        game.take();
        System.out.println(game.getMessage());
        game.move("south");
        System.out.println(game.getMessage());
        game.move("west");
        System.out.println(game.getMessage());
        game.move("south");
        System.out.println(game.getMessage());
        if (game.gameOver())
            System.out.println(game.getMessage());
        else
            System.out.println(game.getMessage());

    }

}
