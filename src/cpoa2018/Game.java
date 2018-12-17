package cpoa2018;

import java.util.*;

public class Game
{
  public static boolean winner(int c1, int c2)
  {
    int f1 = Card.cardToFace(c1), s1 = Card.cardToSuit(c1);
    int f2 = Card.cardToFace(c2), s2 = Card.cardToSuit(c2);
    return f1 > f2 || f1 == f2 && s1 > s2;
  }

  public static void main(String[] args)
  {
    // Initialize the game
    Random rand = new Random(System.currentTimeMillis());
    int[] D = Card.makeDeck();
    int top = 0;
    Card.shuffle(D,rand);
    Player pu  = new Player(); 
    pu.money = 5.0;
    Player pai = new Player(); 
    pai.money = 5.0;

    // Play 5 rounds w/o betting (ante only)
    for(int i = 0; i < 5; i++)
    {
      // Collect ante deal
      double pot = .10;
      pu.money -= 0.05;
      pai.money -= 0.05;
      pu.card = D[top++];
      pai.card = D[top++];  
      System.out.println("You have $" + pu.money 
	 + ", your opponent has $" + pai.money 
	 + ", and you see card " + Card.cardToString(pai.card));

      // Report outcome and give winner the pot
      System.out.println("You had a " + Card.cardToString(pu.card));
      if (winner(pu.card,pai.card))
      {
	pu.money += pot;
	System.out.println("You won!");
      }
      else
      {
	pai.money += pot;
	System.out.println("You lost!");
      }
    }
  }
}

