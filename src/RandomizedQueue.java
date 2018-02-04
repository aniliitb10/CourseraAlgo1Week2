import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item>
{
  private Item[] items;
  private int lastIndex = 1;
  private int firstIndex = 0;
  private int size = 0;

  public RandomizedQueue()
  {
    items = (Item[]) new Object[2];
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
    Item[] newItems = (Item[] ) new Object[oldArrLength * 2];
    int firstIndexToCopyInNewArrAt = (allocateFreeSpaceInBeginning ? oldArrLength : firstIndex + 1);

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

    Item[] newItems = (Item[] ) new Object[capacity];

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
  public void enqueue(Item item)
  {
    if (item == null)
    {
      throw new IllegalArgumentException("null ptr");
    }

    if (firstIndex == -1)
    {
      expand(true);
    }

    items[firstIndex--] = item;
    size++;
  }

  private void swapItem(int leftIndex, int rightIndex)
  {
    Item backUpItem = items[leftIndex];
    items[leftIndex] = items[rightIndex];
    items[rightIndex] = backUpItem;
  }

  // remove and return the item from the end
  public Item dequeue()
  {
    if (isEmpty())
    {
      throw new NoSuchElementException("no such element");
    }

    if (size== items.length / 4)
    {
      shrink();
    }

    int randomIndex = StdRandom.uniform(firstIndex +  1, lastIndex);
    swapItem(randomIndex, --lastIndex);

    Item item = items[lastIndex];
    items[lastIndex] = null;
    size--;
    return item;
  }

  // return a random item (but do not remove it)
  public Item sample()
  {
    if (isEmpty())
    {
      throw new NoSuchElementException("no such element");
    }
    return items[StdRandom.uniform(firstIndex + 1, lastIndex)];
  }

  @Override
  public Iterator<Item> iterator()
  {
    return new RandomizedQueueIterator();
  }

  private class RandomizedQueueIterator implements Iterator<Item>
  {
    int[] indices;
    int nextIndex = 0;

    public RandomizedQueueIterator()
    {
      indices = new int[size];
      int counter = 0;

      for (int index = firstIndex + 1; index < lastIndex; ++index)
      {
        indices[counter++] = index;
      }
      StdRandom.shuffle(indices);
    }

    @Override
    public boolean hasNext()
    {
      return nextIndex != indices.length;
    }

    @Override
    public Item next()
    {
      if (!hasNext())
      {
        throw new NoSuchElementException("no such elements");
      }

      return items[indices[nextIndex++]];
    }
  }
}
