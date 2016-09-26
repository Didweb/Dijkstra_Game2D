package dijkstra;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;





public class Dijkstra extends JFrame {

	private int[][] mapaDi;
	private boolean[][] visitado;
	private int[][] obstaculos;
	
	
	private static pintaMe pantalla;
	
	private int nLados = 15;
	
	
	public Dijkstra(){
		iniTodo();
		mapaDi[2][3]=0;
		visitado[2][3]=true;
		crear();
		
		pantalla = new pintaMe();
		
		
		setSize(800,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setFocusable(true);
		
		add(pantalla);
		
		setVisible(true);
		
		
	}
	
	
	
	public void crear(){
		
		int porHacer=nLados*nLados;
		
		while(porHacer>0){
			
		System.out.println(porHacer);
			int x=0;
			int y=0;
			
			for(int i=0;i<(nLados*nLados);i++){
				if(x==nLados){
					x=0;
					y++;
					}
				
				if(obstaculos[x][y]==1){
					visitado[x][y]=true;
				}
				if(visitado[x][y]==true && obstaculos[x][y]!=1){
					if((x-1)>=0){
						if(visitado[x-1][y]==false && obstaculos[x-1][y]!=1){
							mapaDi[x-1][y]=mapaDi[x][y]+1;
							visitado[x-1][y]=true;
						}
					}
					if((x+1)<=nLados){
						if(visitado[x+1][y]==false  && obstaculos[x+1][y]!=1){
							mapaDi[x+1][y]=mapaDi[x][y]+1;
							visitado[x+1][y]=true;
						}
					}
					if((y-1)>=0){
						if(visitado[x][y-1]==false  && obstaculos[x][y-1]!=1){
							mapaDi[x][y-1]=mapaDi[x][y]+1;
							visitado[x][y-1]=true;
						}
					}
					if((y+1)<=nLados){
						if(visitado[x][y+1]==false  && obstaculos[x][y+1]!=1){
							mapaDi[x][y+1]=mapaDi[x][y]+1;
							visitado[x][y+1]=true;
						}
					}
				
					
				}
				System.out.println(x+","+y+" : "+visitado[x][y]);
				x++;
				
			}
			x=0;
			y=0;
			int control=0;
					for(int i=0;i<(nLados*nLados);i++){
						if(x==nLados){
							x=0;
							y++;
							}
						if(visitado[x][y]==false){
							control++;
						}
						x++;
					}
			
					porHacer=control;
		
			}
		}
	
	
	
	
	public void iniTodo(){
		
		mapaDi = new int[nLados*nLados][nLados*nLados];
		visitado = new boolean[nLados*nLados][nLados*nLados];
		
		obstaculos = new int[nLados*nLados][nLados*nLados];
		int x=0;
		int y=0;
		for(int i=0;i<(nLados*nLados);i++){
			
			if(x==nLados){
			x=0;
			y++;
			}
			
			if((x==3 && y==1) || 
					(x==4 && y==1) ||
					(x==1 && y==3) ||
					(x==1 && y==4) ||
					(x==4 && y==3) ||
					(x==4 && y==4) ||
					(x==6 && y==5) ||
					(x==6 && y==6) ||
					(x==11 && y==12) ||
					(x==11 && y==11) ||
					(x==11 && y==10) ||
					(x==11 && y==9) ||
					(x==11 && y==8) ||
					(x==13 && y==13)){
				
				obstaculos[x][y]=1;
			} else {
				obstaculos[x][y]=0;
			}
			mapaDi[x][y]=-1;
			visitado[x][y] = false;
			System.out.println(x+","+y+" = "+obstaculos[x][y]);
			x++;
			
		}
		
	}

	public static void main(String[] args)  {
		
	    
	    
		Dijkstra Alo = new Dijkstra();
		

	}	

	
	public class pintaMe extends JPanel{
		
		
		
		public pintaMe(){
			
			
			
		}
		
		public void paint(Graphics g){
			int x=0;
			int y=0;
			
			int alpha = 127; // 50% transparent
			Color myColour;	
			
			for(int i=0;i<obstaculos.length;i++){
				
				if(x==nLados){
					x=0;
					y++;
					}
				
				if(obstaculos[x][y]==1){
					g.setColor(Color.BLACK);
				}  else {
					g.setColor(Color.GRAY);}
				
				if (mapaDi[x][y]>=0){
					if(mapaDi[x][y]==0){
						
						myColour = new Color(220, 255, 4, alpha);
						g.setColor(myColour);
					} else {
						myColour = new Color(220, 255-(mapaDi[x][y]*2), 4, alpha);
						g.setColor(myColour);	
					}
					
				}
				
				
				g.fillRect(x*40, y*40, 40, 40);
				g.setColor(Color.DARK_GRAY);
				g.drawRect(x*40, y*40, 40, 40);
				
				
				
				g.drawString(""+mapaDi[x][y], (x*40)+20, (y*40)+20);
				x++;
			}
		}
	}	

}




