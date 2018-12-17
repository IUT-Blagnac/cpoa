package cpoa2018;



import java.util.*;

public class Card
{
  public static int makeCard(int face, int suit) { return 20*suit + face; }
  public static int cardToSuit(int card) { return card/20; }
  public static int cardToFace(int card) { return card % 20; }
  public static String cardToString(int card)
  {
    String[] suits = {"\u2663","\u2666","\u2665","\u2660"};
    String[] faces = {"","","2","3","4","5","6","7","8","9","10","J","Q","K","A"};
    return faces[cardToFace(card)] + suits[cardToSuit(card)];
  }
  public static int[] makeDeck()
  {
    int[] D = new int[52];
    int i = 0;
    for(int s = 0; s < 4; s++)
      for(int f = 2; f <= 14; f++)
	D[i++] = makeCard(f,s);
    return D;
  }
  public static void shuffle(int[] D, Random rand)
  {
    for(int k = 0; k < 200; k++)
    {
      int i = rand.nextInt(52), j = rand.nextInt(52);
      int t = D[i];
      D[i] = D[j];
      D[j] = t;
    }
  }
  public static void main(String[] args)
  {
    Random rand = new Random(System.currentTimeMillis());
    int[] D = makeDeck();
    shuffle(D,rand);
    for(int i = 0; i < 52; i++)
      System.out.println(cardToString(D[i]));
  }
}

