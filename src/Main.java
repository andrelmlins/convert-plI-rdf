import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import Predicados.Chunk;
import Predicados.Token;


public class Main {
	
	private static Compilador c = new Compilador();
	private static Conversor cc = new Conversor();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		compilar();
		converter();
	}
	
	public static void compilar(){
		try {
			c.run();
			//archive();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void converter(){
		try {
			cc.run(c);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void archive() throws IOException{
		PrintStream pw = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("tokens05.rdf"), true)));
		for(Token t : c.getTokens()){
			pw.println(t.string());
		}
		pw.close();
		
		pw = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("chunks05.rdf"), true)));
		for(Chunk ch : c.getChunks()){
			pw.println(ch.string());
		}
		pw.close();
	}
}
