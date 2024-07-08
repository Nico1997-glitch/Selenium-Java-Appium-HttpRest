package it.smartpaper.AutoX;

import autoitx4java.AutoItX;

import java.io.File;

import com.jacob.com.LibraryLoader;

public class AutoItExample {

	public static void main(String[] args) {
		// Carica la libreria di AutoItX
		String jacobDllVersionToUse = "jacob-1.21-x64.dll";
		File file = new File("C\\Jacobs\\" + jacobDllVersionToUse);
		System.setProperty(LibraryLoader.JACOB_DLL_PATH, file.getAbsolutePath());

		// Crea un'istanza di AutoItX
		AutoItX autoItX = new AutoItX();

		// Apri il Blocco Note
		autoItX.run("notepad.exe", "", AutoItX.SW_MAXIMIZE);
		autoItX.winWaitActive("[CLASS:Notepad]");

		// Inserisci del testo
		autoItX.send("Questo è un test di AutoItX da Java.");

		// Aspetta un secondo
		autoItX.sleep(1000);

		// Salva il file
		autoItX.send("^s"); // Ctrl + S
		autoItX.winWaitActive("Save As");
		autoItX.ControlSetText("Save As", "", "Edit1", "test.txt");
		autoItX.controlClick("Save As", "", "Button1");

		// Aspetta che il file sia salvato
		autoItX.winWaitClose("[CLASS:Notepad]");

		// Chiudi il Blocco Note
		autoItX.winClose("[CLASS:Notepad]");
	}
}
