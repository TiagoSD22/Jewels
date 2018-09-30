package Jewels;

import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Gemas {
	public int tabuleiro[][];
	
	private Random rand = new Random();
	HashMap<Integer,ImageIcon> mapa;
	
	public Gemas( int n){
		String caminho = "/Fonte/Imagens/Icones/Gemas/Classicas/";
		tabuleiro = new int[8][8];
		IniciarTabuleiro(n);
		mapa = new HashMap<Integer,ImageIcon>(7);
		mapa.put(1,new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource(caminho + "safira.png")));
		mapa.put(2,new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource(caminho + "diamante.png")));
		mapa.put(3,new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource(caminho + "esmeralda.png")));
		mapa.put(4,new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource(caminho + "citrine.png")));
		mapa.put(5,new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource(caminho + "rubi.png")));
		mapa.put(6,new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource(caminho + "ametista.png")));
		mapa.put(7,new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource(caminho + "topazio.png")));
	}
	
	public int[] CopiarRemovendo(int origem[], int valor){
		int iorigem = 0,isaida = 0;
		int saida[] = new int[origem.length - 1];
		while(iorigem < origem.length){
			if(origem[iorigem] != valor){
				saida[isaida] = origem[iorigem];
				isaida++;
			}
			iorigem++;
		}
		return saida;
	}
	
	public void IniciarTabuleiro(int n){
		int valores[] = {1,2,3,4,5,6,7};
		int copia[] = new int[n-1];
		int copia2[] = new int[n-2];
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				tabuleiro[i][j] = valores[rand.nextInt(n)];
				if(i - 1 >= 0 && i - 2 >= 0){
					if(tabuleiro[i-1][j] == tabuleiro[i][j] && tabuleiro[i-2][j] == tabuleiro[i][j]){
						copia = CopiarRemovendo(valores,tabuleiro[i][j]);
						tabuleiro[i][j] = copia[rand.nextInt(n-1)];
						if(j-1 >= 0 && j-2 >= 0){
							if(tabuleiro[i][j-1] == tabuleiro[i][j] && tabuleiro[i][j-2] == tabuleiro[i][j]){
								copia2 = CopiarRemovendo(copia,tabuleiro[i][j]);
								tabuleiro[i][j] = copia2[rand.nextInt(n-2)];
							}
						}
					}
				}
				if(j -1 >= 0 && j -2 >= 0){
					if(tabuleiro[i][j-1] == tabuleiro[i][j] && tabuleiro[i][j-2] == tabuleiro[i][j]){
						copia = CopiarRemovendo(valores,tabuleiro[i][j]);
						tabuleiro[i][j] = copia[rand.nextInt(n-1)];
						if(i-1 >= 0 && i-2 >= 0){
							if(tabuleiro[i-1][j] == tabuleiro[i][j] && tabuleiro[i-2][j] == tabuleiro[i][j]){
								copia2 = CopiarRemovendo(copia,tabuleiro[i][j]);
								tabuleiro[i][j] = copia2[rand.nextInt(n-2)];
							}
						}
					}
				}
			}
		}
		if(ExistemPeloMenosTresAlinhadasHorizontalmente(tabuleiro) || ExistemPeloMenosTresAlinhadasVerticalmente(tabuleiro)){
			System.out.println("Psiu,eu entraria em recursão");
			IniciarTabuleiro(n);
		}
	}
	
	public boolean ExistemPeloMenosTresAlinhadasHorizontalmente(int tab[][]){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 6; j++){
				if(tab[i][j + 1] == tab[i][j] && tab[i][j + 2] == tab[i][j]){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean ExistemPeloMenosTresAlinhadasVerticalmente(int tab[][]){
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 8; j++){
				if(tab[i+1][j] == tab[i][j] && tab[i+2][j] == tab[i][j]){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean ExisteCruz(int tab[][]){
		int i,j;
		for(i = 0; i < 6; i++){
			for(j = 1; j < 7; j++){
				if((tab[i][j- 1] == tab[i][j] && tab[i][j + 1] == tab[i][j] && tab[i+1][j] == tab[i][j] && tab[i+2][j] == tab[i][j]) 
				|| (tab[i+1][j] == tab[i][j] && tab[i+2][j] == tab[i][j] && tab[i+2][j-1] == tab[i][j] && tab[i+2][j+1] == tab[i][j])
				|| (tab[i+1][j] == tab[i][j] && tab[i+1][j-1] == tab[i][j] && tab[i+1][j+1] == tab[i][j] && tab[i+2][j] == tab[i][j])){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean PodeTrocarPecas(int i1,int j1,int i2,int j2){
		int c1 = 8*i1 + j1;
		int c2 = 8*i2 + j2;
		if(Math.abs(c1 - c2) == 8 || Math.abs(c1 - c2) == 1){
			int TabAux[][] = new int[8][8];
			for(int i = 0; i < 8; i++){
				for(int j = 0; j < 8; j++){
					TabAux[i][j] = tabuleiro[i][j];
				}
			}
			TabAux[i1][j1] = tabuleiro[i2][j2];
			TabAux[i2][j2] = tabuleiro[i1][j1];
			if(ExistemPeloMenosTresAlinhadasHorizontalmente(TabAux) || ExistemPeloMenosTresAlinhadasVerticalmente(TabAux)){
				return true;
			}
		}
		return false;
	}
	
	public void TrocarPecas(int i1,int j1,int i2,int j2){
		int tabAux[][] = new int[1][1];
		tabAux[0][0] = tabuleiro[i1][j1];
		tabuleiro[i1][j1] = tabuleiro[i2][j2];
		tabuleiro[i2][j2] = tabAux[0][0];
	}
	
	public void Mover_para_Baixo(JLabel blocos[][], JLabel frame){
		int i,j,i_aux;
		int col,lin;
		for(j = 0; j < 8; j++){
			for(i = 7; i > 0; i--){
				if(tabuleiro[i][j] == -10){
					i_aux = i - 1;
					int dq = 1;
				    while(tabuleiro[i][j] == -10 && i_aux >= 0){
				    	if(tabuleiro[i_aux][j] != -10){
					    	lin = blocos[i_aux][j].getY();
					    	col = blocos[i_aux][j].getX();
					    	long expectedtime = System.currentTimeMillis();
					    	int nd = 0;
							while (nd <= 51*dq) {//Or any Loops
								while(System.currentTimeMillis() < expectedtime){
							     //Empty Loop   
							   }
							   expectedtime +=1.5;
							   mapa.get(tabuleiro[i_aux][j]).paintIcon(frame, frame.getGraphics(),col, lin+nd);
							   nd++;
							}
				    	}
				        tabuleiro[i][j] = tabuleiro[i_aux][j];
				        tabuleiro[i_aux][j] = -10;
				        i_aux--;
				        dq++;
				    }
				}
			}
		}
	}
	
	public void AcrescentarNovosBlocos(JLabel frame, int n){
		int i,j;
		for(i = 7; i >= 0; i--){
			for(j = 0; j < 8; j++){
				if(tabuleiro[i][j] == -10){
					tabuleiro[i][j] = 1 + rand.nextInt(n);
					long expectedtime = System.currentTimeMillis();
			    	int nd = 0;
					while (nd <= 51*i) {//Or any Loops
						while(System.currentTimeMillis() < expectedtime){
					     //Empty Loop   
					   }
					   expectedtime +=1.5;
					   mapa.get(tabuleiro[i][j]).paintIcon(frame, frame.getGraphics(),245 + j*51, 130+nd);
					   nd++;
					}
				}
			}
		}
	}
	
	public int ProcessarCombinacaoVertical(){
		int i,j;
		int pontuacao = 0;
		for(i = 0; i < 6; i++){
			for(j = 0; j < 8; j++){
				if(tabuleiro[i+1][j] == tabuleiro[i][j] && tabuleiro[i+2][j] == tabuleiro[i][j]){
					if(i < 5){
						if(tabuleiro[i+3][j] == tabuleiro[i][j]){
							if(i < 4){
								if(tabuleiro[i+4][j] == tabuleiro[i][j]){
									pontuacao = 30;
									tabuleiro[i][j]   = -10;
									tabuleiro[i+1][j] = -10;
									tabuleiro[i+2][j] = -10;//troca especial
									tabuleiro[i+3][j] = -10;
									tabuleiro[i+4][j] = -10;
									return pontuacao;
								}
								else{
									pontuacao = 15;
									tabuleiro[i][j]   = -10;
									tabuleiro[i+1][j] = -10;
									tabuleiro[i+2][j] = -10;//troca special
									tabuleiro[i+3][j] = -10;
									return pontuacao;
								}
							}
							else{
								pontuacao = 15;
								tabuleiro[i][j]   = -10;
								tabuleiro[i+1][j] = -10;
								tabuleiro[i+2][j] = -10;//troca special
								tabuleiro[i+3][j] = -10;
								return pontuacao;
							}
						}
						else{
							pontuacao = 10;
							tabuleiro[i][j]   = -10;
							tabuleiro[i+1][j] = -10;//troca especial
							tabuleiro[i+2][j] = -10;
							return pontuacao;
						}
					}
					else{
						pontuacao = 10;
						tabuleiro[i][j]   = -10;
						tabuleiro[i+1][j] = -10;//troca especial
						tabuleiro[i+2][j] = -10;
						return pontuacao;
					}
				}
			}
		}
		return pontuacao;
	}
	
	public int ProcessarCombinacaoHorizontal(){
		int i,j;
		int pontuacao = 0;
		for(i = 0; i < 8; i++){
			for(j = 0; j < 6; j++){
				if(tabuleiro[i][j + 1] == tabuleiro[i][j] && tabuleiro[i][j + 2] == tabuleiro[i][j]){
					if(j < 5){
						if(tabuleiro[i][j + 3] == tabuleiro[i][j]){
							if(j < 4){
								if(tabuleiro[i][j + 4] == tabuleiro[i][j]){
									pontuacao = 30;
									tabuleiro[i][j]   = -10;
									tabuleiro[i][j+1] = -10;
									tabuleiro[i][j+2] = -10;//troca especial
									tabuleiro[i][j+3] = -10;
									tabuleiro[i][j+4] = -10;
									return pontuacao;
								}
								else{
									pontuacao = 15;
									tabuleiro[i][j]   = -10;
									tabuleiro[i][j+1] = -10;
									tabuleiro[i][j+2] = -10;//troca special
									tabuleiro[i][j+3] = -10;
									return pontuacao;
								}
							}
							else{
								pontuacao = 15;
								tabuleiro[i][j]   = -10;
								tabuleiro[i][j+1] = -10;
								tabuleiro[i][j+2] = -10;//troca special
								tabuleiro[i][j+3] = -10;
								return pontuacao;
							}
						}
						else{
							pontuacao = 10;
							tabuleiro[i][j]   = -10;
							tabuleiro[i][j+1] = -10;//troca especial
							tabuleiro[i][j+2] = -10;
							return pontuacao;
						}
					}
					else{
						pontuacao = 10;
						tabuleiro[i][j]   = -10;
						tabuleiro[i][j+1] = -10;//troca especial
						tabuleiro[i][j+2] = -10;
						return pontuacao;
					}
				}
			}
		}
		return pontuacao;
	}
	
	public boolean ExisteL1(int i, int j, int tab[][]){
		if(i-1 >= 0 && i-2 >= 0 && j-1 >= 0 && j-2 >= 0 
		   && tab[i-1][j] == tab[i][j] && tab[i-2][j] == tab[i][j] && tab[i-2][j-1] == tab[i][j] && tab[i-2][j-2] == tab[i][j]){
			return true;
		}
		return false;
	}
	
	public int ProcessarL1(int i,int j){
		tabuleiro[i][j] = -10;
		tabuleiro[i-1][j] = -10;
		tabuleiro[i-2][j] = -10;
		tabuleiro[i-2][j-1] = -10;
		tabuleiro[i-2][j-2] = -10;
		return 50;
	}
	
	public boolean ExisteL2(int i, int j, int tab[][]){
		if(i-1 >= 0 && i-2 >= 0 && j+1 < 8 && j+2 < 8 
				   && tab[i-1][j] == tab[i][j] && tab[i-2][j] == tab[i][j] && tab[i-2][j+1] == tab[i][j] && tab[i-2][j+2] == tab[i][j]){
					return true;
		}
		return false;
	}
	
	public int ProcessarL2(int i, int j){
		tabuleiro[i][j] = -10;
		tabuleiro[i-1][j] = -10;
		tabuleiro[i-2][j] = -10;
		tabuleiro[i-2][j+1] = -10;
		tabuleiro[i-2][j+2] = -10;
		return 50;
	}
	
	public boolean ExisteL3(int i, int j, int tab[][]){
		if(i+1 < 8 && i+2 < 8 && j-1 >= 0 && j-2 >= 0
		   && tab[i][j-1] == tab[i][j] && tab[i][j-2] == tab[i][j] && tab[i+1][j-2] == tab[i][j] && tab[i+2][j-2] == tab[i][j]){
			return true;
		}
		return false;
	}
	
	public int ProcessarL3(int i, int j){
		tabuleiro[i][j] = -10;
		tabuleiro[i][j-1] = -10;
		tabuleiro[i][j-2] = -10;
		tabuleiro[i+1][j-2] = -10;
		tabuleiro[i+2][j-2] = -10;
		return 50;
	}
	
	public boolean ExisteL4(int i, int j, int tab[][]){
		if(i-1 >= 0 && i-2 >= 0 && j-1 >= 0 && j-2 >= 0
		   && tab[i][j-1] == tab[i][j] && tab[i][j-2] == tab[i][j] && tab[i-1][j-2] == tab[i][j] && tab[i-2][j-2] == tab[i][j]){
			return true;
		}
		return false;
	}
	
	public int ProcessarL4(int i, int j){
		tabuleiro[i][j] = -10;
		tabuleiro[i][j-1] = -10;
		tabuleiro[i][j-2] = -10;
		tabuleiro[i-1][j-2] = -10;
		tabuleiro[i-2][j-2] = -10;
		return 50;
	}
	
	public boolean ExisteL5(int i, int j, int tab[][]){
		if(i+1 < 8 && i+2 < 8 && j+1 < 8 && j+2 < 8
		  && tab[i][j+1] == tab[i][j] && tab[i][j+2] == tab[i][j] && tab[i+1][j+2] == tab[i][j] && tab[i+2][j+2] == tab[i][j]){
			return true;
		}
		return false;
	}
	
	public int ProcessarL5(int i, int j){
		tabuleiro[i][j] = -10;
		tabuleiro[i][j+1] = -10;
		tabuleiro[i][j+2] = -10;
		tabuleiro[i+1][j+2] = -10;
		tabuleiro[i+2][j+2] = -10;
		return 50;
	}
	
	public boolean ExisteL6(int i, int j, int tab[][]){
		if(i-1 >= 0 && i-2 >= 0 && j+1 < 8 && j+2 < 8
		  && tab[i][j+1] == tab[i][j] && tab[i][j+2] == tab[i][j] && tab[i-1][j+2] == tab[i][j] && tab[i-2][j+2] == tab[i][j]){
			return true;
		}
		return false;
	}
	
	public int ProcessarL6(int i, int j){
		tabuleiro[i][j] = -10;
		tabuleiro[i][j+1] = -10;
		tabuleiro[i][j+2] = -10;
		tabuleiro[i-1][j+2] = -10;
		tabuleiro[i-2][j+2] = -10;
		return 50;
	}
	
	public boolean ExisteL7(int i, int j, int tab[][]){
		if(i+1 < 8 && i+2 < 8 && j-1 >=0 && j-2 >= 0
		  && tab[i+1][j] == tab[i][j] && tab[i+2][j] == tab[i][j] && tab[i+2][j-1] == tab[i][j] && tab[i+2][j-2] == tab[i][j]){
			return true;
		}
		return false;
	}
	
	public int ProcessarL7(int i, int j){
		tabuleiro[i][j] = -10;
		tabuleiro[i+1][j] = -10;
		tabuleiro[i+2][j] = -10;
		tabuleiro[i+2][j-1] = -10;
		tabuleiro[i+2][j-2] = -10;
		return 50;
	}
	
	public boolean ExisteL8(int i, int j, int tab[][]){
		if(i+1 < 8 && i+2 < 8 && j+1 < 8 && j+2 < 8
		  && tab[i+1][j] == tab[i][j] && tab[i+2][j] == tab[i][j] && tab[i+2][j+1] == tab[i][j] && tab[i+2][j+2] == tab[i][j]){
			return true;
		}
		return false;
	}
	
	public int ProcessarL8(int i, int j){
		tabuleiro[i][j] = -10;
		tabuleiro[i+1][j] = -10;
		tabuleiro[i+2][j] = -10;
		tabuleiro[i+2][j+1] = -10;
		tabuleiro[i+2][j+2] = -10;
		return 50;
	}
	
	public boolean ExisteCruz1(int i, int j, int tab[][]){
		if(j-1 >= 0 && j+1 < 8 && i+1 < 8 && i+2 < 8){
			if(tab[i][j-1] == tab[i][j] && tab[i][j+1] == tab[i][j] && tab[i+1][j] == tab[i][j] && tab[i+2][j] == tab[i][j]){
				return true;
			}
		}
		return false;
	}
	
	public int ProcessarCruz1(int i, int j){
		tabuleiro[i][j] = -10;
		tabuleiro[i][j-1] = -10;
		tabuleiro[i][j+1] = -10;
		tabuleiro[i+1][j] = -10;
		tabuleiro[i+2][j] = -10;
		return 60;
	}
	
	public boolean ExisteCruz2(int i, int j, int tab[][]){
		if(j-1 >= 0 && j+1 < 8 && i-1 >= 0 && i-2 >= 0){
			if(tab[i][j-1] == tab[i][j] && tab[i][j+1] == tab[i][j] && tab[i-1][j] == tab[i][j] && tab[i-2][j] == tab[i][j]){
				return true;
			}
		}
		return false;
	}
	
	public int ProcessarCruz2(int i, int j){
		tabuleiro[i][j] = -10;
		tabuleiro[i][j-1] = -10;
		tabuleiro[i][j+1] = -10;
		tabuleiro[i-1][j] = -10;
		tabuleiro[i-2][j] = -10;
		return 60;
	}
	
	public boolean ExisteCruz3(int i, int j, int tab[][]){
		if(j-1 >= 0 && j+1 < 8 && i+1 < 8 && i-1 >= 0){
			if(tab[i][j-1] == tab[i][j] && tab[i][j+1] == tab[i][j] && tab[i+1][j] == tab[i][j] && tab[i-1][j] == tab[i][j]){
				return true;
			}
		}
		return false;
	}
	
	public int ProcessarCruz3(int i, int j){
		tabuleiro[i][j] = -10;
		tabuleiro[i][j-1] = -10;
		tabuleiro[i][j+1] = -10;
		tabuleiro[i+1][j] = -10;
		tabuleiro[i-1][j] = -10;
		return 60;
	}
	
	public boolean ExisteCruz4(int i, int j, int tab[][]){
		if(j-1 >= 0 && j-2 >= 0 && i+1 < 8 && i-1 >= 0){
			if(tab[i][j-1] == tab[i][j] && tab[i][j-2] == tab[i][j] && tab[i+1][j] == tab[i][j] && tab[i-1][j] == tab[i][j]){
				return true;
			}
		}
		return false;
	}
	
	public int ProcessarCruz4(int i, int j){
		tabuleiro[i][j] = -10;
		tabuleiro[i][j-1] = -10;
		tabuleiro[i][j-2] = -10;
		tabuleiro[i+1][j] = -10;
		tabuleiro[i-1][j] = -10;
		return 60;
	}
	
	public boolean ExisteCruz5(int i, int j, int tab[][]){
		if(j+1 < 8 && j+2 < 8 && i+1 < 8 && i-1 >= 0){
			if(tab[i][j+1] == tab[i][j] && tab[i][j+2] == tab[i][j] && tab[i+1][j] == tab[i][j] && tab[i-1][j] == tab[i][j]){
				return true;
			}
		}
		return false;
	}
	
	public int ProcessarCruz5(int i, int j){
		tabuleiro[i][j] = -10;
		tabuleiro[i][j+1] = -10;
		tabuleiro[i][j+2] = -10;
		tabuleiro[i+1][j] = -10;
		tabuleiro[i-1][j] = -10;
		return 60;
	}
	
	public boolean ExisteCruz6(int i, int j, int tab[][]){
		if(j-1 >= 0 && j-2 >= 0 && j+1 < 8 && j+2 < 8 && i-1 >= 0 && i-2 >= 0){
			if(tab[i][j-1] == tab[i][j] && tab[i][j-2] == tab[i][j] && tab[i][j+1] == tab[i][j]
			&& tab[i][j+2] == tab[i][j] && tab[i-1][j] == tab[i][j] && tab[i-2][j] == tab[i][j]){
				return true;
			}
		}
		return false;
	}
	
	public int ProcessarCruz6(int i, int j){
		tabuleiro[i][j] = -10;
		tabuleiro[i][j-1] = -10;
		tabuleiro[i][j-2] = -10;
		tabuleiro[i][j+1] = -10;
		tabuleiro[i][j+2] = -10;
		tabuleiro[i-1][j] = -10;
		tabuleiro[i-2][j] = -10;
		return 80;
	}
	
	public boolean ExisteCruz7(int i, int j, int tab[][]){
		if(j-1 >= 0 && j-2 >= 0 && j+1 < 8 && j+2 < 8 && i+1 < 8 && i+2 < 8){
			if(tab[i][j-1] == tab[i][j] && tab[i][j-2] == tab[i][j] && tab[i][j+1] == tab[i][j]
			&& tab[i][j+2] == tab[i][j] && tab[i+1][j] == tab[i][j] && tab[i+2][j] == tab[i][j]){
				return true;
			}
		}
		return false;
	}
	
	public int ProcessarCruz7(int i, int j){
		tabuleiro[i][j] = -10;
		tabuleiro[i][j-1] = -10;
		tabuleiro[i][j-2] = -10;
		tabuleiro[i][j+1] = -10;
		tabuleiro[i][j+2] = -10;
		tabuleiro[i+1][j] = -10;
		tabuleiro[i+2][j] = -10;
		return 80;
	}
	
	public boolean ExisteCruz8(int i, int j, int tab[][]){
		if(j-1 >= 0 && j-2 >= 0 && j+1 < 8 && j+2 < 8 && i-1 >= 0 && i+1 < 8){
			if(tab[i][j-1] == tab[i][j] && tab[i][j-2] == tab[i][j] && tab[i][j+1] == tab[i][j]
			&& tab[i][j+2] == tab[i][j] && tab[i-1][j] == tab[i][j] && tab[i+1][j] == tab[i][j]){
				return true;
			}
		}
		return false;
	}
	
	public int ProcessarCruz8(int i, int j){
		tabuleiro[i][j] = -10;
		tabuleiro[i][j-1] = -10;
		tabuleiro[i][j-2] = -10;
		tabuleiro[i][j+1] = -10;
		tabuleiro[i][j+2] = -10;
		tabuleiro[i-1][j] = -10;
		tabuleiro[i+1][j] = -10;
		return 80;
	}
	
	public int ProcessarCruz(){
		int i,j;
		int pontuacao = 0;
		for(i = 0; i < 6; i++){
			for(j = 1; j < 7; j++){
				if((tabuleiro[i][j- 1] == tabuleiro[i][j] && tabuleiro[i][j + 1] == tabuleiro[i][j] && tabuleiro[i+1][j] == tabuleiro[i][j] && tabuleiro[i+2][j] == tabuleiro[i][j])){
					pontuacao = 40;
					tabuleiro[i][j-1] = -10;
					tabuleiro[i][j+1] = -10;
					tabuleiro[i][j] = -10;
					tabuleiro[i+1][j] = -10;
					tabuleiro[i+2][j] = -10;
					return pontuacao;
				}
				if((tabuleiro[i+1][j] == tabuleiro[i][j] && tabuleiro[i+2][j] == tabuleiro[i][j] && tabuleiro[i+2][j-1] == tabuleiro[i][j] && tabuleiro[i+2][j+1] == tabuleiro[i][j])){
					pontuacao = 40;
					tabuleiro[i][j] = -10;
					tabuleiro[i+1][j] = -10;
					tabuleiro[i+2][j] = -10;
					tabuleiro[i+2][j-1] = -10;
					tabuleiro[i+2][j+1] = -10;
					return pontuacao;
				}
				if((tabuleiro[i+1][j] == tabuleiro[i][j] && tabuleiro[i+1][j-1] == tabuleiro[i][j] && tabuleiro[i+1][j+1] == tabuleiro[i][j] && tabuleiro[i+2][j] == tabuleiro[i][j])){
					pontuacao = 70;
					tabuleiro[i][j] = -10;
					tabuleiro[i+1][j] = -10;
					tabuleiro[i+1][j-1] = -10;
					tabuleiro[i+1][j+1] = -10;
					tabuleiro[i+2][j] = -10;
					return pontuacao;
				}
			}
		}
		return pontuacao;
	}
	
	public int ProcessarCombinacoes(){
		int pontuacao = 0;
		if(ExistemPeloMenosTresAlinhadasHorizontalmente(tabuleiro) && !ExistemPeloMenosTresAlinhadasVerticalmente(tabuleiro)){
			pontuacao = ProcessarCombinacaoHorizontal();
			return pontuacao;
		}
		if(!ExistemPeloMenosTresAlinhadasHorizontalmente(tabuleiro) && ExistemPeloMenosTresAlinhadasVerticalmente(tabuleiro)){
			pontuacao = ProcessarCombinacaoVertical();
			return pontuacao;
		}
		if(ExistemPeloMenosTresAlinhadasHorizontalmente(tabuleiro) && ExistemPeloMenosTresAlinhadasVerticalmente(tabuleiro)){
			for(int i = 0; i < 8; i++){
				for(int j = 0; j < 8; j++){
					if(ExisteCruz8(i,j,tabuleiro)){
						pontuacao = ProcessarCruz8(i,j);
						return pontuacao;
					}
					if(ExisteCruz7(i,j,tabuleiro)){
						pontuacao = ProcessarCruz7(i,j);
						return pontuacao;
					}
					if(ExisteCruz6(i,j,tabuleiro)){
						pontuacao = ProcessarCruz6(i,j);
						return pontuacao;
					}
					if(ExisteCruz5(i,j,tabuleiro)){
						pontuacao = ProcessarCruz5(i,j);
						return pontuacao;
						}
					if(ExisteCruz4(i,j,tabuleiro)){
						pontuacao = ProcessarCruz4(i,j);
						return pontuacao;
					}
					if(ExisteCruz3(i,j,tabuleiro)){
						pontuacao = ProcessarCruz3(i,j);
						return pontuacao;
					}
					if(ExisteCruz2(i,j,tabuleiro)){
						pontuacao = ProcessarCruz2(i,j);
						return pontuacao;
					}
					if(ExisteCruz1(i,j,tabuleiro)){
						pontuacao = ProcessarCruz1(i,j);
						return pontuacao;
					}
					if(ExisteL1(i,j,tabuleiro)){
						pontuacao = ProcessarL1(i,j);
						return pontuacao;
					}
					if(ExisteL2(i,j,tabuleiro)){
						pontuacao = ProcessarL2(i,j);
						return pontuacao;
					}
					if(ExisteL3(i,j,tabuleiro)){
						pontuacao = ProcessarL3(i,j);
						return pontuacao;
					}
					if(ExisteL4(i,j,tabuleiro)){
						pontuacao = ProcessarL4(i,j);
						return pontuacao;
					}
					if(ExisteL5(i,j,tabuleiro)){
						pontuacao = ProcessarL5(i,j);
						return pontuacao;
					}
					if(ExisteL6(i,j,tabuleiro)){
						pontuacao = ProcessarL6(i,j);
						return pontuacao;
					}
					if(ExisteL7(i,j,tabuleiro)){
						pontuacao = ProcessarL7(i,j);
						return pontuacao;
					}
					if(ExisteL8(i,j,tabuleiro)){
						pontuacao = ProcessarL8(i,j);
						return pontuacao;
					}
				}
			}
			pontuacao = ProcessarCombinacaoVertical();
			return pontuacao;
		}
		return pontuacao;
	}
	
	public boolean GameOver(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(i-1 >= 0){
					if(PodeTrocarPecas(i,j,i-1,j)){
						return false;
					}
				}
				if(i+1 < 8){
					if(PodeTrocarPecas(i,j,i+1,j)){
						return false;
					}
				}
				if(j-1 >= 0){
					if(PodeTrocarPecas(i,j,i,j-1)){
						return false;
					}
				}
				if(j+1 < 8){
					if(PodeTrocarPecas(i,j,i,j+1)){
						return false;
					}
				}
			}
		}
		return true;
	}
}
