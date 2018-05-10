import java.util.*;
public class Room
{
    String description;
    Item item;
    HashMap<String, Room> rooms;
    public Room(String d)
    {
        description = d;
        item = null;
        rooms = new HashMap();
    }

    public Room(String d, Item i)
    {
        description = d;
        item = i;
        rooms = new HashMap();
    }

    public String getDescription()
    {
        return description;
    }

    public Item getItem()
    {
        return item;
    }

    public void addItem(Item i)
    {
        item = i;
    }

    public boolean hasItem()
    {
        return !(item == null);
    }

    public void addNeighbor (String direction, Room r)
    {
        rooms.put(direction, r);
    }

    public Room getNeighbor (String direction)
    {
        return rooms.get(direction);
    }

    public Item removeItem()
    {
        Item i = item;
        item = null;
        return i;
    }

    public String getLongDescription()
    {
        String a = "You are in " + description;
        String b = "";
        if(item != null)
            b = " You see " + item.getDescription() +" ("+ item.getWeight() + "weight) ";
        return a + b;

    }
}
