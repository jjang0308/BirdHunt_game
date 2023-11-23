import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameDetail extends JFrame{
	
	
	private Graphics screenGraphic;
	
	public GameDetail() {
		this.setSize(new Dimension(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT));
		this.setTitle("게임 설명 페이지");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(null);
		this.setResizable(false);
		paint(screenGraphic);
		
		GamePanelD gamePanel = new GamePanelD();
        this.add(gamePanel);
		gamePanel.addKeyListener((KeyListener) gamePanel);
		
	}
	
	public void main(String[]args) {
		new GameDetail();
	}
	
	public class GamePanelD extends JPanel implements KeyListener {

	    public GamePanelD() {
	        
	        this.addKeyListener(this);
	        requestFocus();
	       this.setFocusable(true);
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	       
	        drawGameInstructionsScreen(g);
	        repaint();
	      
	    }

	    // Rest of the code, including drawGameInstructionsScreen method

	    @Override
	    public void keyTyped(KeyEvent e) {
	        // Implement keyTyped if necessary
	    }

	    @Override
	    public void keyPressed(KeyEvent e) {
	        int keycode = e.getKeyCode();
	        if (keycode == KeyEvent.VK_ENTER) {
	            try {
	                // dispose(); // You don't need to dispose the frame here
	                dispose(); // Remove all components from the panel
	                this.add(new SecondPage());
	                // Refresh the panel to show the new component
	            } catch (Exception e2) {
	                e2.printStackTrace(); // Handle exceptions properly in your actual code
	            }
	        }
	    }

	    @Override
	    public void keyReleased(KeyEvent e) {
	        // Implement keyReleased if necessary
	    }
	}

	
	
	
	
	private void drawGameInstructionsScreen(Graphics g) {
	    g.setColor(Color.black);
	    g.setFont(new Font("TimesRoman", Font.BOLD, 20));
	    g.drawString("게임 설명:", 30, 120);

	    // 기본 게임 설명
	    g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
	    g.drawString("1. 화살을 쏴서 새를 죽이는 게임입니다!", 30, 170);
	    g.drawString("2. 먼저 자신에게 맞는 난이도를 선택해주시면 됩니다! ", 30, 200);
	    g.drawString("- EASY와 HARD모드가 있습니다.", 30, 220);
	    g.drawString("3. 제한시간은 30초 입니다!", 30, 250);
	    g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	    g.drawString("EASY모드와 HARD모드는 화살의 속도와 새의 갯수가 차이 납니다.", 30, 300);

	    // 추가적인 설명
	    g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
	    g.drawString("조작 방법:", 30, 340);
	    g.drawString("화살표키: 위, 아래, 오른쪽, 왼쪽", 30, 370);
	    g.drawString("스페이스바: 화살 발사", 30, 430);

	    g.drawString("30초안에 최대한 많은 점수를 획득하십쇼~", 30, 500);

	}

}
