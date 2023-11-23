import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class BirdHunt extends JFrame {

	private ImageIcon birdImage = new ImageIcon("src\\bird.png");
	private ImageIcon arrowImage = new ImageIcon("src\\arrow.png");
	private ImageIcon playerImage = new ImageIcon("src\\player.png");
	private Image gameBackground = new ImageIcon("src\\SecondPage.jpg").getImage();
	private Graphics screenGraphic;
	private Image screenImage;
	private JPanel mainPanel = new JPanel();
	private JPanel timePanel = new JPanel();
	private int remainTime = 30;
	int catchBird=0;
	
	int birdWidth = 50;
	int birdHeight = 30;
	
	int panelWidth = 500;
	int panelHeight = 700;
	
	ArrayList<Bird> BirdList = new ArrayList<>();
	ArrayList<Arrow> ArrowList = new ArrayList<>();
	Player player = new Player("src\\player.png", panelWidth / 2 - 50, panelHeight - 150, 100, 100);
	
	JLabel timing = new JLabel("시간 : " + remainTime + "초");
	JLabel catchBirdnum = new JLabel("     잡은 새 : " + catchBird + "마리");
Timer timer;
Timer timer2;
	

	public BirdHunt() {

		for (int i = 0; i < 10; i++) {
			BirdList.add(new Bird("src\\bird.png", (int) (Math.random() * 300) + 100, (int) (Math.random() * 100) + 100,
					birdWidth, birdHeight));

		}
		
		setTitle("새잡기 게임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setSize(new Dimension(panelWidth, panelHeight));
		setLayout(new BorderLayout());

		GamePanel gamePanel = new GamePanel();
		gamePanel.add(timing);
		gamePanel.add(catchBirdnum);
		this.add(gamePanel);
		gamePanel.addKeyListener((KeyListener) gamePanel);

		 timer2 = new Timer(1000, new TimerListener2());
		 timer = new Timer(50, new TimerListener());
		timer.start();
		timer2.start();

		setVisible(true);
	}

	public class GamePanel extends JPanel implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
		}

		public void keyPressed(KeyEvent e) {
		    int keyCode = e.getKeyCode();
		    
		    int moveAmount = 15; // 이동 거리를 조절하세요. 숫자가 더 작을수록 더 부드러운 이동이 될 수 있습니다.

		    if (keyCode == KeyEvent.VK_RIGHT) {
		        player.playerX += moveAmount;
		    }
		    if (keyCode == KeyEvent.VK_LEFT) {
		        player.playerX -= moveAmount;
		    }
		    if (keyCode == KeyEvent.VK_UP) {
		        player.playerY -= moveAmount;
		    }
		    if (keyCode == KeyEvent.VK_DOWN) {
		        player.playerY += moveAmount;
		    }
		    if (keyCode == KeyEvent.VK_SPACE && ArrowList.size() < 5) {
		        ArrowList.add(new Arrow("src\\arrow.png", player.playerX + player.playerWidth / 2 - 15, player.playerY, 20, 50));
		    }
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void paintComponent(Graphics g) {

			screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
			screenGraphic = screenImage.getGraphics();
			screenDraw(screenGraphic);
			g.drawImage(screenImage, 0, 0, null);

			for (Bird bi : BirdList) {
				bi.BirdDraw(g);
			}

			player.PlayerDraw(g);

			for (Arrow ar : ArrowList) {
				ar.ArrowDraw(g);
			}
			requestFocus();
			setFocusable(true);
			repaint();
		}

		public void screenDraw(Graphics g) {
			g.drawImage(gameBackground, 0, 0, null);
			this.repaint();
		}

	}

	
	
	
	public void main(String[] args) {
		new BirdHunt();
	}

	private class TimerListener2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			remainTime -= 1;

			timing.setText("남은시간  : " + remainTime + "초");
			
			
			if (remainTime == 25 || remainTime == 20 || remainTime == 15 || remainTime== 10 || remainTime == 5) {
				for (int i = 0; i < 4; i++) {
					BirdList.add(new Bird("src\\bird.png", (int) (Math.random() * 500) + 100,
							(int) (Math.random() * 200) + 100, birdWidth, birdHeight));
				}
			}
			if(remainTime==0) {
				timer.stop();
				timer2.stop();
				JOptionPane.showMessageDialog(null,
		                  "Game Over\nYour 잡은 새 마릿수 : " + catchBird ,"GAME OVER",JOptionPane.INFORMATION_MESSAGE);
				
				try {
					dispose();
					getContentPane().add(new StartPage());
				} catch (Exception e2) {
					// TODO: handle exception
					 
				}
			}
		}
	}

	private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {  	
        	catchBirdnum.setText("     잡은 새 : " + catchBird + "마리");
        	for (Arrow ar : ArrowList) {
                ar.arrowY -= 20;
                
 
            }
        	
            ArrayList<Bird> birdsToRemove = new ArrayList<>();
            ArrayList<Arrow> arrowsToRemove = new ArrayList<>();

            for (Bird bird : BirdList) {
                bird.BirdMove(bird.moveX, bird.moveY);

                if (bird.birdX <= 0 || bird.birdX >= panelWidth - birdWidth)
                    bird.moveX = bird.moveX * -1;

                if (bird.birdY <= 0 || bird.birdY >= 400 - birdHeight)
                    bird.moveY = bird.moveY * -1;

                for (Arrow arrow : ArrowList) {
                	if (arrow.arrowY < 0) {
                        arrowsToRemove.add(arrow); 
                    }
                    if (arrow.arrowX + arrow.arrowWidth >= bird.birdX && arrow.arrowX <= bird.birdX + birdWidth
                            && arrow.arrowY-5 >= bird.birdY && arrow.arrowY <= bird.birdY + birdHeight) {
                         arrowsToRemove.add(arrow);  
                         birdsToRemove.add(bird);   
                         catchBird++;
                    }
                }
            }
            BirdList.removeAll(birdsToRemove);
            ArrowList.removeAll(arrowsToRemove);
            repaint();
        }
    }
	
	
	public class Arrow extends ImageIcon {
		int arrowX;
		int arrowY;
		int arrowHeight;
		int arrowWidth;

		public Arrow(String img, int arrowX, int arrowY, int arrowWidth, int arrowHeight) {
			super(img);
			this.arrowX = arrowX;
			this.arrowY = arrowY;
			this.arrowHeight = arrowHeight;
			this.arrowWidth = arrowWidth;
		}

		public int getArrowDX() {
			return this.arrowX;
		}

		public int getArrowDY() {
			return this.arrowY;
		}

		public void ArrowDraw(Graphics g) {
			
			g.drawImage(arrowImage.getImage(), arrowX, arrowY, arrowWidth, arrowHeight, null);
		}
	}

	public class Player extends ImageIcon {
		int playerX;
		int playerY;
		int playerWidth;
		int playerHeight;

		public Player(String img, int playerX, int playerY, int playerWidth, int playerHeight) {
			super(img);
			this.playerX = playerX;
			this.playerY = playerY;
			this.playerHeight = playerHeight;
			this.playerWidth = playerWidth;
		}

		public void PlayerDraw(Graphics g) {
			
			g.drawImage(playerImage.getImage(), playerX, playerY, playerWidth, playerHeight, null);
		}

		public void catchBird() {
			catchBird++;
		}
	}

	public class Bird extends ImageIcon {

		int birdX;
		int birdY;
		int birdWidth;
		int birdHeight;
		int moveX = 2;
		int moveY = -2;
		
		public Bird(String img, int birdX, int birdY, int birdWidth, int birdHeight) {
			super(img);
			this.birdX = birdX;
			this.birdY = birdY;
			this.birdHeight = birdHeight;
			this.birdWidth = birdWidth;
		}

		public void BirdMove(int x, int y) {
			birdX += x;
			birdY += y;
		}

		public void BirdDraw(Graphics g) {
		
			g.drawImage(birdImage.getImage(), birdX, birdY, null);
		}
	}

}
