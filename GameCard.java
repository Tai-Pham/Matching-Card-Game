import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class GameCard extends JButton implements ActionListener {

	private ImageIcon BackSide;
	private ImageIcon FrontSide;
	private int id;
	private boolean clicked;
	
	private GameBoard board;
	
	public GameCard(ImageIcon f, ImageIcon s, int id)
	{
		BackSide = f;
		FrontSide = s;
		this.id = id;
		
		clicked = false;		
		this.setIcon(BackSide);
		
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setFocusPainted(false); 
		this.setOpaque(false);
		addActionListener(this);
	}
	
	public ImageIcon getFirst()
	{
		return BackSide;
	}
	
	public ImageIcon getSecond()
	{
		return FrontSide;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void flip()
	{	
		this.setIcon(FrontSide);
		clicked = true;
	}
	
	public void reset()
	{
		this.setIcon(BackSide);
		clicked = false;
	}
	
	public Boolean clicked()
	{
		return clicked;
	}
	
	public void setBoard(GameBoard b)
	{
		board = b;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Check to make sure that you can't
		//select more than 2 cards
		if(board.getNumOfSelectedCard() == 2)
		{
			return;
		}
		
		if(!clicked)
		{
			board.increaseSelected();
			//clicked = true;
			//this.setIcon(second);
			board.hold((GameCard)e.getSource());
		}
		
	}

}
