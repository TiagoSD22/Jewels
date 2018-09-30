package Jewels;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Graphics;

public class Level3{

	private JFrame frmJogo;
			JFrame fim;
			JLabel info;
			JButton novo,sair;
			JLabel backboard,recomecar;
			boolean tema = true;
			JLabel TemaAudio;
			JButton voltarmenu;
			JFrame inicio;
			JLabel menu;
	
	public JLabel background;
		   JLabel Recomecar;
		   JLabel blocos[][];
		   Gemas jogo = new Gemas(7); 
		   boolean b1 = false,b2 = false;
		   int ib1,jb1,ib2,jb2;
		   int pontuacao;
		   int combo;
		   JLabel foco;
		   JLabel board;
		   JLabel tabelaPontos;
		   JLabel pontos;
		   JLabel labelpontos;
		   Sons som;
	
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
	
	public void NovoJogo(){
		b1 = false;
		b2 = false;
		pontuacao = 0;
		pontos.setText("0");
		jogo = new Gemas(2);
		PintarPlanoDeFundo();
		DesenharTabuleiro();
	}
		   
	public void DesenharBloco(int i, int j, JLabel bloco){
		bloco.setIcon(jogo.mapa.get(jogo.tabuleiro[i][j]));
	}
	
    public void DesenharTabuleiro(){
    	for(int i = 0; i < 8; i++){
    		for(int j = 0; j < 8; j++){
    			DesenharBloco(i,j,blocos[i][j]);
    		}
    	}
    }
    
    public void GameOver(){
    	if(jogo.GameOver() || pontuacao >= 2000){
    		som.Parar("lvl1");
    		fim.setVisible(true);
    		if(jogo.GameOver()){
    			novo.setVisible(true);
    			voltarmenu.setVisible(true);
    			info.setText("NÃO HÁ MOVIMENTOS POSSÍVEIS!");
    		}
    		else{
    			voltarmenu.setVisible(true);
    			info.setText("VOCÊ CONCLUIU O LEVEL!");
    		}
    	}
    	
    }
	/**
	 * Create the application.
	 */
	public Level3() {
		initialize();
		frmJogo.setTitle("Level 2\n");
		frmJogo.getContentPane().setLayout(null);
		NovoJogo();
		Recomecar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NovoJogo();
			}
		});
		
		for(int i = 0; i < blocos.length; i++){
			for(int j = 0; j < blocos[i].length; j++){
				blocos[i][j].addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e){
						int x = 0, y = 0;
						while(e.getSource() != blocos[x][y]){
							y++;
							if(y == 8){
								y = 0;
								x++;
							}
						}
						if(!b1){
							b1 = true;
							som.Tocar("foco");
							foco.setBounds(blocos[x][y].getX(),blocos[x][y].getY(), 50, 50);
							foco.setVisible(true);
							foco.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fonte/Imagens/Icones/foco.png")));
							combo = 1;
							ib1 = x;
							jb1 = y;
						}
						else{
							foco.setIcon(null);
							if(!b2){
								ib2 = x;
								jb2 = y;
								if(jogo.PodeTrocarPecas(ib1,jb1,ib2,jb2)){
									som.Tocar("whoosh");
									Swap(blocos[ib1][jb1],blocos[ib2][jb2]);
									jogo.TrocarPecas(ib1, jb1, ib2, jb2);
									pontuacao = pontuacao + jogo.ProcessarCombinacoes();
									som.Tocar("ponto");
									pontos.setText(String.valueOf(pontuacao));
									DesenharTabuleiro();
									jogo.Mover_para_Baixo(blocos,background);
									jogo.AcrescentarNovosBlocos(background,2);
									som.Tocar("queda");
									DesenharTabuleiro();
									while(jogo.ExistemPeloMenosTresAlinhadasHorizontalmente(jogo.tabuleiro)||
									jogo.ExistemPeloMenosTresAlinhadasVerticalmente(jogo.tabuleiro)){
										combo++;
										pontuacao = pontuacao + combo*(jogo.ProcessarCombinacoes());
										som.Tocar("ponto");
										jogo.Mover_para_Baixo(blocos,background);
										jogo.AcrescentarNovosBlocos(background,2);
										som.Tocar("queda");
										pontos.setText(String.valueOf(pontuacao));
										DesenharTabuleiro();
									}
									GameOver();
								}
								else{
									//som.Tocar("npode");
									if(ib1 != ib2 || jb1 != jb2){
										if(Math.abs((8*ib1+jb1) - (8*ib2+jb2)) == 1 || Math.abs((8*ib1+jb1) - (8*ib2+jb2)) == 8){
											som.Tocar("whoosh");
											DesfazerSwap(blocos[ib1][jb1],blocos[ib2][jb2]);
											som.Tocar("no");
											
										}
										frmJogo.repaint();
									}
								}
								b1 = false;
								b2 = false;
							}
						}
					}
				});
			}
		}
	}
	
	public void Swap(JLabel bloco1 , JLabel bloco2){
		Graphics g = background.getGraphics();
		ImageIcon icon1 = (ImageIcon)bloco1.getIcon();
		ImageIcon icon2 = (ImageIcon)bloco2.getIcon();
		int x1 = bloco1.getX();
		int y1 = bloco1.getY();
		int x2 = bloco2.getX();
		int y2 = bloco2.getY();
		int nd = 0;
		long expectedtime = System.currentTimeMillis();
		while (nd <= 51) {//Or any Loops
		   while(System.currentTimeMillis() < expectedtime){
		     //Empty Loop   
		   }
		   expectedtime +=5;
		   if(y1 == y2){
			   if(x1 < x2){
				   	icon1.paintIcon(null,g,x1+nd,y1);
					icon2.paintIcon(null, g, x2-nd, y2);
					nd++;
			   }
			   else{
				   icon1.paintIcon(null,g,x1-nd,y1);
				   icon2.paintIcon(null, g, x2+nd, y2);
				   nd++;
			   }
		   }
		   else{
			   if(y1 < y2){
				   	icon1.paintIcon(null,g,x1,y1+nd);
					icon2.paintIcon(null, g, x2, y2-nd);
					nd++;
			   }
			   else{
				   icon1.paintIcon(null,g,x1,y1-nd);
				   icon2.paintIcon(null, g, x2, y2+nd);
				   nd++;
			   }
		   }
		}
	}

	public void DesfazerSwap(JLabel bloco1, JLabel bloco2){
		Graphics g = background.getGraphics();
		ImageIcon icon1 = (ImageIcon)bloco1.getIcon();
		ImageIcon icon2 = (ImageIcon)bloco2.getIcon();
		int x1 = bloco1.getX();
		int y1 = bloco1.getY();
		int x2 = bloco2.getX();
		int y2 = bloco2.getY();
		int nd = 0;
		long expectedtime = System.currentTimeMillis();
		while (nd <= 52) {//Or any Loops
			while(System.currentTimeMillis() < expectedtime){
		     //Empty Loop   
		   }
		   expectedtime +=5;
		   if(y1 == y2){
			   if(x1 < x2){
			   		icon1.paintIcon(null,g,x1+nd,y1);
			   		icon2.paintIcon(null, g, x2-nd, y2);
			   		nd++;
			   }
			   else{
				   	icon1.paintIcon(null,g,x1-nd,y1);
			   		icon2.paintIcon(null, g, x2+nd, y2);
			   		nd++;
			   }
		   }
		   else{
			   if(y1 < y2){
			   		icon1.paintIcon(null,g,x1,y1+nd);
			   		icon2.paintIcon(null, g, x2, y2-nd);
			   		nd++;
			   }
			   else{
				   	icon1.paintIcon(null,g,x1,y1-nd);
			   		icon2.paintIcon(null, g, x2, y2+nd);
			   		nd++;
			   }
		   }
		}
		nd = 0;
		while (nd <= 52) {//Or any Loops
			   while(System.currentTimeMillis() < expectedtime){
			     //Empty Loop   
			   }
			   expectedtime +=5;
			   if(y1 == y2){
				   if(x1 < x2){
					   	icon1.paintIcon(null,g,x2-nd,y1);
						icon2.paintIcon(null, g, x1+nd, y2);
						nd++;
				   }
				   else{
					   	icon1.paintIcon(null,g,x2+nd,y1);
						icon2.paintIcon(null, g, x1-nd, y2);
						nd++;
				   }
			   }
			   else{
				   if(y1 < y2){
					   	icon1.paintIcon(null,g,x1,y2-nd);
						icon2.paintIcon(null, g, x2, y1+nd);
						nd++;
				   }
				   else{
					   	icon1.paintIcon(null,g,x1,y2+nd);
						icon2.paintIcon(null, g, x2, y1-nd);
						nd++;
				   }
			   }
			}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJogo = new JFrame();
		frmJogo.setVisible(true);
		frmJogo.setIconImage(Toolkit.getDefaultToolkit().getImage(Level1.class.getResource("/Fonte/Imagens/Icones/icone.png")));
		frmJogo.setResizable(true);
		frmJogo.setBackground(Color.LIGHT_GRAY);
		//frmJogo.setPreferredSize(new Dimension(900,600));
		//frmJogo.pack();
		frmJogo.setBounds(10, 10, 900, 563);
		frmJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		fim = new JFrame();
		fim.setBounds(frmJogo.getWidth()/2,frmJogo.getHeight()/2,400,200);
		fim.getContentPane().setLayout(null);
		fim.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fim.setVisible(false);
		info = new JLabel();
		info.setBounds(((fim.getWidth() - 1)/2) - 165, 10, 330, 20);
		info.setHorizontalAlignment(SwingConstants.CENTER);
		fim.getContentPane().add(info);
		novo = new JButton("TENTAR NOVAMENTE");
		novo.setVisible(false);
		novo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		novo.setBounds(((fim.getWidth() - 1)/2) - 90, 90, 180, 20);
		novo.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				NovoJogo();
				fim.dispose();
			}
		});
		fim.getContentPane().add(novo);
		sair = new JButton("SAIR");
		sair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sair.setBounds(((fim.getWidth() - 1)/2) - 35, 130, 70, 20);
		sair.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				frmJogo.dispose();
				fim.dispose();
				System.exit(0);
			}
		});
		fim.getContentPane().add(sair);
		voltarmenu = new JButton("VOLTAR PARA O MENU");
		voltarmenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		voltarmenu.setBounds(((fim.getWidth() - 1)/2) - 95, 50, 190,20);
		voltarmenu.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				frmJogo.dispose();
				fim.dispose();
				new Menu();
			}
		});
		fim.getContentPane().add(voltarmenu);
		
		recomecar = new JLabel("NOVO JOGO");
		recomecar.setFont(new Font("Purisa",Font.BOLD,12));
		recomecar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		recomecar.setBounds(50,245,90,20);
		frmJogo.getContentPane().add(recomecar);
		
		Recomecar = new JLabel("");
		Recomecar.setOpaque(false);
		Recomecar.setBackground(Color.LIGHT_GRAY);
		Recomecar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Recomecar.setBounds(20,230, 140, 60);
		Recomecar.setToolTipText("COMEÇAR NOVO JOGO");
		Recomecar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fonte/Imagens/Icones/barra.png")));
		frmJogo.getContentPane().add(Recomecar);
		
		blocos = new JLabel[8][8];
		frmJogo.getContentPane().setLayout(new GridLayout(8,8));
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				blocos[i][j] = new JLabel("");
				blocos[i][j].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				blocos[i][j].setBackground(Color.LIGHT_GRAY);
				blocos[i][j].setOpaque(false);
				blocos[i][j].setBounds(j + (245 + 50*j), i + (130 + 50*i), 50, 50);
				frmJogo.getContentPane().add(blocos[i][j]);
			}
		}
		
		foco = new JLabel();
		foco.setBounds(0, 0, 0, 0);
		foco.setVisible(false);
		frmJogo.getContentPane().add(foco);
		
		board = new JLabel();
		board.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fonte/Imagens/Icones/board.jpg")));
		board.setBounds(blocos[0][0].getX() - 1,blocos[0][0].getY() - 1, 409,409);
		frmJogo.getContentPane().add(board);
		
		labelpontos = new JLabel("PONTOS:");
		labelpontos.setFont(new Font("Purisa",Font.BOLD,12));
		labelpontos.setHorizontalAlignment(SwingConstants.CENTER);
		labelpontos.setBounds(55,160,70,20);
		frmJogo.getContentPane().add(labelpontos);
		
		pontos = new JLabel("0");
		pontos.setFont(new Font("Purisa", Font.BOLD, 12));
		pontos.setHorizontalAlignment(SwingConstants.CENTER);
		pontos.setBounds(45,175, 90,20);
		frmJogo.getContentPane().add(pontos);
		
		tabelaPontos = new JLabel("");
		tabelaPontos.setBounds(20, 150, 140,60);
		tabelaPontos.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fonte/Imagens/Icones/score.png")));
		frmJogo.getContentPane().add(tabelaPontos);
		
		som = new Sons();
		
		//som.Tocar("lvl1");
		TemaAudio = new JLabel();
		TemaAudio.setBounds(55,300,70,70);
		TemaAudio.setOpaque(false);
		TemaAudio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		TemaAudio.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fonte/Imagens/Icones/comsom.png")));
		TemaAudio.setToolTipText("Parar/Tocar a música");
		TemaAudio.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				tema = !tema;
				if(!tema){
					som.Parar("lvl1");
					TemaAudio.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fonte/Imagens/Icones/semsom.png")));
				}
				else{
					som.Tocar("lvl1");
					TemaAudio.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fonte/Imagens/Icones/comsom.png")));
				}
			}
		});
		frmJogo.getContentPane().add(TemaAudio);
		
		menu = new JLabel();
		menu.setBounds(55,400,70,70);
		menu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fonte/Imagens/Icones/menu.gif")));
		menu.setToolTipText("Voltar para o menu");
		menu.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				frmJogo.dispose();
				if(inicio.isVisible()){
					inicio.dispose();
				}
				som.Parar("lvl1");
				new Menu();
			}
		});
		frmJogo.getContentPane().add(menu);
		
		background = new JLabel("");
		background.setBounds(0,0, frmJogo.getWidth(),frmJogo.getHeight());
		frmJogo.getContentPane().add(background);
		
		//tela de inicio
		
		inicio = new JFrame("Level 2");
		inicio.setBounds(450, 300, 400, 200);
		inicio.setLayout(null);
		inicio.setVisible(true);
		
		JLabel infolvl = new JLabel("Faça 2000 pontos para ganhar!");
		infolvl.setHorizontalAlignment(SwingConstants.CENTER);
		infolvl.setBounds((inicio.getWidth() - 1)/2 - 125,10, 250, 20);
		inicio.getContentPane().add(infolvl);
		
		JButton continuar = new JButton("CONTINUAR");
		continuar.setBounds((inicio.getWidth() - 1)/2 - 75, (inicio.getHeight() - 1)/2 - 10, 150, 20);
		continuar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		continuar.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				inicio.dispose();
				som.Tocar("lvl1");
			}
		});
		inicio.getContentPane().add(continuar);
	}
}
