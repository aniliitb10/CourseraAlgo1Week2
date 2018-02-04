import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Permutation
{
  public static void main(String[] args)
  {
    int k = Integer.parseInt(args[0]);
    RandomizedQueue<String> rq = new RandomizedQueue<>();
    while(!StdIn.isEmpty())
    {
      rq.enqueue(StdIn.readString());
    }

    Iterator<String> itr = rq.iterator();

    while(k != 0)
    {
      StdOut.println(itr.next());
      k--;
    }
  }
}