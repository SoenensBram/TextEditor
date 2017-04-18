/*
 * implementatie:
 * knop: new game en scoreboard
 * als gameover return score to score board
 * 
 * Geen linked list (pre def)
 * wel private class
 * linkedlist van in de les (coppy paste)
 * 
 * met size de tail grounden
 * size+1 als eten
 * 
 * eerst eten colision voor lichaam collision (staartpunt)
 * 
 *  richting beveiligen
 *  
 *  new game => while lus tot game over
 *  
 *  Move de input updaten
 *  
 *  Kimpende methode aantoepen op trage timer
 *  Food methode aanroepene op gemiddelde timers
 *  Move aantoepen op korte timer
 *  Input onafhangkelijk van timer;
 */
package Snake;
//TODO TIMERS
/**
 * java classes:
 * swing voor GUI
 * awt voor figuren
 * util voor random nummers
 */

import java.lang.Math;

public class Engine{
	//int variabelen
	private int score;private int DirectionChange;private int food;private int slangLengte;private int foodSaturation;
	private int restSpawn;private int timeRest;
	//boolean variabelen
	private boolean grow; private boolean gameOver;
	//Linked List variabelen
	private LinkedList<Coordinaten> slang;
	private LinkedList<Coordinaten> eten;
	private LinkedList<Coordinaten> restLengte;
	//Coordinaat variabelen
	private Coordinaten nextCoordinaatHead;private Coordinaten frameGR;
	
	/*
	 * this method defines all the starting variabels
	 */
	public Engine(){
		frameGR = new Coordinaten(100,100);
		slang.add(new Coordinaten(50,50));
		slang.add(new Coordinaten(50,49));
		slangLengte = 2;
		DirectionChange = 3;
		grow = false;
		foodSaturation = 800;
		restSpawn = 30;
	}
	

	/*
	 * This method calls Colision
     * This is the method that determans what to do if a move is made.
	 * Direction:
	 * 1 = up
	 * 2 = right
	 * 3 = down
	 * 4 = left
	 */
	public void MoveAction(int DirectionChanges){
		int CoordHeadX = slang.first().getX();
		int CoordHeadY = slang.first().getY();
		if(DirectionChanges == 1){
			CoordHeadY +=1;
			nextCoordinaatHead = new Coordinaten(CoordHeadX,CoordHeadY);
			this.Colison();
		}
		if(DirectionChanges == 2){
			CoordHeadX +=1;
			nextCoordinaatHead = new Coordinaten(CoordHeadX,CoordHeadY);
			this.Colison();
		}
		if(DirectionChanges == 3){
			CoordHeadY -=1;
			nextCoordinaatHead = new Coordinaten(CoordHeadX,CoordHeadY);
			this.Colison();
		}
		if(DirectionChanges == 4){
			CoordHeadX-=1;
			nextCoordinaatHead = new Coordinaten(CoordHeadX,CoordHeadY);
			this.Colison();
		}
		this.Colison();
		score = score +1;
	}
	
	/*
	 * Collision method:
	 * determans what hapens
	 */
	private void Colison(){
		//@random variable
		//Checking if we are going back to the prevouis tile
		if(slang.get(0) == nextCoordinaatHead){
			DirectionChange = DirectionChange +1;
			if(DirectionChange > 4)DirectionChange=1;
			this.MoveAction(DirectionChange);
		}
		//Checking if we collide with the wall
		if(nextCoordinaatHead.getX() == 0){
			this.gameOver();
		}
		if(nextCoordinaatHead.getX() == 100){
			this.gameOver();
		}
		if(nextCoordinaatHead.getY() == 0){
			this.gameOver();
		}
		if(nextCoordinaatHead.getY() == 100){
			this.gameOver();
		}
		//Checking if we can eat food or have rest
		int ListCycle = 0;
		grow = false;
		if(restLengte.get(0) != slang.get(0)){
			while(ListCycle <= eten.size()){
				if(nextCoordinaatHead == eten.get(ListCycle)){
					grow = true;
				}
			}
		}else{
			slang.keep2Leads();
			score += 10000;
		}
		if(grow = false){
			slang.removeLast();
		}else{
			slangLengte =+1;
			score =+ 1;
		}
		//Checking if we colide with our own boddy
		ListCycle = 2;
		while(ListCycle<= slang.size()){
			if(slang.first() == slang.get(ListCycle)){
				this.gameOver();
			}
			ListCycle = ListCycle+1;
		}
		slang.prepend(nextCoordinaatHead);
		score++;
	}
	
	/*
	 * This Code is writen to generate food randomly (with a timer)
	 */
	//TODO in timer
	public void foodGeneration(){
		food = (((frameGR.getX()*frameGR.getY())-slangLengte)/foodSaturation)+1;
		int LoopCounter = 0;
		int Xas;int Yas;
		while(LoopCounter<=food){
			int SlangCounter = 0;
			int uniek = SlangCounter;
			Xas = (int) ((Math.random()*(frameGR.getX()-2))+1);
			Yas = (int) ((Math.random()*(frameGR.getY()-2))+1);
			Coordinaten tmpCoord = new Coordinaten(Xas,Yas);
				while(slang.size() == SlangCounter){
					if(tmpCoord != slang.get(SlangCounter)){uniek =+1;}
					SlangCounter =+ 1;
				}
			if(uniek == slang.size()){
				eten.set(LoopCounter, tmpCoord);
				LoopCounter =+1;
			}
		}
	}
	
	/*
	 * This will generate a food reste item to rest the size of the snake
	 * the time is dependant on the size of the snake (spawn)
	 */
	//TODO timer
	public void restSizeItem(){
		if(restSpawn-slangLengte<=0){
			timeRest = slangLengte-restSpawn;
		}else{timeRest =0;}
	}
	
	private int gameOver(){
		new java.util.Timer().cancel();
		return score;
	}
	
	
	public LinkedList<Coordinaten> get(){
		return slang;
	}
	
	public LinkedList<Coordinaten> getFood(){
		return eten;
	}
	
	public LinkedList<Coordinaten> getRest(){
		return restLengte;
	}
	
	//TODO implement
	public void setRest(Coordinaten c){
		eten.add(c);
	}
	
	//TODO implement
	public void setFood(Coordinaten c){
		restLengte.add(c);
	}
	
}
