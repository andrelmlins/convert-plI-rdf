import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import Predicados.Chunk;
import Predicados.Dependency;
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
			if(ch.getType()!=null) pw.println(Const.OBJETO+"hasType"+Const.MAIORQUE+ch.getType()+Const.FOBJETO+"hasType"+Const.MAIORQUE);
			if(ch.getPosRelPred()!=null) pw.println(Const.OBJETO+"hasDistToRoot"+Const.MAIORQUE+ch.getPosRelPred()+Const.FOBJETO+"hasDistToRoot"+Const.MAIORQUE);
			for(String s : ch.getChunks()){
				pw.println(Const.OBJETO+"hasSucc"+Const.RESOURCE+"chunk/"+s+Const.FINAL);
			}
			for(String s : ch.getTokens()){
				pw.println(Const.OBJETO+"hasToken"+Const.RESOURCE+"token/"+s+Const.FINAL);
			}
			for(String s : ch.getHeads()){
				pw.println(Const.OBJETO+"hasHead"+Const.RESOURCE+"token/"+s+Const.FINAL);
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
			if(t.getNext()!=null) pw.println(Const.OBJETO+"hasNext"+Const.RESOURCE+"token/"+t.getNext()+Const.FINAL);
			
			if(t.getPos()!=null) pw.println(Const.OBJETO+"hasPos"+Const.MAIORQUE+t.getPos()+Const.FOBJETO+"hasPos"+Const.MAIORQUE);
			if(t.getChunkOT()!=null) pw.println(Const.OBJETO+"hasCkTypeOT"+Const.MAIORQUE+t.getChunkOT()+Const.FOBJETO+"hasCkTypeOT"+Const.MAIORQUE);
			if(t.getChunk()!=null) pw.println(Const.OBJETO+"hasCkType"+Const.MAIORQUE+t.getChunk()+Const.FOBJETO+"hasCkType"+Const.MAIORQUE);
			if(t.getNeType()!=null) pw.println(Const.OBJETO+"hasNEType"+Const.MAIORQUE+t.getNeType()+Const.FOBJETO+"hasNEType"+Const.MAIORQUE);
			if(t.getType()!=null) pw.println(Const.OBJETO+"hasTkType"+Const.MAIORQUE+t.getType()+Const.FOBJETO+"hasTkType"+Const.MAIORQUE);
			if(t.getGpos()!=null) pw.println(Const.OBJETO+"hasGPos"+Const.MAIORQUE+t.getGpos()+Const.FOBJETO+"hasGPos"+Const.MAIORQUE);
			if(t.getNer()!=null) pw.println(Const.OBJETO+"hasNER"+Const.MAIORQUE+t.getNer()+Const.FOBJETO+"hasNER"+Const.MAIORQUE);
			if(t.getOrth()!=null) pw.println(Const.OBJETO+"hasOrth"+Const.MAIORQUE+t.getOrth()+Const.FOBJETO+"hasOrth"+Const.MAIORQUE);
			if(t.getLength()!=0) pw.println(Const.OBJETO+"hasLength"+Const.MAIORQUE+t.getLength()+Const.FOBJETO+"hasLength"+Const.MAIORQUE);
			if(t.getStem()!=null) pw.println(Const.OBJETO+"hasStem"+Const.MAIORQUE+t.getStem()+Const.FOBJETO+"hasStem"+Const.MAIORQUE);
			if(t.getBigPosAft()!=null) pw.println(Const.OBJETO+"hasBigPosAft"+Const.MAIORQUE+t.getBigPosAft()+Const.FOBJETO+"hasBigPosAft"+Const.MAIORQUE);
			if(t.getBigPosBef()!=null) pw.println(Const.OBJETO+"hasBigPosBef"+Const.MAIORQUE+t.getBigPosBef()+Const.FOBJETO+"hasBigPosBef"+Const.MAIORQUE);
			if(t.getTrigPosAft()!=null) pw.println(Const.OBJETO+"hasTrigPosAft"+Const.MAIORQUE+t.getTrigPosAft()+Const.FOBJETO+"hasTrigPosAft"+Const.MAIORQUE);
			if(t.getTrigPosBef()!=null) pw.println(Const.OBJETO+"hasTrigPosBef"+Const.MAIORQUE+t.getTrigPosBef()+Const.FOBJETO+"hasTrigPosBef"+Const.MAIORQUE);
			if(t.getMtype()!=null) pw.println(Const.OBJETO+"hasMType"+Const.MAIORQUE+t.getMtype()+Const.FOBJETO+"hasMType"+Const.MAIORQUE);
			if(t.getSubtype()!=null) pw.println(Const.OBJETO+"hasSubType"+Const.MAIORQUE+t.getSubtype()+Const.FOBJETO+"hasSubType"+Const.MAIORQUE);
			
			pw.println(Const.OBJETO+"isHeadPP"+Const.MAIORQUE+t.isHeadPP()+Const.FOBJETO+"isHeadPP"+Const.MAIORQUE);
			pw.println(Const.OBJETO+"isHeadNP"+Const.MAIORQUE+t.isHeadNP()+Const.FOBJETO+"isHeadNP"+Const.MAIORQUE);
			pw.println(Const.OBJETO+"isHeadVP"+Const.MAIORQUE+t.isHeadVP()+Const.FOBJETO+"isHeadVP"+Const.MAIORQUE);
			pw.println(Const.OBJETO+"isRoot"+Const.MAIORQUE+t.isHeadVP()+Const.FOBJETO+"isRoot"+Const.MAIORQUE);
			
			for(Dependency d : t.getDep()){
				String[] dep = d.getDep().split("");
				String has = "has";
				for(int i=0;i<dep.length;i++){
					if(i==0) has += dep[i].toUpperCase();
					else if(dep[i].equals("_")) {
						i++;
						has += dep[i].toUpperCase();
					}
					else has += dep[i];
				}
				pw.println(Const.OBJETO+has+Const.RESOURCE+"token/"+d.getToken()+Const.FINAL);
			}
			
			pw.println(Const.FDESCRIPTION);
		}
		pw.println(Const.FRDF);
		pw.close();
	}
}
