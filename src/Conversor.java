import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import Predicados.Chunk;
import Predicados.Token;


public class Conversor {
	
	private ArrayList<Chunk> chunks;
	private ArrayList<Token> tokens;
	
	public void run(Compilador c) throws IOException {
		this.chunks = c.getChunks();
		this.tokens = c.getTokens();
		createchunk();
		createToken();
	}
	
	private void createchunk() throws IOException {
		PrintStream pw = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("chunk.rdf"), true)));
		pw.println(Const.RDF);
		for(Chunk ch : this.chunks){
			pw.println(Const.DESCRIPTION+"chunk/"+ch.getId()+Const.SFINAL);
			pw.println(Const.TYPE+"chunk"+Const.FINAL);
			pw.println(Const.OBJETO+"type"+Const.MAIORQUE+ch.getType()+Const.FOBJETO+"type"+Const.MAIORQUE);
			for(String s : ch.getChunks()){
				pw.println(Const.OBJETO+"chunk"+Const.RESOURCE+"chunk/"+s+Const.FINAL);
			}
			for(String s : ch.getTokens()){
				pw.println(Const.OBJETO+"token"+Const.RESOURCE+"token/"+s+Const.FINAL);
			}
			for(String s : ch.getHeads()){
				pw.println(Const.OBJETO+"head"+Const.RESOURCE+"token/"+s+Const.FINAL);
			}
			pw.println(Const.FDESCRIPTION);
		}
		pw.println(Const.FRDF);
		pw.close();
	}
	
	private void createToken() throws IOException {
		PrintStream pw = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("token.rdf"), true)));
		pw.println(Const.RDF);
		for(Token t : this.tokens){
			pw.println(Const.DESCRIPTION+"token/"+t.getId()+Const.SFINAL);
			
			pw.println(Const.TYPE+"token"+Const.FINAL);
			if(t.getNext()!=null) pw.println(Const.OBJETO+"next"+Const.RESOURCE+"token/"+t.getNext()+Const.FINAL);
			
			if(t.getPos()!=null) pw.println(Const.OBJETO+"pos"+Const.MAIORQUE+t.getPos()+Const.FOBJETO+"pos"+Const.MAIORQUE);
			if(t.getChunkOT()!=null) pw.println(Const.OBJETO+"chunktypeot"+Const.MAIORQUE+t.getChunkOT()+Const.FOBJETO+"chunktypeot"+Const.MAIORQUE);
			if(t.getChunk()!=null) pw.println(Const.OBJETO+"chunktype"+Const.MAIORQUE+t.getChunk()+Const.FOBJETO+"chunktype"+Const.MAIORQUE);
			if(t.getType()!=null) pw.println(Const.OBJETO+"type"+Const.MAIORQUE+t.getType()+Const.FOBJETO+"type"+Const.MAIORQUE);
			if(t.getGpos()!=null) pw.println(Const.OBJETO+"gpos"+Const.MAIORQUE+t.getGpos()+Const.FOBJETO+"gpos"+Const.MAIORQUE);
			if(t.getNer()!=null) pw.println(Const.OBJETO+"ner"+Const.MAIORQUE+t.getNer()+Const.FOBJETO+"ner"+Const.MAIORQUE);
			if(t.getOrth()!=null) pw.println(Const.OBJETO+"orth"+Const.MAIORQUE+t.getOrth()+Const.FOBJETO+"orth"+Const.MAIORQUE);
			if(t.getLength()!=0) pw.println(Const.OBJETO+"length"+Const.MAIORQUE+t.getLength()+Const.FOBJETO+"length"+Const.MAIORQUE);
			if(t.getStem()!=null) pw.println(Const.OBJETO+"stem"+Const.MAIORQUE+t.getStem()+Const.FOBJETO+"stem"+Const.MAIORQUE);
			if(t.getBigPosAft()!=null) pw.println(Const.OBJETO+"bigposaft"+Const.MAIORQUE+t.getBigPosAft()+Const.FOBJETO+"bigposaft"+Const.MAIORQUE);
			if(t.getBigPosBef()!=null) pw.println(Const.OBJETO+"bigposbef"+Const.MAIORQUE+t.getBigPosBef()+Const.FOBJETO+"bigposbef"+Const.MAIORQUE);
			if(t.getTrigPosAft()!=null) pw.println(Const.OBJETO+"trigposaft"+Const.MAIORQUE+t.getTrigPosAft()+Const.FOBJETO+"trigposaft"+Const.MAIORQUE);
			if(t.getTrigPosBef()!=null) pw.println(Const.OBJETO+"trigposbef"+Const.MAIORQUE+t.getTrigPosBef()+Const.FOBJETO+"trigposbef"+Const.MAIORQUE);
			if(t.getTrigPosBef()!=null) pw.println(Const.OBJETO+"trigposbef"+Const.MAIORQUE+t.getTrigPosBef()+Const.FOBJETO+"trigposbef"+Const.MAIORQUE);
			if(t.getMtype()!=null) pw.println(Const.OBJETO+"mtype"+Const.MAIORQUE+t.getMtype()+Const.FOBJETO+"mtype"+Const.MAIORQUE);
			if(t.getSubtype()!=null) pw.println(Const.OBJETO+"subtype"+Const.MAIORQUE+t.getSubtype()+Const.FOBJETO+"subtype"+Const.MAIORQUE);
			
			pw.println(Const.OBJETO+"headpp"+Const.MAIORQUE+t.isHeadPP()+Const.FOBJETO+"headpp"+Const.MAIORQUE);
			pw.println(Const.OBJETO+"headnp"+Const.MAIORQUE+t.isHeadNP()+Const.FOBJETO+"headnp"+Const.MAIORQUE);
			pw.println(Const.OBJETO+"headvp"+Const.MAIORQUE+t.isHeadVP()+Const.FOBJETO+"headvp"+Const.MAIORQUE);
			
			pw.println(Const.FDESCRIPTION);
		}
		pw.println(Const.FRDF);
		pw.close();
	}
}
