import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class SecondPage extends JFrame {
	private Image screenImage2;
	private Graphics screenGraphic2;
	private Image Background_Main = new ImageIcon("src\\StarPageBackG.jpg").getImage();
	private ImageIcon easyB = new ImageIcon("src\\easyBB.png");
	private ImageIcon hardB = new ImageIcon("src\\hardBB.png");
	int num;
	private SecondPagePanel centerPanel2;
	private JButton easy = new JButton(easyB);
	private JButton hard = new JButton(hardB);
	

	public SecondPage() {
		centerPanel2 = new SecondPagePanel();
		
		this.add(centerPanel2);
		this.setTitle("무제2");
		this.setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(null);
		this.setResizable(false);
		
		
		
		this.easy.setBounds(10, 300, 200, 100);
		
		
		this.easy.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					dispose();
					getContentPane().add(new BirdHunt());

				} catch (Exception e2) {
					// TODO: handle exception
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				easy.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				easy.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		this.hard.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					dispose();
					getContentPane().add(new BirdHuntHard());

				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				hard.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				hard.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		centerPanel2.add(easy);
		centerPanel2.add(hard);
		
		

		
	}

	public class SecondPagePanel extends JPanel {

		protected void paintComponent(Graphics g) {
			screenImage2 = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
			screenGraphic2 = screenImage2.getGraphics();
			screenDraw2(screenGraphic2);
			g.drawImage(screenImage2, 0, 0, null);
			repaint();
		}

		public void screenDraw2(Graphics g) {
			g.drawImage(Background_Main, -30, 0, null);
			this.repaint();
		}
	}
}
