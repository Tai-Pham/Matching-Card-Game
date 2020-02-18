import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameBoard extends JPanel{

	String[] name = {"black2.jpg","black3.jpg","black4.jpg","black5.jpg","black6.jpg","black7.jpg","black8.jpg","black9.jpg","black10.jpg",
			"blackAce.jpg","blackJack.jpg","blackQueen.jpg","blackKing.jpg","red2.jpg","red3.jpg","red4.jpg","red5.jpg","red6.jpg","red7.jpg",
			"red8.jpg","red9.jpg","red10.jpg","redAce.jpg","redJack.jpg","redQueen.jpg","redKing.jpg"};
	
	private ArrayList<GameCard> cardList;
	private ArrayList<GameCard> removedList;
	private GameCard firstSelected;
	private GameCard secondSelected;
	private int numOfCardSelected;
	
	private int cardsPerGame;
	private int maxIdNumber;
	
	private boolean matched;
	private boolean checked;
	private Timer time;
	
	
	
	public GameBoard()
	{
		cardList = new ArrayList<>();
		removedList = new ArrayList<>();
		setLayout(new GridLayout(4,13));		
		cardsPerGame = 4;

		maxIdNumber = 1;
		
		for(int j = 0; j < cardsPerGame; j++)
		{
			GameCard card1 = new GameCard(new ImageIcon("back.jpg"), new ImageIcon(name[j]), maxIdNumber);
			card1.setBoard(this);
			cardList.add(card1);
			
			GameCard card2 = new GameCard(new ImageIcon("back.jpg"), new ImageIcon(name[j]), maxIdNumber);
			card2.setBoard(this);
			cardList.add(card2);		
			maxIdNumber++;
		}
		
		Collections.shuffle(cardList);
		
		for(GameCard c : cardList)
		{
			this.add(c);
		}
		
		time = new Timer(1250, new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                checkAll();
            }
        });
        time.setRepeats(false);
        
        numOfCardSelected = 0;
        this.setBackground(Color.WHITE);
	}
	
	public int getNumOfSelectedCard()
	{
		return numOfCardSelected;
	}
	
	public void increaseSelected()
	{
		numOfCardSelected++;
	}
	
	// checks the current states of the board in order to decide
	// what to do with the cards.
	private void logicalCheck()
	{		
		if(firstSelected.getId() == secondSelected.getId())
		{
			matched = true;
			checked = true;
		}
		else
		{
			//JOptionPane.showMessageDialog(null, "Congratulations you have a decent memory");
			matched = false;
			checked = true;
		}	
	}
	
	// once logicalCheck gives labels to the board, depending on labels,
	// take the cards out of the arrayList or reset the cards to the back face.
	private void booleanCheck()
	{
		if(checked && matched)
		{
			removedList.add(firstSelected);
			cardList.remove(firstSelected);
			
			removedList.add(secondSelected);
			cardList.remove(secondSelected);
		}
		
		if(checked && !matched)
		{
			firstSelected.reset();
			secondSelected.reset();
		}
	}
	
	// once both logicalCheck and booleanCheck have run
	// reset variables for another pass if needed.
	// the win condition is also checked here.
	private void boardReset()
	{
		
		if(numOfCardSelected >= 26)
		{
			JOptionPane.showMessageDialog(null, "Congratulations you have a decent memory");
			endGame();
		}
		
		if(cardList.size() == 0)
		{
			JOptionPane.showMessageDialog(null, "Next Level");
			for(GameCard c : removedList)
			{
				c.reset();
			}
			
			cardList.addAll(removedList);
			removedList.clear();
			nextLevel();
		}
		
		firstSelected = null;
		secondSelected = null;
		matched = false;
		checked = false;
		numOfCardSelected = 0;
	}
	
	public void hold(GameCard card)
	{
		if (firstSelected == null)
		{
			firstSelected = card;
			card.flip();
		}
		else
		{
			secondSelected = card;
			card.flip();			
		}
		
		time.start();
	}
	
	public void checkAll()
	{
		if (firstSelected != null && secondSelected != null)
		{
			logicalCheck();
			booleanCheck();
			boardReset();
		}
	}
	
	public void endGame()
	{
		System.exit(0);
	}
	
	public void nextLevel()
	{
		this.removeAll();
		this.revalidate();
		
		int newSize = 0;
		if(cardsPerGame + 2 > 26 )
		{
			//One off for the loop
			newSize = 27;
		}
		else
		{
			newSize = cardsPerGame + 2;
		}
		
		for(int i = cardsPerGame; i < newSize; i++)
		{
			GameCard card1 = new GameCard(new ImageIcon("back.jpg"), new ImageIcon(name[i]), maxIdNumber);
			card1.setBoard(this);
			cardList.add(card1);
			
			GameCard card2 = new GameCard(new ImageIcon("back.jpg"), new ImageIcon(name[i]), maxIdNumber);
			card2.setBoard(this);
			cardList.add(card2);		
			maxIdNumber++;
		}
		
		Collections.shuffle(cardList);		
		for(GameCard c : cardList)
		{
			this.add(c);
		}
		
		cardsPerGame = newSize;		
		
		this.revalidate();
		this.repaint();
	}
	
	public int getDeck() {
		return cardList.size();
	}
}
