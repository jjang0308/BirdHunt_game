import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartPage extends JFrame{
	
	private Image screenImage;
	private Graphics screenGraphic;
	
	private ImageIcon startButtonImage = new ImageIcon("src\\startbuttonton.jpg");
//	private ImageIcon quitButtonImage = new ImageIcon("src\\quitB.png");
	private Image introBackground = new ImageIcon("src\\SecondPage.jpg").getImage();
																																																																																																																																																																																																																																																																																																																				
	private JButton startButton = new JButton(startButtonImage);
//	private JButton quitButton = new JButton(quitButtonImage);
	private StartPagePanel centerPanel;
	
	public StartPage() { 
		
		centerPanel = new StartPagePanel();
		
		this.add(centerPanel);
		
		this.setTitle("무제");
		this.setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(null);
		this.setResizable(false);
		this.startButton.setBounds(55,470,350,100);
//		this.playAudio("src\\BackGround2.wav");
//      	this.quitButton.setBounds(400, 490, 80, 80);
		
		this.startButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					dispose();
				getContentPane().add(new SecondPage());
				
				} catch (Exception e2) {
					
				}
				
		}
			@Override
			public void mouseExited(MouseEvent e) {
				
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
//		this.quitButton.addMouseListener(new MouseListener() {
//			
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				}
//			
//			@Override
//			public void mousePressed(MouseEvent e) {
//				System.exit(0);
//			}
//			@Override
//			public void mouseExited(MouseEvent e) {}
//			@Override
//			public void mouseEntered(MouseEvent e) {}
//			@Override
//			public void mouseClicked(MouseEvent e) {}
//		});
		centerPanel.add(startButton);
//		centerPanel.add(quitButton);
	}	
	
//	private void playAudio(String audioFilePath) {
//	    try {
//	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(audioFilePath).getAbsoluteFile());
//	        Clip clip = AudioSystem.getClip();
//	        clip.open(audioInputStream);
//	        clip.start();
//	    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
//	        e.printStackTrace();
//	    }
//	}
	
	
	
	
	public class StartPagePanel extends JPanel{
		@Override
		protected void paintComponent(Graphics g) {
			screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
		repaint();
			
		}
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, -30, 0, null);
		
		g.setColor(Color.black);
	    g.setFont(new Font("TimesRoman", Font.BOLD, 20));
	    g.drawString("!새잡기 게임!:", 30, 200);

	    // 기본 게임 설명
	    g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
	    g.drawString("1. 화살을 쏴서 새를 죽이는 게임입니다!", 30, 250);
	    g.drawString("2. 먼저 자신에게 맞는 난이도를 선택해주시면 됩니다! ", 30, 280);
	    g.drawString("- EASY와 HARD모드가 있습니다.", 30, 300);
	    g.drawString("3. 제한시간은 30초 입니다!", 30, 330);

	    // 추가적인 설명
	    g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
	    g.drawString("조작 방법:", 30, 360);
	    g.drawString("화살표키: 위, 아래, 오른쪽, 왼쪽", 30, 430);
	    g.drawString("스페이스바: 화살 발사", 30, 390);

	    g.drawString("30초안에 최대한 많은 점수를 획득하십쇼~", 30, 500);
		
		
	}
	
}
