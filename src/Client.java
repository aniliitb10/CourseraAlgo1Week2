public class Client
{
  public static void main(String[] args)
  {
    RandomizedQueue<Integer> iq = new RandomizedQueue<>();

    // Testing initialization
    iq.addFirst(5);
    iq.print();

    // Testing addFirst
    iq.addFirst(8);
    iq.addFirst(3);
    iq.addFirst(4);
    iq.print();

    // Testing addLast
    iq.addLast(7);
    iq.addLast(9);
    iq.addLast(0);
    iq.print();

    // Testing removeFirst
    iq.removeFirst();
    iq.removeFirst();
    iq.print();

    // Testing removeLast and Array shrink
    iq.removeLast();
    iq.removeLast();
    iq.removeLast();
    iq.removeLast();
    iq.print();

    // Testing addLast after all these operations
    iq.addLast(23);
    iq.addLast(2);
    iq.addLast(20);
    iq.addLast(24);
    iq.addLast(45);
    iq.addLast(67);
    iq.addLast(22);
    iq.print();

    // Testing removeFirst and Array shrink
    iq.removeFirst();
    iq.removeFirst();
    iq.removeFirst();
    iq.removeFirst();
    iq.removeFirst();
    iq.removeFirst();
    iq.removeFirst();
    iq.print();

    //Testing edge-cases
    iq.removeLast();
    iq.print();
    iq.addLast(4);
    iq.print();
    iq.removeFirst();
    iq.print();
  }
}
