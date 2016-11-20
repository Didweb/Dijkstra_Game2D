package dijkstra;

/*
 * Dijkstra_Game2D
 * 
 * Eduard Pinuaga Linares
 * <info@did-web.com>
 * 
 * Devuelve un array con los valores Dijkstra de cada casilla. 
 * Toda la información en https://github.com/Didweb/Dijkstra_Game2D
 * 
 */



public class Dijkstra  {

	private int[][] mapDestiny;
	private boolean[][] visited;
	private int[][] obstacles;
	
	
	
	
	private int facesX;
	private int facesY;
	
	
	public Dijkstra(int destinyX, int destinyY, int facesX, int facesY, int[][] obstacles){
		
		
		int x=0;
		int y=0;
		
		mapDestiny = new int[facesX*facesY][facesX*facesY];
		visited = new boolean[facesX*facesY][facesX*facesY];
		
		this.obstacles = obstacles;
		this.facesX = facesX;
		this.facesY = facesY;
		
		
		
		// Inicializamos los array con valores de partida.
		for(int i=0;i<(facesX*facesY);i++){
			if(x==facesX){
				x=0;
				if(y==facesY) {y=0;} else {y++;}
				}
				mapDestiny[x][y]=-1;
				visited[x][y] = false;
				x++;
			}
		
		
		// Situamos el punto de inicio
		mapDestiny[destinyX][destinyY]=0;
		visited[destinyX][destinyY]=true;
		

	}
	
	
	



	public void crear(){

		int porHacer=facesX*facesY;
		

		while(porHacer>0){
			
		
			int x=0;
			int y=0;

			
			for(int i=0;i<(facesX*facesY);i++){
				if(x==facesX){
					x=0;
					if(y==facesY) {y=0;} else {y++;}
					}
				
				// Si en esa posición hay un obstaculo se marca como visitado.
				if(obstacles[x][y]==1){
					visited[x][y]=true;
				}
				
				if(visited[x][y]==true && obstacles[x][y]!=1){
					if((x-1)>=0){
						if(visited[x-1][y]==false && obstacles[x-1][y]!=1){
							mapDestiny[x-1][y]=mapDestiny[x][y]+1;
							visited[x-1][y]=true;
						}
						
					}
					
					if((x+1)<=facesX-1){
						if(visited[x+1][y]==false  && obstacles[x+1][y]!=1){
							mapDestiny[x+1][y]=mapDestiny[x][y]+1;
							visited[x+1][y]=true;
						}
						
					}
					
					if((y-1)>=0){
						if(visited[x][y-1]==false  && obstacles[x][y-1]!=1){
							mapDestiny[x][y-1]=mapDestiny[x][y]+1;
							visited[x][y-1]=true;
						}
					}
					if((y+1)<=facesY-1){
						if(visited[x][y+1]==false  && obstacles[x][y+1]!=1){
							mapDestiny[x][y+1]=mapDestiny[x][y]+1;
							visited[x][y+1]=true;
						}
					}
				
					
				}
				
				x++;
				
			}
			x=0;
			y=0;
			int control=0;
					for(int i=0;i<(facesX*facesY);i++){
						if(x==facesX-1){
							x=0;
							if(y==facesY-1) {y=0;} else {y++;}
							}
						if(visited[x][y]==false){
							control += 1;
						}
						x++;
					}
					porHacer=control;
			}
		
		}
	
	

	public int[][] getMapaDi() {
		return mapDestiny;
	}



	public boolean[][] getVisitado() {
		return visited;
	}



	public int[][] getObstaculos() {
		return obstacles;
	}



	public int getFacesX() {
		return facesX;
	}



	public int getFacesY() {
		return facesY;
	}
	
	
}

	

	





