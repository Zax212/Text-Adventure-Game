
public class Item
{
    String name = "";
    String description = "";
    int weight = 0;
    public Item(String n, String d, int w)
    {
        name = n;
        description = d;
        weight = w;
    }

    public void setName (String n)
    {
        name = n;
    }

    public void  setDescription (String d)
    {
        description = d;
    }

    public void setWeight (int w)
    {
        weight = w;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public int getWeight()
    {
        return weight;
    }

    public String toString()
    {
        return name + ", ";
    }
}
