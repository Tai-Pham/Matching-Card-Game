import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;

public class tester {
	public static void main(String[] args)
	{
		JFrame mainFrame = new JFrame();
		mainFrame.setLayout(new BorderLayout());
		
		GameBoard board = new GameBoard();
		mainFrame.add(board,BorderLayout.CENTER);
		
		TimerPanel countdown = new TimerPanel(board);
		mainFrame.add(countdown,BorderLayout.NORTH);
		
		mainFrame.setSize(new Dimension(1000,1000));
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}
}
