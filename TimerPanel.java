import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Took inspiration from Screen.java from https://www.youtube.com/watch?v=iH_uoe4vvso
public class TimerPanel extends JPanel implements ActionListener{
	JLabel timeLabel = new JLabel();
	int time = 61;
	Timer cycleTime = new Timer(1000,this);
	GameBoard game;
	
	public TimerPanel(GameBoard gb)
	{
		cycleTime.start();
		add(timeLabel);
		game = gb;
	}
	
	public void cycle()
	{
		time--;
		timeLabel.setText(""+time);
		timeLabel.setFont(timeLabel.getFont().deriveFont(Font.BOLD, 50));
		if (time == 0)
		{
			cycleTime.stop();
			JOptionPane.showMessageDialog(null, "Sorry, you lose!");
			game.endGame();
		}
		if(game.getDeck() == 0) {
			time = 61;
			cycleTime.restart();
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		cycle();
		repaint();
	}
	
	public int getTime()
	{
		return time;
	}
}
