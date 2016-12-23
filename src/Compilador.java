import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import Comparator.ChunkComparator;
import Comparator.TokenComparator;
import Predicados.Chunk;
import Predicados.Dependency;
import Predicados.Token;


public class Compilador {
	private ArrayList<Chunk> chunks = new ArrayList<>();
	private ArrayList<Token> tokens = new ArrayList<>();
	
	private List<String> facts = new ArrayList<>();
	private List<String> factsNoReads = new ArrayList<>();
	
	public void run() throws IOException{
		Scanner s = new Scanner(new File("CorpusACE2004_FULL.pl"));
		
		while(s.hasNext()){
			String line = s.nextLine().replaceAll(" ","");
			facts.add(line);
		}
		s.close();
		int i=0;
		while(this.facts.size()!=0){
			for(String fact : this.facts){
				if(!fact.equals("") && fact.charAt(0)!='%'){
					i++;
					if(i%10000==0) System.out.println(i);
					String[] split = fact.split("[(]");
					
					String name = split[0];
					String[] parametros = split[1].replace(")","").replace(".","").split("[,]");
					if(!fato(name,parametros)){
						this.factsNoReads.add(fact);
					}
				}
			}
			this.facts.clear();
			this.facts.addAll(this.factsNoReads);
			this.factsNoReads.clear();
			System.out.println(this.facts.size());
		}
		s.close();
		System.out.println("next");
		s = new Scanner(new File("next_ALL_ACE2004.pl"));
		while(s.hasNext()){
			String fact = s.nextLine().replaceAll(" ","");
			
			if(!fact.equals("") && fact.charAt(0)!='%'){
				String[] split = fact.split("[(]");
				
				String name = split[0];
				String[] parametros = split[1].replace(")","").replace(".","").split("[,]");
				fato(name,parametros);
			}
		}
		System.out.println("Dependency");
		s = new Scanner(new File("dependency_ACE_2004.pl"));
		while(s.hasNext()){
			String fact = s.nextLine().replaceAll(" ","");
			
			if(!fact.equals("") && fact.charAt(0)!='%'){
				String[] split = fact.split("[(]");
				
				String name = split[0];
				String[] parametros = split[1].replace(")","").replace(".","").split("[,]");
				fato(name,parametros);
			}
		}
		s.close();
		
		Collections.sort(this.chunks,new ChunkComparator());
		Collections.sort(this.tokens,new TokenComparator());
	}
	
	public ArrayList<Chunk> getChunks() {
		return chunks;
	}

	public ArrayList<Token> getTokens() {
		return tokens;
	}
	
	public void addChunk(Chunk chunk) {
		this.chunks.add(chunk);
	}

	public void addToken(Token token) {
		this.tokens.add(token);
	}

	private boolean classe(String name, String id){
		if(name.equals("chunk")){
			chunks.add(new Chunk(id));
		} else if(name.equals("token")){
			tokens.add(new Token(id));
		}
		return true;
	}

	private boolean hasSucc(String id, String chunk1){
		if(this.chunks.contains(new Chunk(id))){
			Chunk chunk = this.chunks.get(this.chunks.indexOf(new Chunk(id)));
			chunk.addChunk(chunk1);
			this.changeChunk(chunk);
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean hasToken(String id, String token){
		if(this.chunks.contains(new Chunk(id))){
			Chunk chunk = this.chunks.get(this.chunks.indexOf(new Chunk(id)));
			chunk.addToken(token);
			this.changeChunk(chunk);
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean hasHead(String id, String token){
		if(this.chunks.contains(new Chunk(id))){
			Chunk chunk = this.chunks.get(this.chunks.indexOf(new Chunk(id)));
			chunk.addHead(token);
			this.changeChunk(chunk);
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean hasType(String id, String type){
		if(this.chunks.contains(new Chunk(id))){
			Chunk chunk = this.chunks.get(this.chunks.indexOf(new Chunk(id)));
			chunk.setType(type);
			this.changeChunk(chunk);
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean relPred(String id, String relPred){
		if(this.chunks.contains(new Chunk(id))){
			Chunk chunk = this.chunks.get(this.chunks.indexOf(new Chunk(id)));
			chunk.setPosRelPred(relPred);
			this.changeChunk(chunk);
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean pos(String id, String type){
		if(this.tokens.contains(new Token(id))){
			Token token = this.tokens.get(this.tokens.indexOf(new Token(id)));
			token.setPos(type);
			this.changeToken(token);
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean type(String id, String type){
		if(this.tokens.contains(new Token(id))){
			Token token = this.tokens.get(this.tokens.indexOf(new Token(id)));
			token.setType(type);
			this.changeToken(token);
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean ckOt(String id, String chunk){
		if(this.tokens.contains(new Token(id))){
			Token token = this.tokens.get(this.tokens.indexOf(new Token(id)));
			token.setChunk(chunk);
			this.changeToken(token);
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean ckTagOt(String id, String chunk){
		if(this.tokens.contains(new Token(id))){
			Token token = this.tokens.get(this.tokens.indexOf(new Token(id)));
			token.setChunkOT(chunk);
			this.changeToken(token);
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean orth(String id, String chunk){
		if(this.tokens.contains(new Token(id))){
			Token token = this.tokens.get(this.tokens.indexOf(new Token(id)));
			token.setOrth(chunk);
			this.changeToken(token);
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean ner(String id, String ner){
		if(this.tokens.contains(new Token(id))){
			Token token = this.tokens.get(this.tokens.indexOf(new Token(id)));
			token.setNer(ner);
			this.changeToken(token);
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean gpos(String id, String ner){
		if(this.tokens.contains(new Token(id))){
			Token token = this.tokens.get(this.tokens.indexOf(new Token(id)));
			token.setGpos(ner);
			this.changeToken(token);
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean length(String id, String length){
		if(this.tokens.contains(new Token(id))){
			Token token = this.tokens.get(this.tokens.indexOf(new Token(id)));
			token.setLength(Integer.parseInt(length));
			this.changeToken(token);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean stem(String id, String stem){
		stem = stem.replaceAll("'", "");
		if(this.tokens.contains(new Token(id))){
			Token token = this.tokens.get(this.tokens.indexOf(new Token(id)));
			token.setStem(stem);
			this.changeToken(token);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean bigPosAft(String id, String bigPosAft){
		
		if(this.tokens.contains(new Token(id))){
			Token token = this.tokens.get(this.tokens.indexOf(new Token(id)));
			token.setBigPosAft(bigPosAft);
			this.changeToken(token);
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean bigPosBef(String id, String bigPosBef){
		if(this.tokens.contains(new Token(id))){
			Token token = this.tokens.get(this.tokens.indexOf(new Token(id)));
			token.setBigPosBef(bigPosBef);
			this.changeToken(token);
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean trigPosAft(String id, String trigPosAft){
		if(this.tokens.contains(new Token(id))){
			Token token = this.tokens.get(this.tokens.indexOf(new Token(id)));
			token.setTrigPosAft(trigPosAft);
			this.changeToken(token);
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean trigPosBef(String id, String trigPosBef){
		if(this.tokens.contains(new Token(id))){
			Token token = this.tokens.get(this.tokens.indexOf(new Token(id)));
			token.setTrigPosBef(trigPosBef);
			this.changeToken(token);
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean mtype(String id, String mtype){
		if(this.tokens.contains(new Token(id))){
			Token token = this.tokens.get(this.tokens.indexOf(new Token(id)));
			token.setMtype(mtype);
			this.changeToken(token);
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean subtype(String id, String subtype){
		if(this.tokens.contains(new Token(id))){
			Token token = this.tokens.get(this.tokens.indexOf(new Token(id)));
			token.setSubtype(subtype);
			this.changeToken(token);
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean next(String id, String token1){
		if(this.tokens.contains(new Token(id))){
			Token token = this.tokens.get(this.tokens.indexOf(new Token(id)));
			token.setNext(token1);
			this.changeToken(token);
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean ne_type(String id, String type){
		if(this.tokens.contains(new Token(id))){
			Token token = this.tokens.get(this.tokens.indexOf(new Token(id)));
			token.setNeType(type);
			this.changeToken(token);
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean dep(String dep, String token1, String token2){
		if(this.tokens.contains(new Token(token1))){
			Token token = this.tokens.get(this.tokens.indexOf(new Token(token1)));
			token.addDep(new Dependency(dep,token2));
			this.changeToken(token);
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean headpp(String id){
		if(this.tokens.contains(new Token(id))){
			Token token = this.tokens.get(this.tokens.indexOf(new Token(id)));
			token.setHeadPP(true);
			this.changeToken(token);
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean headnp(String id){
		if(this.tokens.contains(new Token(id))){
			Token token = this.tokens.get(this.tokens.indexOf(new Token(id)));
			token.setHeadNP(true);
			this.changeToken(token);
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean headvp(String id){
		if(this.tokens.contains(new Token(id))){
			Token token = this.tokens.get(this.tokens.indexOf(new Token(id)));
			token.setHeadVP(true);
			this.changeToken(token);
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean root(String id){
		if(this.tokens.contains(new Token(id))){
			Token token = this.tokens.get(this.tokens.indexOf(new Token(id)));
			token.setRoot(true);
			this.changeToken(token);
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean fato(String name, String[] parametros){
		boolean success = true;
		switch(name){
			case "chunk":
				success = classe(name,parametros[0]);
				break;
			case "token":
				success = classe(name,parametros[0]);
				break;
			case "ck_hasSucc":
				success = hasSucc(parametros[0],parametros[1]);
				break;
			case "ck_has_tokens":
				success = hasToken(parametros[0],parametros[1]);
				break;
			case "ck_hasHead":
				success = hasHead(parametros[0],parametros[1]);
				break;
			case "ck_hasType":
				success = hasType(parametros[0],parametros[1]);
				break;
			case "t_pos":
				success = pos(parametros[0],parametros[1]);
				break;
			case "t_type":
				success = type(parametros[0],parametros[1]);
				break;
			case "t_ck_ot":
				success = ckOt(parametros[0],parametros[1]);
				break;
			case "t_ck_tag_ot":
				success = ckTagOt(parametros[0],parametros[1]);
				break;
			case "t_orth":
				success = orth(parametros[0],parametros[1]);
				break;
			case "t_ner":
				success = ner(parametros[0],parametros[1]);
				break;
			case "t_gpos":
				success = gpos(parametros[0],parametros[1]);
				break;
			case "t_length":
				success = length(parametros[0],parametros[1]);
				break;
			case "t_stem":
				success = stem(parametros[0],parametros[1]);
				break;
			case "t_bigPosAft":
				success = bigPosAft(parametros[0],parametros[1]);
				break;
			case "t_bigPosBef":
				success = bigPosBef(parametros[0],parametros[1]);
				break;
			case "t_trigPosAft":
				success = trigPosAft(parametros[0],parametros[1]);
				break;
			case "t_trigPosBef":
				success = trigPosBef(parametros[0],parametros[1]);
				break;
			case "t_mtype":
				success = mtype(parametros[0],parametros[1]);
				break;
			case "t_subtype":
				success = subtype(parametros[0],parametros[1]);
				break;
			case "t_isHeadPP":
				success = headpp(parametros[0]);
				break;
			case "t_isHeadNP":
				success = headnp(parametros[0]);
				break;
			case "t_isHeadVP":
				success = headvp(parametros[0]);
				break;
			case "t_next":
				success = next(parametros[0],parametros[1]);
				break;
			case "t_ne_type":
				success = ne_type(parametros[0],parametros[1]);
				break;
			case "ck_posRelPred":
				success = relPred(parametros[0],parametros[1]);
				break;
			case "t_hasDep":
				success = dep(parametros[0],parametros[1],parametros[2]);
				break;
			case "t_root":
				success = root(parametros[0]);
				break;
			default:
				System.out.println(name);
				break;
		}
		return success;
	}
	
	private void changeChunk(Chunk c){
		this.chunks.remove(c);
		this.chunks.add(c);
	}
	
	private void changeToken(Token t){
		this.tokens.remove(t);
		this.tokens.add(t);
	}
}
