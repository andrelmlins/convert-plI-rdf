import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Predicados.Chunk;
import Predicados.Sentence;
import Predicados.Token;


public class Compilador {
	private ArrayList<Chunk> chunks = new ArrayList<>();
	private ArrayList<Sentence> sentences = new ArrayList<>();
	private ArrayList<Token> tokens = new ArrayList<>();
	
	private List<String> facts = new ArrayList<>();
	private List<String> factsNoReads = new ArrayList<>();
	
	public void run() throws FileNotFoundException{
		Scanner s = new Scanner(new File("CorpusACE2004_FULL.pl"));
		while(s.hasNext()){
			String line = s.nextLine().replaceAll(" ","");
			facts.add(line);
		}
		s.close();
		
		while(this.facts.size()!=0){
			for(String fact : this.facts){
				if(!fact.equals("") && fact.charAt(0)!='%'){
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
		}
		
		for(Token t : this.tokens){
			System.out.println(t.toString());
		}		
	}
	
	public boolean classe(String name, String id){
		if(name.equals("chunk")){
			chunks.add(new Chunk(id));
		} else if(name.equals("st")){
			sentences.add(new Sentence(id));
		} else if(name.equals("token")){
			tokens.add(new Token(id));
		}
		return true;
	}
	
	public boolean voice(String id, String voice){
		if(this.sentences.contains(new Sentence(id))){
			Sentence sentence = this.sentences.get(this.sentences.indexOf(new Sentence(id)));
			sentence.setVoice(voice);
			this.changeSentence(sentence);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean hasChunk(String id, String chunk){
		if(this.sentences.contains(new Sentence(id))){
			Sentence sentence = this.sentences.get(this.sentences.indexOf(new Sentence(id)));
			sentence.addChunk(chunk);
			this.changeSentence(sentence);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean hasSucc(String id, String chunk1){
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
	
	public boolean hasToken(String id, String token){
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
	
	public boolean hasHead(String id, String token){
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
	
	public boolean hasType(String id, String type){
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
	
	public boolean pos(String id, String type){
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
	
	public boolean type(String id, String type){
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
	
	public boolean ckOt(String id, String chunk){
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
	
	public boolean ckTagOt(String id, String chunk){
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
	
	public boolean orth(String id, String chunk){
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
	
	public boolean ner(String id, String ner){
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
	
	public boolean gpos(String id, String ner){
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
	
	public boolean fato(String name, String[] parametros){
		boolean success = true;
		switch(name){
			case "chunk":
				success = classe(name,parametros[0]);
				break;
			case "st":
				success = classe(name,parametros[0]);
				break;
			case "token":
				success = classe(name,parametros[0]);
				break;
			case "st_hasVoice":
				success = voice(parametros[0],parametros[1]);
				break;
			case "s_hasChunk":
				success = hasChunk(parametros[0],parametros[1]);
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
			default:
				break;
		}
		return success;
	}
	
	public void changeSentence(Sentence s){
		this.sentences.remove(s);
		this.sentences.add(s);
	}
	
	public void changeChunk(Chunk c){
		this.chunks.remove(c);
		this.chunks.add(c);
	}
	
	public void changeToken(Token t){
		this.tokens.remove(t);
		this.tokens.add(t);
	}
}
