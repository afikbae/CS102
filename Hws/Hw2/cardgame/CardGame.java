package cardgame;

import java.time.temporal.TemporalUnit;
import java.util.ArrayList;

// Cardgame
// author:
// date:
public class CardGame
{
    // properties
    Cards             fullPack;
    ArrayList<Player> players;
    ScoreCard         scoreCard;
    Cards[]           cardsOnTable;
    int               roundNo;
    int               turnOfPlayer;
    
    // constructors
    public CardGame( Player p1, Player p2, Player p3, Player p4)
    {
        players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        scoreCard = new ScoreCard(players.size());
        cardsOnTable = new Cards[players.size()];
        for (int i = 0; i < cardsOnTable.length; i++)
        {
            cardsOnTable[i] = new Cards(false);
        }
        roundNo = 1;
        turnOfPlayer = 0;
        fullPack = new Cards(true);
        
        fullPack.shuffle();
        for (int i = 0; i < 13; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                players.get(j).add(fullPack.getTopCard());
            }
        }
    }
    
    // methods
    public boolean playTurn( Player p, Card c)
    {
        if (!isTurnOf(p))
        {
            return false;
        }
        for (int i = 0; i < players.size(); i++)
        {
            if (p == players.get(i))
            {
                System.out.println("\n" + p.getName() + " played " + c);
                cardsOnTable[i].addTopCard(c);
                turnOfPlayer++;

                if (turnOfPlayer % 4 != 0)
                {
                    return true;
                }

                turnOfPlayer %= 4;
                roundNo++;


                int highest = 0;
                ArrayList<Integer> winners = new ArrayList<>();
                for (int j = 0; j < cardsOnTable.length; j++)
                {
                    Card card = cardsOnTable[j].getTopCard();
                    if (card.getFaceValue() > highest)
                    {
                        highest = card.getFaceValue();
                        winners.clear();
                        winners.add(j);
                    } 
                    else if (card.getFaceValue() == highest)
                    {
                        winners.add(j);
                    }
                    cardsOnTable[j].addTopCard(card);
                }
                
                System.out.print("Winners : ");
                
                for (Integer w : winners)
                {
                    System.out.print(players.get(w).getName() + " ");
                    scoreCard.update(w, 1);
                }

                System.out.println("");

                return true;
            }
        }
        return false;
    }
    
    public boolean isTurnOf( Player p)
    {
        for (int i = 0; i < players.size(); i++)
        {
            if (p == players.get(i))
            {
                return turnOfPlayer == i;
            }
        }
        return false;
    }
    
    public boolean isGameOver()
    {
        return roundNo > 13;
    }
    
    public int getScore( int playerNumber)
    {
        return scoreCard.getScore(playerNumber);
    }
    
    public String getName( int playerNumber)
    {
        return players.get(playerNumber).getName();
    }
    
    public int getRoundNo()
    {
        return roundNo;
    }
    
    public int getTurnOfPlayerNo()
    {
        return turnOfPlayer+1;
    }
    
    public Player[] getWinners()
    {
        int[] winnersID = scoreCard.getWinners();
        Player[] winners = new Player[winnersID.length];
        for (int i = 0; i < winnersID.length; i++)
        {
            winners[i] = players.get(winnersID[i]);
        }
        return winners;
    }
    
    public String showScoreCard()
    {
        return scoreCard.toString();
    }
    
}