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

    public void dealBoth() {
        player1.add(deck.deal());
        player2.add(deck.deal());
    }

    public void dealP1() {
        player1.add(deck.deal());
    }

    public void dealP2() {
        player2.add(deck.deal());
    }

    public List<Card> getP1() {
        return player1;
    }

    public List<Card> getP2() {
        return player2;
    }

    public boolean check21(List<Card> hand) {
        int sum = 0;
        for (Card card : hand) {
            sum += card.pointValue();
        }
        if (sum > 21) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkGameStatus() {

        return true;
    }
}
