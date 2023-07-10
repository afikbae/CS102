package cardgame;

import java.util.Random;

// Cards - Maintains a collection of zero or more playing cards.
//         Provides facilities to create a full pack of 52 cards
//         and to shuffle the cards.
// @author Mehmet Akif Şahin
// date: 03.03.23
public class Cards
{
    final int NOOFCARDSINFULLPACK = 52;
    
    // properties
    Card[] cards;
    int    valid;  // number of cards currently in collection
    
    // constructors
    public Cards( boolean fullPack)
    {
        cards = new Card[ NOOFCARDSINFULLPACK ];
        valid = 0;
        
        if ( fullPack)
            createFullPackOfCards();
    }
    
    public boolean isEmpty ()
    {
        for (int i = 0; i < cards.length; i++)
        {
            if (cards[i] != null)
            {
                return false;
            }
        }
        return true;
    }
    // methods
    public Card getTopCard()
    {
        Card tmp;

        if ( valid <= 0)
            return null;
        else
        {
            valid--;
            tmp = cards[valid];
            cards[valid] = null;
            return tmp;
        }
    }
    
    public boolean addTopCard( Card c)
    {
        if ( valid < cards.length)
        {
            cards[valid] = c;   // should this be cloned?
            valid++;
            return true;
        }
        return false;
    }
    
    private void createFullPackOfCards()
    {
        valid = NOOFCARDSINFULLPACK;
        for (int i = 0; i < NOOFCARDSINFULLPACK; i++)
        {
            cards[i] = new Card(i);
        }
    }
    
    public void shuffle()
    {
        Random rand = new Random();

        for (int i = 0; i < 1000; i++)
        {
            int cardPos1 = rand.nextInt(NOOFCARDSINFULLPACK);
            int cardPos2 = rand.nextInt(NOOFCARDSINFULLPACK);
            
            Card temp = cards[cardPos1];
            cards[cardPos1] = cards[cardPos2];
            cards[cardPos2] = temp;
        }
    }
} // end class Cards
