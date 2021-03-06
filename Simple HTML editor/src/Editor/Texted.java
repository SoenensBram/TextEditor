package Editor;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Simple GUI for a text editor.
 *
 */
public class Texted extends JFrame implements DocumentListener {
	private JTextArea textArea;
	private StackLL<textString> logUndo;
	private StackLL<textString> logRedo;
	private textString LogWord;
	private int previos;
	private boolean reDo;
	private textString change;
	
	private static final long serialVersionUID = 5514566716849599754L;
	/**
	 * Constructs a new GUI: A TextArea on a ScrollPane
	 */
	public Texted() {
		super();
		setTitle("Html Editor");
		setBounds(800, 800, 600, 600);
		textArea = new JTextArea(30, 80);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		//Registration of the callback
		textArea.getDocument().addDocumentListener(this);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		add(scrollPane);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Declaring variabels
		previos = 0;
		logUndo = new StackLL<textString>(10);
		logRedo = new StackLL<textString>(10);
		LogWord= new textString();
		change = new textString();
		reDo = false;
	}

	/**
	 * Callback when changing an element
	 */
	public void changedUpdate(DocumentEvent ev) {
		this.addToLog(textArea.getText().subSequence(ev.getOffset(), ev.getOffset()+ev.getLength()).toString(), ev.getOffset(), 1);
		
		change = new textString((textArea.getText().subSequence(ev.getOffset(), ev.getOffset()+ev.getLength()).toString()), ev.getOffset());
	}

	/**
	 * Callback when deleting an element
	 */
	public void removeUpdate(DocumentEvent ev) {
		//this.addToLog(textArea.getText().subSequence(ev.getOffset(), ev.getOffset()+ev.getLength()).toString(), ev.getOffset(), 2);
		//change = new textString(textArea.getText().subSequence(ev.getOffset(), ev.getOffset()+ev.getLength()).toString(), ev.getOffset());
	}

	/**
	 * Callback when inserting an element
	 */
	public void insertUpdate(DocumentEvent ev) {
		//Check if the change is only a single character, otherwise return so it does not go in an infinite loop
		if(ev.getLength() != 1) return;
		
		//this.addToLog(textArea.getText().subSequence(ev.getOffset(), ev.getOffset()+ev.getLength()).toString(), ev.getOffset(), 3);
		//change = new textString(textArea.getText().subSequence(ev.getOffset(), ev.getOffset()+ev.getLength()).toString(), ev.getOffset());
		
		// In the callback you cannot change UI elements, you need to start a new Runnable
		if(textArea.getText().subSequence(ev.getOffset(), ev.getOffset()+ev.getLength()).toString().contains("<")){
			SwingUtilities.invokeLater(new Task(">"));
		}
	}

	/**
	 * this adds a change to the log
	 * @param woord
	 * @param calling = the calling event
	 */
	private void addToLog(String woord,int position, int calling){
		if(reDo){
			logRedo.push(new textString(woord, position));
			reDo = false;
		}else{
			if(LogWord.length()>0){
				if(previos == 0){
					LogWord.rester();
				}else{
					if(calling != previos){
						logUndo.push(LogWord);
						LogWord.rester();
					}
				}
				if(LogWord.length() >= 10){
					logUndo.push(LogWord);
					LogWord.rester();
				}else{
					LogWord.concat(woord);
				}
				previos = calling;
			}
			logRedo = new StackLL<textString>(10);
		}
	}
	
	public void reDoEvent(){
		textString temp = logRedo.pop();
		//write back to doc
		
		textArea.replaceRange(temp.getInhoud(), temp.getPos(), temp.getPos()+temp.length());
		
		logUndo.push(change);
		reDo = true;
	}
	
	public void unDoEvent(){
		textString temp = logUndo.pop();
		//write back to doc
		
		textArea.replaceRange(temp.getInhoud(), temp.getPos(), temp.getPos()+temp.length());
		
		logRedo.push(change);
	}
	
	/**
	 * This Methode Checks if all the tags are closed.
	 */
	public void tagControle(){
		String tags = textArea.getText();
		int OpeningTagLocation = 0;
		String result = "";
		if(tags.contains("<")){
			OpeningTagLocation = tags.indexOf("<", OpeningTagLocation);
			if(tags.contains(">")){
				int i = 0;
				while(i != tags.length()){
					i = tags.indexOf("<", i);
					if(tags.indexOf("<", i)<tags.indexOf(">", i)){
						String[] tekst = tags.split("<", 2);
						result.concat(tekst[0]);
						result.concat("< Tag Not Closed ");
						result.concat(tekst[1]);
					}
					if(i==-1){
						i = tags.length();
					}
					textArea.setText(result);
				}
			}else{
				String[] tekst = tags.split("<");
				for(int i = 0 ; i < tekst.length ; i++){
					result.concat(tekst[i]);
					result.concat("< Tag Not Closed ");
				}
				textArea.setText(result);
			}
		}
	}
	
	public void setText(String tekst){
		textArea.setText(tekst);
	}
	
	public String getText(){
		return textArea.getText();
	}
	
	private class textString{
		private String inhoud;
		private int pos;
		
		public textString(){
			this("",0);
		}
		
		public textString(String inhoud, int pos){
			this.inhoud = inhoud;
			this.pos = pos;
		}
		
		public void setInhoud(String inhoud){
			this.inhoud = inhoud;
		}
		
		public void setPos(int pos){
			this.pos = pos;
		}
		
		public String getInhoud(){
			return inhoud;
		}
		
		public int getPos(){
			return pos;
		}
		
		public int length(){
			return this.inhoud.length();
		}
		
		public void rester(){
			this.inhoud = "";
			this.pos = 0;
		}
		
		public void concat(String catje){
			inhoud.concat(catje);
		}
	}
	
	/**
	 * Runnable: change UI elements as a result of a callback
	 * Start a new Task by invoking it through SwingUtilities.invokeLater
	 */
	private class Task implements Runnable {
		private String text;
		
		/**
		 * Pass parameters in the Runnable constructor to pass data from the callback 
		 * @param text which will be appended with every character
		 */
		Task(String text) {
			this.text = text;
		}

		/**
		 * The entry point of the runnable
		 */
		public void run() {
			textArea.append(text);
		}
	}

	/**
	 * Entry point of the application: starts a GUI
	 */
	public static void main(String[] args) {
		new Texted();

	}

}
