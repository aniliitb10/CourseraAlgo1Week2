import edu.princeton.cs.algs4.StdOut;

public class Client
{
  public static void main(String[] args)
  {
    Deque<Integer> id = new Deque<>();
    id.addLast(10);
    StdOut.println(id.isEmpty());
    StdOut.println(id.removeLast());
    id.addLast(5);
    StdOut.println(id.removeLast());
  }
}
