package principal;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dijkstra.Dijkstra;


public class Principal  extends JFrame{

	private Dijkstra Dij;
	private static pintaMe pantalla;
	
	
	public  Principal(){
		
		
		/* ############################################
		 * DATOS PARA USAR Dijkstra, devuelve un array.
		 * #############################################
		 */
		
		int facesX = 7;
		int facesY = 7;
		
		int[][] obstacles = obstaclesDemo(facesX, facesY);
		
		// Instanciamos  con los valores e inicializamos valores:
		// Dijkstra ( X del objetivo, 
		//            Y del Objetivo,
		//            Lados en X, 
		//            Lados en Y, 
		//             Array[][] de obstaculos: 1 tiene obstaculo y 0 libre)
		
		Dij = new Dijkstra(6, 1, facesX, facesY, obstacles);
		
		// Genera el Array[][] con los valores Dijkstra.
		Dij.crear();
		
		
		/* #################################################
		 * Fin uso Dijkstra.
		 * #################################################
		 */
		
		
		
		pantalla = new pintaMe(Dij);
		
		setSize(800,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setFocusable(true);
		setBackground(Color.WHITE);
		
	    add(pantalla);
	    
	    setVisible(true);
	}

	 
	
	private int[][] obstaclesDemo(int facesX, int facesY){

		int x = 0;
		int y = 0;
		int[][] obs = new int[facesX][facesY];
		
		for(int i=0; i<(facesX*facesY); i++){
			
				if(x==facesX){
				x=0;
				y++;
				}
				
				if((x==1 && y==2) || 
						(x==4 && y==1) ||
						(x==4 && y==2) ||
						(x==4 && y==3) ||
						(x==4 && y==4) ||
						(x==2 && y==5) ||
						(x==2 && y==6) ){
					
					obs[x][y]=1;
					
				} else {
					obs[x][y]=0;
				}
		
				x +=1;
				
	}
		return obs;
	}
	
	
	
	
	public static void main(String[] args) {
		
		Principal maler = new Principal();
		}

	
	
	
}


class pintaMe extends JPanel{
	
	
	private int[][] obstacules;
	private int[][] mapaDi;
	private int facesX;
	private int facesY;
	
	public pintaMe(Dijkstra Dij){
		
		this.obstacules = Dij.getObstaculos();
		this.mapaDi = Dij.getMapaDi();
		this.facesX = Dij.getFacesX();
		this.facesY = Dij.getFacesY();
		
	}
	
	public void paint(Graphics g){
		int x=0;
		int y=0;
		int positionX=60;
		int positionY=170;
		
		
		Color myColour;	
		
		for(int i=0;i<(facesX*facesY);i++){
			
			if(x==facesX){
				x=0;
				if(y==facesY) {y=0;} else {y++;}
				}
			
			if(obstacules[x][y]==1){
				g.setColor(Color.BLACK);
			}  else {
				g.setColor(Color.GRAY);}
			
			if (mapaDi[x][y]>=0){
				if(mapaDi[x][y]==0){
					
					myColour = new Color(220, 50, 4, 127);
					g.setColor(myColour);
				} else {
					
					myColour = new Color(220, 255, 4, 127);
					g.setColor(myColour);	
				}
				
			}
			
			
			g.fillRect(positionX+x*40, positionY+y*40, 40, 40);
			g.setColor(Color.DARK_GRAY);
			g.drawRect(positionX+x*40, positionY+y*40, 40, 40);
			
			
			
			g.drawString(""+mapaDi[x][y], (positionX+x*40)+20, (positionY+y*40)+20);
			x++;
		}
		
		
		
		g.drawImage(loadImg("recursos/logo_Dijkstra.png"), 10, 10, this);
		
		g.drawImage(loadImg("recursos/cuadro.png"), 440, positionY-40, this);
		
		g.setColor(Color.GRAY);
		g.drawString("By: Eduard Pinuaga", 10, 700);
		g.drawString("Code und info: https://github.com/Didweb", 10, 730);
		
	}
	
	
	
	
	private BufferedImage loadImg(String path){
		BufferedImage imagen = null;
		try {
			imagen = ImageIO.read(new FileInputStream(path));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return imagen;
	}
	

	
}
