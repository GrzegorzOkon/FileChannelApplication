package zad1;

import java.io.IOException;
//import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
//import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
//import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
//import java.util.Scanner;

public class Futil {
	//static String fileText = "";
	
	public static void processDir(String dirName, String resultFileName) {		
		try {
			Files.walkFileTree(Paths.get(dirName), new SimpleFileVisitor() { 
				@Override
				public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {									

					/*Scanner scan = new Scanner((Path)file, "Cp1250");
					
					while(scan.hasNextLine()) {
						fileText += scan.nextLine() + "\r\n";  //ustawia znak nowej linii przy zapisie do pliku
					}
					
					scan.close();*/
					
					return FileVisitResult.CONTINUE;
				}
			});
			
			//Files.write(Paths.get(resultFileName), fileText.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
		} catch(IOException ex) {}
	}
}
