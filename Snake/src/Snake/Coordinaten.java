package Snake;

public class Coordinaten {
	private int xCoordinaat;
	private int yCoordinaat;
	
	public Coordinaten(){
		this(0,0);
	}
	
	public Coordinaten(int xCoordinaat, int yCoordinaat){
		this.xCoordinaat = xCoordinaat;
		this.yCoordinaat = yCoordinaat;
	}
	
	public void setCoordinaten(int xCoordinaten, int yCoordinaten){
		this.xCoordinaat = xCoordinaten;
		this.yCoordinaat = yCoordinaten;
	}
	
	public void setX(int xCoordinaat){
		this.xCoordinaat = xCoordinaat;
	}
	
	public void setY(int yCoordinaat){
		this.yCoordinaat = yCoordinaat;
	}
	
	public int getX(){
		return this.xCoordinaat;
	}
	
	public int getY(){
		return this.yCoordinaat;
	}
	
}
