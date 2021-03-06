package Editor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestEditor {

	private Texted editor;
	
	@Before
	public void setUp() throws Exception {
		editor = new Texted();
	}

	@Test
	public void testAutocomplete() {
		editor.setText("<");
		assertEquals(editor.getText(),"<"+">");
	}

	@Test
	public void testTagControle(){
		editor.setText("<html5 tag <html5tag cloosed>");
		editor.tagControle();
		assertTrue(editor.getText().contains("< Tag Not Colsed html5 tag <html5tag cloosed>"));
	}
	
	@Test
	public void testUndo(){
		editor.unDoEvent();
		assertEquals(editor.getText(), "<html5 tag <html5tag cloosed>");	
	}
	
	@Test
	public void testRedo(){
		editor.reDoEvent();
		assertEquals(editor.getText(), "< Tag Not Colsed html5 tag <html5tag cloosed>");
	}
}
