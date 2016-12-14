package Predicados;

public class Token {
	private String id;
	private String type;
	private String pos;
	private String chunk;
	private String chunkOT;
	private String orth;
	private String ner;
	private String gpos;
	private int length;
	
	public Token(String id) {
		this.id = id;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public void setPos(String pos){
		this.pos = pos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setChunk(String chunk) {
		this.chunk = chunk;
	}
	
	public void setChunkOT(String chunk) {
		this.chunkOT = chunk;
	}
	
	public void setOrth(String orth){
		this.orth = orth;
	}
	
	public void setNer(String ner){
		this.ner = ner;
	}
	
	public void setGpos(String gpos){
		this.gpos = gpos;
	}
	
	public void setLength(int length){
		this.length = length;
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof Token){
			Token t = (Token) o;
			return this.id.equals(t.getId());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
	    return this.id.hashCode();
	}

	@Override
	public String toString() {
		return "Token [id=" + id + ", type=" + type + ", pos=" + pos
				+ ", chunk=" + chunk + ", chunkOT=" + chunkOT + ", orth="
				+ orth + ", ner=" + ner + ", gpos=" + gpos + ", length="
				+ length + "]";
	}
}
