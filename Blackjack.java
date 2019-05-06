import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class Blackjack here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Blackjack {
    private static final String[] RANKS = { "ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen",
            "king" };

    /**
     * The suits of the cards for this game to be sent to the deck.
     */
    private static final String[] SUITS = { "spades", "hearts", "diamonds", "clubs" };

    /**
     * The values of the cards for this game to be sent to the deck.
     */
    private static final int[] POINT_VALUES = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };

    private Deck deck = new Deck(RANKS, SUITS, POINT_VALUES);
    private List<Card> player1 = new ArrayList<Card>();
    private List<Card> player2 = new ArrayList<Card>();

    public Blackjack() {
        player1.add(deck.deal());
        player2.add(deck.deal());
    }

    public void dealHand(List<Card> hand) {
        hand.add(deck.deal());
    }

    public List<Card> getP1() {
        return player1;
    }

    public List<Card> getP2() {
        return player2;
    }

    public int sumHand(List<Card> hand) {
        int sum = 0;
        for (int i = 0; i < hand.size(); i++) {
            sum += hand.get(i).pointValue();
        }
        return sum;
    }

    public int changeAce(List<Card> hand) {
        int aceValue = 1;
        for (int i = 0; i < hand.size() - 1; i++) {
            Card currentCard = hand.get(i);
            if (currentCard.rank().equals("ace") && currentCard.pointValue() == 10) {
                currentCard.setPointValue(1);
                aceValue = 1;
            } else if (currentCard.rank().equals("ace") && currentCard.pointValue() == 1) {
                currentCard.setPointValue(10);
                aceValue = 10;
            }
        }
        return aceValue;
    }

    public int getAceValue(List<Card> hand) {
        int aceValue = 1;
        for (int i = 0; i < hand.size() - 1; i++) {
            Card currentCard = hand.get(i);
            if (currentCard.rank().equals("ace") && currentCard.pointValue() == 10) {
                aceValue = 1;
            } else if (currentCard.rank().equals("ace") && currentCard.pointValue() == 1) {
                aceValue = 10;
            }
        }
        return aceValue;
    }

    public boolean check21(List<Card> hand) {
        if (sumHand(hand) < 21) {
            return true;
        } else if (sumHand(hand) > 21) {
            return false;
        } else {
            return true;
        }
    }

    public String gameCheck() {
        if (check21(player1) && check21(player2)) {
            return "You Win";
        } else if ((check21(player1) == false) && (check21(player2) == true)) {
            return "You Win";
        } else {
            return "You Lose!";
        }
    }

    public void dealP1() {
        for (int i = 0; i < (int) Math.random() * 5; i++) {
            player1.add(deck.deal());
        }
    }

}
