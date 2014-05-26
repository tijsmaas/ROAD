package road.movementdts.helpers;

/**
 * Created by geh on 26-2-14.
 * A generic class to store a pair of values.
 * java.util.Pair already exists, but does not allow you to
 * set the values after initialization, this does.
 */
public class Pair<U, V>
{
    private U first;
    private V second;

    public Pair()
    {

    }

    public Pair(Class<U> uClass, Class<V> vClass)
    {

    }

    public Pair(U first, V second)
    {
        this.first = first;
        this.second = second;
    }

    public U getFirst() { return first; }
    public void setFirst(U first) { this.first = first; }

    public V getSecond() { return second; }
    public void setSecond(V second) { this.second = second; }
}
