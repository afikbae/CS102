package cardgame;

import java.util.ArrayList;

// ScoreCard - Maintains one integer score per player, for any number of players
//             Caution: invalid playernumbers result in run-time exception!
// author:
// date:
public class ScoreCard
{
    // properties
    int[] scores;
    
    // constructors
    public ScoreCard( int noOfPlayers)
    {
        scores = new int[noOfPlayers];
        
        // init all scores to zero
        for ( int i = 0; i < scores.length; i++)
            scores[i] = 0;
    }
    
    // methods
    public int getScore( int playerNo)
    {
        return scores[ playerNo];
    }
    
    public void update( int playerNo, int amount)
    {
        scores[playerNo] += amount;
    }
    
    public String toString()
    {
        String s;
        s = "\n"
            + "_____________\n"
            + "\nPlayer\tScore\n"
            + "_____________\n";
        
        for ( int playerNo = 0; playerNo < scores.length; playerNo++)
        {
            s = s + (playerNo + 1) + "\t" + scores[playerNo] + "\n";
        }
        s += "_____________";
        return s;
    }
    
    public int[] getWinners()
    {
        ArrayList<Integer> winners = new ArrayList<>();
        int highest = 0;
        for (int i = 0; i < scores.length; i++)
        {
            if (highest < scores[i])
            {
                highest = scores[i];
                winners.clear();
                winners.add(i);      
            }
            else if (highest == scores[i])
            {
                winners.add(i);
            }   
        }
        int[] result = new int[winners.size()];
        for (int i = 0; i < result.length; i++)
        {
            result[i] = winners.get(i);
        }
        return result;
    }
    
} // end class ScoreCard
