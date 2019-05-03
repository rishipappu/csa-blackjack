import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;
/**
 * Write a description of class BlackJackGUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BlackJackGUI
{
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
	/** Distance between the upper left x coords of
	 *  two horizonally adjacent cards. */
	private static final int LAYOUT_WIDTH_INC = 100;
	/** Distance between the upper left y coords of
	 *  two vertically adjacent cards. */
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
	/** Distance between the tops of the "n undealt cards" and
	 *  the "You lose/win" labels. */
	private static final int LABEL_HEIGHT_INC = 35;

    
    boolean p1Status = true;
    boolean p2Status = true;
    private Blackjack game = new Blackjack();
    JFrame f=new JFrame("Blackjack"); 
    JPanel p=new JPanel(); 

    public BlackJackGUI() {
        render("2");
        render("1");
        JButton deal=new JButton("Deal Me");
        deal.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                dealP2();
            }
        });
        JButton pass=new JButton("Pass");
        pass.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                game.dealP1();
                dealP1();
            }
        });
        JLabel wlt = new JLabel("Keep Playing!");
        deal.setBounds(50,100,200,30);  
        pass.setBounds(50,100,200,30);  
        f.add(p); 
        p.add(deal);
        p.add(pass);
        p.add(wlt);
        p.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        f.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);  
        p.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);  
        f.setLayout(null);  
        f.setVisible(true);
    }
    public void render(String deck) {
        if (deck.equals("1")) {
            for(Card card : game.getP1()) {
                JLabel cardval = new JLabel(card.rank());
                p.add(cardval);
            }
        } else if (deck.equals("2")) {
            for(Card card : game.getP2()) {
                JLabel cardval = new JLabel(card.rank());
                p.add(cardval);
            }
        }
    }

    public void dealP1() {
        game.dealP1();
    }
    public void dealP2() {
        game.dealP2();
        render("2");
    }

}
