package it.smartpaper.AutoXjava;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testng.Assert.assertTrue;

import java.io.File;

import org.opentest4j.FileInfo;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jacob.com.LibraryLoader;

import autoitx4java.AutoItX;

public class AutoItX4JavaTest {
	
	 private AutoItX autoItX;

	    @BeforeTest
	    public void setUp() throws Exception {
	        // Carica la libreria di AutoItX
	        String jacobPath = System.getProperty("java.library.path") + ";path_to_autoitx4java_dll";
	        System.setProperty(LibraryLoader.JACOB_DLL_PATH, jacobPath);

	        // Crea un'istanza di AutoItX
	        autoItX = new AutoItX();
	    }

	    @Test
	    public void testOpenNotepadAndSaveFile() {
	        // Apri il Blocco Note
	        autoItX.run("notepad.exe", "", AutoItX.SW_MAXIMIZE);
	        autoItX.winWaitActive("Untitled - Notepad");

	        // Inserisci del testo
	        autoItX.send("Questo è un test di AutoItX da Java.");

	        // Aspetta un secondo
	        autoItX.sleep(1000);

	        // Salva il file
	        autoItX.send("^s"); // Ctrl + S
	        autoItX.winWait("Salva con nome");
	        autoItX.ControlSetText("Salva con nome", "", "Edit1", "test.txt");
	        autoItX.controlClick("Salva con nome", "", "Button2");

	        // Aspetta che il file sia salvato
	        autoItX.winWaitClose("Untitled - Notepad");

	        // Chiudi il Blocco Note
	        autoItX.winClose("Senza titolo - Blocco note");

	        // Verifica se il file è stato salvato correttamente
	        Assert.assertTrue(new File("test.txt").exists(), "Il file test.txt non esiste");
	    }
	    
	    @AfterTest
	    public void tearDown() throws Exception {
	        // Chiudi l'istanza di AutoItX
	        autoItX = null;
	    }
}
