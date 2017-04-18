package Snake;

import java.awt.event.KeyEvent;

public class Game {
	
	private int Direction;
	
	public void keyTyped(KeyEvent event){
		if(event.getKeyCode() == KeyEvent.VK_UP)Direction=1;
		else{if(event.getKeyCode() == KeyEvent.VK_RIGHT)Direction=2;
		else{if(event.getKeyCode() == KeyEvent.VK_DOWN)Direction=3;
		else{if(event.getKeyCode() == KeyEvent.VK_LEFT)Direction=4;}}}
	}
	
	/*
	 * Opdracht1 schildpad 1e semester
	 */
	/*private class GameWindow{
		//TODO slang vervangen
		private JFrame frame;
		private TekenPaneel paneel;
		private Graphics gB;
		private Image buffer;
		
		private HashSet<LinkedList<int[]>> database;
		private HashMap<String,Color> kleurMap;
		
		public GameWindow(String titel){
			frame = new JFrame(titel);
			paneel = new TekenPaneel();
			frame.setContentPane(paneel);
			paneel.setPreferredSize(new Dimension(frameGR[0]*10, frameGR[1]*10));
			frame.pack();
			database = new HashSet<LinkedList<int[]>>();
			kleurMap = new HashMap<String,Color>();
			kleurMap.put("rood", Color.red);
			maakZichtbaar(true);
		}
		
		public void maakZichtbaar(boolean zichtbaar){
			if(gB == null){
				Dimension dim = paneel.getSize();
				buffer = frame.createImage(dim.width,dim.height);
				gB = buffer.getGraphics();
			}
			frame.setVisible(zichtbaar);
		}
		
		public void teken(LinkedList<int[]> s){
			s = get();
			database.add(s);
			herschilder();
		}
		
		public void verwijder(LinkedList<int[]> s){
			s = get();
			database.remove(s);
			herschilder();
		}
		
		private void maakBufferSchoon(){
			gB.setColor(Color.red);
			Dimension dim = paneel.getSize();
			gB.fillRect(0,0,dim.width,dim.height);
		}
		
		private void tekenSlang(LinkedList<int[]> s){
			s = get();
			String kleur = s.getKleur();
			Color color = kleurMap.get(kleur);
			if(color != null) gB.setColor(color);
			else gB.setColor(Color.black);
			
			//kop
			tekenCirkel(s.get(0)[0],s.get(0)[1]);
			//lijf
			if(s.size()>2){
				for(int grootte=1;grootte<(s.size()-2);grootte++){
					tekenVierKant(s.get(grootte)[0],s.get(grootte)[1]);
				}
			}
			//staart
			tekenTriangle(s.get(.size()-1)[0],s.get(s.size()-1)[1])
			
			//food
			//TODO
			tekenVierKant();
			
			//restItem
			//TODO
		}
		
		private void tekenCirkel(double xMpt, double yMpt){
			gB.fillOval(Math.round(xMpt -5), Math.round(yMpt-5), 10, 10);
		}
		
		private void tekenVierKant(double xMpt, double yMpt){
			gB.fillRect(Math.round(xMpt-5), Math.round(yMpt-5), 10, 10);
		}
		
		private void tekenTraingle(double xMpt, double yMpt){
			gB.fillRect(Math.round(xMpt-5), Math.round(yMpt-5), 6, 6);
		}
		
		private class TekenPaneel extends JPanel{
			public void paint(Graphics g){
				g.drawImage(buffer,0,0,null);
			}
		}
	}
	*/
	
	/*
	 * Main method
	 */
	public static void main(String[] args){
	}
}
