package Predicados;

import java.util.ArrayList;

public class Chunk {
	private String id;
	private ArrayList<String> chunks;
	private ArrayList<String> tokens;
	private ArrayList<String> heads;
	private String type;
	
	public Chunk(String id) {
		this.id = id;
		this.chunks = new ArrayList<>();
		this.tokens = new ArrayList<>();
		this.heads = new ArrayList<>();
	}
	
	public void addChunk(String chunk){
		this.chunks.add(chunk);
	}
	
	public void addToken(String token){
		this.tokens.add(token);
	}
	
	public void addHead(String token){
		this.heads.add(token);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof Chunk){
			Chunk c = (Chunk) o;
			return this.id.equals(c.getId());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
	    return this.id.hashCode();
	}

	@Override
	public String toString() {
		return "Chunk [id=" + id + ", chunks=" + chunks + ", tokens=" + tokens
				+ ", heads=" + heads + ", type=" + type + "]";
	}
}
