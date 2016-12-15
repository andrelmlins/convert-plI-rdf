import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import Predicados.Chunk;
import Predicados.Sentence;
import Predicados.Token;


public class Conversor {
	
	private ArrayList<Chunk> chunks;
	private ArrayList<Sentence> sentences;
	private ArrayList<Token> tokens;
	
	public void run(Compilador c) throws IOException {
		this.chunks = c.getChunks();
		this.sentences = c.getSentences();
		this.tokens = c.getTokens();
		createchunk();
		createSentence();
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
	
	private void createSentence() throws IOException {
		PrintStream pw = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("sentence.rdf"), true)));
		pw.println(Const.RDF);
		for(Sentence s : this.sentences){
			pw.println(Const.DESCRIPTION+"sentence/"+s.getId()+Const.SFINAL);
			pw.println(Const.TYPE+"sentence"+Const.FINAL);
			pw.println(Const.OBJETO+"voice"+Const.MAIORQUE+s.getVoice()+Const.FOBJETO+"voice"+Const.MAIORQUE);
			pw.println(Const.OBJETO+"text"+Const.MAIORQUE+s.getText()+Const.FOBJETO+"text"+Const.MAIORQUE);
			for(String st : s.getChunks()){
				pw.println(Const.OBJETO+"chunk"+Const.RESOURCE+"chunk/"+st+Const.FINAL);
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
			if(t.getType()!=null) pw.println(Const.OBJETO+"type"+Const.MAIORQUE+t.getType()+Const.FOBJETO+"type"+Const.MAIORQUE);
			if(t.getPos()!=null) pw.println(Const.OBJETO+"postag"+Const.RESOURCE+"token/pos/"+t.getPos()+Const.FINAL);
			if(t.getChunk()!=null) pw.println(Const.OBJETO+"chunktag"+Const.RESOURCE+"chunk/tag/"+t.getChunk()+Const.FINAL);
			if(t.getChunkOT()!=null) pw.println(Const.OBJETO+"chunktagOT"+Const.RESOURCE+"chunk/tagot/"+t.getChunkOT()+Const.FINAL);
			if(t.getOrth()!=null) pw.println(Const.OBJETO+"orth"+Const.RESOURCE+"token/orth/"+t.getOrth()+Const.FINAL);
			if(t.getNer()!=null) pw.println(Const.OBJETO+"ner"+Const.RESOURCE+"token/ner/"+t.getNer()+Const.FINAL);
			if(t.getGpos()!=null) pw.println(Const.OBJETO+"gpostag"+Const.RESOURCE+"token/gpos/"+t.getGpos()+Const.FINAL);
			if(t.getNext()!=null) pw.println(Const.OBJETO+"next"+Const.RESOURCE+"token/"+t.getNext()+Const.FINAL);
			if(t.getLength()!=0) pw.println(Const.OBJETO+"length"+Const.RESOURCE+"token/length/"+t.getLength()+Const.FINAL);
			
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
