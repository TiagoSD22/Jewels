package Jewels;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Menu {

	private JFrame frame;
			JLabel background;
			JButton lvl1,lvl2;
			int pg = 1;
			JLabel over;
			
	public void PintarPlanoDeFundo(){
		Random rand = new Random();
		int plano = rand.nextInt(7);
		if(plano == 0){
			background.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fonte/Imagens/Backgrounds/plano1.jpg")));
		}
		if(plano == 1){
			background.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fonte/Imagens/Backgrounds/plano2.jpg")));
		}
		if(plano == 2){
			background.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fonte/Imagens/Backgrounds/plano3.jpg")));
		}
		if(plano == 3){
			background.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fonte/Imagens/Backgrounds/plano4.jpg")));
		}
		if(plano == 4){
			background.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fonte/Imagens/Backgrounds/plano5.jpg")));
		}
		if(plano == 5){
			background.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fonte/Imagens/Backgrounds/plano6.jpg")));
		}
		if(plano == 6){
			background.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fonte/Imagens/Backgrounds/plano7.jpg")));
		}
		ImageIcon img2 =(ImageIcon) background.getIcon();
		background.setIcon(new ImageIcon(img2.getImage().getScaledInstance(background.getWidth(),background.getHeight(),Image.SCALE_DEFAULT)));
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Gemas");
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/Fonte/Imagens/Icones/icone.png")));
		frame.setBounds(100, 100, 900, 563);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		over = new JLabel();
		over.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fonte/Imagens/Icones/safira.gif")));
		over.setVisible(false);
		frame.getContentPane().add(over);
		
		lvl1 = new JButton();
		lvl1.setBounds(135,115,103,103);
		lvl1.setOpaque(false);
		lvl1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fonte/Imagens/Fases/Level1/level1.png")));
		lvl1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lvl1.setVisible(true);
		lvl1.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				over.setVisible(true);
				over.setBounds((lvl1.getX() + ((lvl1.getWidth() - 1)/2) - 25),lvl1.getY() + lvl1.getHeight() + 10,50,50);
			}
			public void mouseExited(MouseEvent e){
				over.setVisible(false);
			}
			public void mouseClicked(MouseEvent e){
				new Level1();
				frame.dispose();
			}
		});
		frame.getContentPane().add(lvl1);
		
		JLabel lvl1label = new JLabel();
		lvl1label.setBounds(lvl1.getX() + (lvl1.getWidth() - 1)/2 - 70,lvl1.getY() - 75,140,60);
		lvl1label.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fonte/Imagens/Fases/Level1/label.gif")));
		frame.getContentPane().add(lvl1label);
		
		lvl2 = new JButton();
		lvl2.setBounds(341,115,103,103);
		lvl2.setOpaque(false);
		lvl2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fonte/Imagens/Fases/Level2/level2.png")));
		lvl2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lvl2.setVisible(true);
		lvl2.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				over.setVisible(true);
				over.setBounds((lvl2.getX() + ((lvl2.getWidth() - 1)/2) - 25),lvl2.getY() + lvl2.getHeight() + 10,50,50);
			}
			public void mouseExited(MouseEvent e){
				over.setVisible(false);
			}
			public void mouseClicked(MouseEvent e){
				new Level2();
				frame.dispose();
			}
		});
		frame.getContentPane().add(lvl2);
		
		JLabel lvl2label = new JLabel();
		lvl2label.setBounds(lvl2.getX() + (lvl2.getWidth() - 1)/2 - 70,lvl2.getY() - 75,140,60);
		lvl2label.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fonte/Imagens/Fases/Level2/label.gif")));
		frame.getContentPane().add(lvl2label);
		
		background = new JLabel();
		background.setBounds(0,0,900,563);
		PintarPlanoDeFundo();
		frame.getContentPane().add(background);

	}

}
