package com.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class SaveImage {
	
	public static void salvaImmagine(String savePath, InputStream fileContent, String FileName)
	{
		File cartella=new File("C:\\carmina esame");
		if(!cartella.exists())
		{
			if(cartella.mkdirs())
				System.out.println("Creata la cartella");
			else
				System.out.println("Errore creazione cartella");
		}
		else
		{
			System.out.println("Cartella già creata");
		}
		/*File directory = new File(savePath);
		if(!directory.exists())
		{
			boolean success =directory.mkdirs();
			if(success)
			{
				System.out.println("cartella creata");
				Path destinazione = Path.of(savePath, FileName);//creato percorso completo del file di destianzione
				
				try {
					Files.copy(fileContent, destinazione, StandardCopyOption.REPLACE_EXISTING);
					System.out.println(destinazione.toString());
					System.out.println("Immagine salvata");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Errore durante il salvataggio");
				}
			}
			else
				System.out.println("impossibile crearla");
		}*/
		
	}
		
	}
