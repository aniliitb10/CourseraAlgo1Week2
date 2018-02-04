import java.util.NoSuchElementException;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item>
{
  private int size = 0;
  private Node first = null;
  private Node last = null;

  private class Node
  {
    private Item item = null;
    private Node next = null;
    private Node prev = null;

    Node(Item item)
    {
      this.item = item;
    }

    Node(Item item, Node next)
    {
      this(item);
      this.next = next;
    }

    Node (Item item, Node next, Node prev)
    {
      this(item, next);
      this.prev  = prev;
    }
  }

  // is the deque empty?
  public boolean isEmpty()
  {
    return first == null;
  }

  // return the number of items on the deque
  public int size()
  {
    return size;
  }

  // add the item to the front
  public void addFirst(Item item)
  {
    if (item == null)
    {
      throw new IllegalArgumentException("Arg is null");
    }

    if (isEmpty())
    {
      first = new Node(item);
      last = first;
    }
    else
    {
      first = new Node(item, first);
      first.next.prev = first;
    }
    size++;
  }

  // add the item to the end
  public void addLast(Item item)
  {
    if (item == null)
    {
      throw new IllegalArgumentException("Arg is null");
    }

    if (isEmpty())
    {
      last = new Node(item);
      first = last;
    }
    else
    {
      last.next = new Node(item, null, last);
      last = last.next;
    }
    size++;
  }

  // remove and return the item from the front
  public Item removeFirst()
  {
    if (isEmpty())
    {
      throw new NoSuchElementException("Deque is empty");
    }

    Item item = first.item;
    first = first.next;

    if (first != null)
    {
      first.prev = null;
    }
    size--;
    return item;
  }

  // remove and return the item from the end
  public Item removeLast()
  {
    if (isEmpty())
    {
      throw new NoSuchElementException("Deque is empty");
    }

    Item item = last.item;
    last = last.prev;
    if (last != null)
    {
      last.next = null;
    }
    else
    {
      first = null;
    }
    size--;
    return item;
  }

  // return an iterator over items in order from front to end
  @Override
  public Iterator<Item> iterator()
  {
    return new DequeIterator();
  }

  private class DequeIterator implements Iterator<Item>
  {
    private Node current = first;

    @Override
    public boolean hasNext()
    {
      return current != null;
    }

    @Override
    public Item next()
    {
      if(!hasNext())
      {
        throw new NoSuchElementException("No more elements in Deque");
      }

      Item item = current.item;
      current = current.next;
      return item;
    }

    @Override
    public void remove()
    {
      throw new UnsupportedOperationException("remove is not supported");
    }
  }
}
