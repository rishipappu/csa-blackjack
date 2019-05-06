import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.util.List;
import java.awt.EventQueue;
import java.util.ArrayList;

/**
 * Write a description of class BlackJackGUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BlackJackGUI {
    /** Height of the game frame. */
    private static final int DEFAULT_HEIGHT = 302;
    /** Width of the game frame. */
    private static final int DEFAULT_WIDTH = 800;
    /** Width of a card. */
    private static final int CARD_WIDTH = 73;
    /** Height of a card. */
    private static final int CARD_HEIGHT = 97;
    /** Row (y coord) of the upper left corner of the first card. */
    private static final int LAYOUT_TOP = 30;
    /** Column (x coord) of the upper left corner of the first card. */
    private static final int LAYOUT_LEFT = 30;
    /**
     * Distance between the upper left x coords of two horizonally adjacent cards.
     */
    private static final int LAYOUT_WIDTH_INC = 100;
    /**
     * Distance between the upper left y coords of two vertically adjacent cards.
     */
    private static final int LAYOUT_HEIGHT_INC = 125;
    /** y coord of the "Replace" button. */
    private static final int BUTTON_TOP = 30;
    /** x coord of the "Replace" button. */
    private static final int BUTTON_LEFT = 570;
    /** Distance between the tops of the "Replace" and "Restart" buttons. */
    private static final int BUTTON_HEIGHT_INC = 50;
    /** y coord of the "n undealt cards remain" label. */
    private static final int LABEL_TOP = 160;
    /** x coord of the "n undealt cards remain" label. */
    private static final int LABEL_LEFT = 540;
    /**
     * Distance between the tops of the "n undealt cards" and the "You lose/win"
     * labels.
     */
    private static final int LABEL_HEIGHT_INC = 35;

    private Blackjack game = new Blackjack();
    JFrame f = new JFrame("Blackjack");
    JPanel p = new JPanel();
    JPanel c = new JPanel();
    JLabel sum = new JLabel("Sum: " + game.sumHand(game.getP2()));
    JLabel aceValue = new JLabel("Ace Value: " + game.getAceValue(game.getP2()));
    JLabel gameStatus = new JLabel(game.gameCheck());
    JButton redo = new JButton("Replay");

    public BlackJackGUI() {
        f.setSize(DEFAULT_HEIGHT, DEFAULT_WIDTH);
        f.setVisible(true);
        JButton deal = new JButton("Hit");
        JButton ace = new JButton("Change Value of Ace");
        JButton stand = new JButton("Stand");
        game.dealHand(game.getP1());
        game.dealHand(game.getP2());
        deal.setBounds(50, 50, 300, 500);
        p.setBounds(LAYOUT_LEFT, LAYOUT_TOP, 2 * LAYOUT_WIDTH_INC, 2 * LAYOUT_HEIGHT_INC);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(p);
        f.add(c);
        p.add(deal);
        p.add(ace);
        p.add(stand);
        if (game.gameCheck().equals("You Lose!") || game.gameCheck().equals("You Win!")) {
            redo.setVisible(true);
        } else {
            redo.setVisible(false);
        }
        p.add(redo);
        p.add(sum);
        p.add(aceValue);
        p.add(gameStatus);
        initialRender(game.getP2());
        status();
        while (game.check21(game.getP2())) {
            deal.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    status();
                }
            });
        }

        redo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                JFrame f = new JFrame("Blackjack");
                JPanel p = new JPanel();
                JPanel c = new JPanel();
                JLabel sum = new JLabel("Sum: " + game.sumHand(game.getP2()));
                JLabel aceValue = new JLabel("Ace Value: " + game.getAceValue(game.getP2()));
                JLabel gameStatus = new JLabel(game.gameCheck());
                JButton redo = new JButton("Replay");
                BlackJackGUI newgame = new BlackJackGUI();
            }
        });

        stand.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.dealP1();
                f.repaint();
            }
        });

        ace.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.changeAce(game.getP2());
                updateSum(game.getP2());
            }
        });
    }

    public void initialRender(List<Card> hand) {
        for (Card card : hand) {
            ImageIcon i = new ImageIcon("./cards/" + card.rank() + card.suit() + ".GIF");
            JLabel scard = new JLabel(i);
            scard.setSize(CARD_WIDTH, CARD_HEIGHT);
            c.add(scard);
            scard.setVisible(true);
            updateSum(game.getP2());
        }
    }

    public void render(List<Card> hand) {
        Card cardToRender = hand.get(hand.size() - 1);
        ImageIcon j = new ImageIcon("./cards/" + cardToRender.rank() + cardToRender.suit() + ".GIF");
        JLabel cardib = new JLabel(j);
        cardib.setSize(CARD_WIDTH, CARD_HEIGHT);
        c.add(cardib);
        cardib.setVisible(true);
        updateSum(game.getP2());
    }

    public void updateSum(List<Card> hand) {
        sum.setText("Sum: " + game.sumHand(hand));
        f.repaint();
    }

    public void status() {
        if (game.check21(game.getP2())) {
            game.dealHand(game.getP2());
            render(game.getP2());
            gameStatus.setText(game.gameCheck());
            f.repaint();
        } else {
            redo.setVisible(true);
        }
    }
}
