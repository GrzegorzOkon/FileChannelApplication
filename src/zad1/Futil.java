package zad1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;

public class Futil {
	static Charset cs1 = Charset.forName("cp1250");
	static Charset cs2 = Charset.forName("UTF-8");
	static CharSequence wynik = null;
	
	public static void processDir(String dirName, String resultFileName) {		
		try {
			Files.deleteIfExists(Paths.get(resultFileName));   //kasuje plik jeœli istnieje

			try ( FileChannel dest = FileChannel.open(Paths.get(resultFileName), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {  //otwiera kana³ i tworzy plik jeœli go brakuje
				Files.walkFileTree(Paths.get(dirName), new SimpleFileVisitor() { 
				
					@Override
					public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {	
					
						//odczyt i zapis
						try (FileChannel fc = FileChannel.open((Path)file)) {   //tworzy kana³ do odczytu pliku
							wynik = cs1.decode(read(fc, newBuffer(fc)));
							dest.write(cs2.encode(CharBuffer.wrap(wynik + "\r\n")));  //"\r\n" - ustawia znak nowej linii przy zapisie do pliku
						} 
					
						return FileVisitResult.CONTINUE;
					}
				});
			} 
		} catch(IOException ex) {
			ex.printStackTrace();
			
			return;
		} 
	}
	
    private static ByteBuffer newBuffer(FileChannel file) throws IOException { 
    	return ByteBuffer.allocateDirect((int)file.size()); 
    }
    
    private static ByteBuffer read(FileChannel file, ByteBuffer bb) throws IOException { 
    	file.read(bb); 
    	bb.flip(); 
    	
    	return bb; 
    }
}
