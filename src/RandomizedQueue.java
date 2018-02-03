import edu.princeton.cs.algs4.StdOut;

import java.security.SignatureException;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item>
{
  private Item[] items;
  private int lastIndex = 0;
  private int firstIndex = 0;
  private int size = 0;

  public void print()
  {
    for (Integer index  = firstIndex+1;  index < lastIndex; ++index)
    {
      StdOut.print(items[index] + " ");
    }
    StdOut.println(":: size: " + size() + ", array size: " + items.length);
  }
  public RandomizedQueue()
  {
    items = (Item[]) new Object[1];
  }

  // is the deque empty?
  public boolean isEmpty()
  {
    return size() == 0;
  }

  // return the number of items on the deque
  public int size()
  {
    return size;
  }

  private void expand(boolean allocateFreeSpaceInBeginning)
  {
    final int oldArrLength = items.length;
    Item[] newItems = (Item[] ) new Object[oldArrLength * 2] ;
    int firstIndexToCopyInNewArrAt = (allocateFreeSpaceInBeginning ? oldArrLength : 0);

    for (int index = firstIndex + 1; index < lastIndex; ++index)
    {
      newItems[firstIndexToCopyInNewArrAt++] = items[index];
    }

    if (allocateFreeSpaceInBeginning)
    {
      firstIndex = oldArrLength - 1;
      lastIndex = oldArrLength + size;
    }
    items = newItems;
  }

  private void shrink()
  {
    int capacity = items.length / 2;
    if (capacity == 2)
    {
      return;
    }

    Item[] newItems = (Item[] ) new Object[capacity] ;

    int counter = 0;

    for (int index=(firstIndex+1); index < lastIndex; ++index)
    {
      newItems[counter++] = items[index];
    }
    items = newItems;
    firstIndex = -1;
    lastIndex =  size;
  }

  // add the item to the front
  public void addFirst(Item item)
  {
    if (item == null)
    {
      throw new IllegalArgumentException("null ptr");
    }

    if (firstIndex == -1)
    {
      expand(true);
    }

    if (isEmpty())
    {
      lastIndex += 1;
    }

    items[firstIndex--] = item;
    size++;
  }

  // add the item to the end
  public void addLast(Item item)
  {
    if (item == null)
    {
      throw new IllegalArgumentException("null ptr");
    }

    if (lastIndex == items.length)
    {
      expand(false);
    }
    items[lastIndex++] = item;
    size++;
  }

  // remove and return the item from the front
  public Item removeFirst()
  {
    if (isEmpty())
    {
      throw new NoSuchElementException("no elements");
    }

    if ((size() != 4) && (size() == items.length / 4))
    {
      shrink();
    }

    Item item = items[++firstIndex];
    items[firstIndex] = null;
    size--;
    return item;
  }

  // remove and return the item from the end
  public Item removeLast()
  {
    if (isEmpty())
    {
      throw new NoSuchElementException("no such element");
    }

    if ((size() != 4) && (size() == items.length / 4))
    {
      shrink();
    }

    Item item = items[--lastIndex];
    items[lastIndex] = null;
    size--;
    return item;
  }

}
