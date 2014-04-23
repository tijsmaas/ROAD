package helpers;

/**
 * Created by geh on 26-2-14.
 */
public class Pair<U, V>
{
    private U first;
    private V second;

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
