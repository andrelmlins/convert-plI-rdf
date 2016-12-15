import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import Predicados.Chunk;
import Predicados.Sentence;
import Predicados.Token;


public class Main {
	
	private static Compilador c = new Compilador();
	private static Conversor cc = new Conversor();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		converter();
	}
	
	public static void compilar(){
		try {
			c.run();
			archive();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void converter(){
		try {
			Scanner s = new Scanner(new File("tokens.plx"));
			while(s.hasNext()){
				String[] a = s.nextLine().split(" -- ");
				c.addToken(new Token(
						a[0].equals("null") ? null : a[0], a[1].equals("null") ? null : a[1],
						a[2].equals("null") ? null : a[2], a[3].equals("null") ? null : a[3],
						a[4].equals("null") ? null : a[4], a[5].equals("null") ? null : a[5],
						a[6].equals("null") ? null : a[6], a[7].equals("null") ? null : a[7],
						a[8].equals("null") ? null : a[8], Integer.parseInt(a[9]),
						a[10].equals("null") ? null : a[10], a[11].equals("null") ? null : a[11],
						a[12].equals("null") ? null : a[12], a[13].equals("null") ? null : a[13],
						a[14].equals("null") ? null : a[14], a[15].equals("null") ? null : a[15],
						a[16].equals("null") ? null : a[16], a[17].equals("true") ? true : false,
						a[0].equals("true") ? true : false, a[0].equals("true") ? true : false
				));
			}
			s.close();
			
			s = new Scanner(new File("chunks.plx"));
			while(s.hasNext()){
				String[] a = s.nextLine().split(" -- ");
				String[] chunks = a[1].replace("[", "").replace("]", "").replace(" ", "").split("[,]");
				String[] tokens = a[2].replace("[", "").replace("]", "").replace(" ", "").split("[,]");
				String[] heads = a[3].replace("[", "").replace("]", "").replace(" ", "").split("[,]");
				Chunk ch = new Chunk(a[0]);
				ch.setType(a[4]);
				
				for(int i=0;i<chunks.length;i++) ch.addChunk(chunks[i]);
				for(int i=0;i<tokens.length;i++) ch.addToken(tokens[i]);
				for(int i=0;i<heads.length;i++) ch.addHead(heads[i]);
				
				c.addChunk(ch);
			}
			s.close();
			
			s = new Scanner(new File("sentences.plx"));
			while(s.hasNext()){
				String[] a = s.nextLine().split(" -- ");
				String[] chunks = a[1].replace("[", "").replace("]", "").replace(" ", "").split("[,]");
				Sentence se = new Sentence(a[0]);
				se.setVoice(a[1].equals("null") ? null : a[1]);
				se.setText(a[2].equals("null") ? null : a[2]);
				
				for(int i=0;i<chunks.length;i++) se.addChunk(chunks[i]);
				c.addSentence(se);
			}
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void archive() throws IOException{
		PrintStream pw = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("tokens.plx"), true)));
		for(Token t : c.getTokens()){
			pw.println(t.string());
		}
		pw.close();
		
		pw = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("sentences.plx"), true)));
		for(Sentence se : c.getSentences()){
			pw.println(se.string());
		}
		pw.close();
		
		pw = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("chunks.plx"), true)));
		for(Chunk ch : c.getChunks()){
			pw.println(ch.string());
		}
		pw.close();
	}
}
