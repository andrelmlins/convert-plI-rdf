package Predicados;

import java.util.ArrayList;

public class Sentence {
	private String voice;
	private String id;
	private ArrayList<String> chunks;
	
	public Sentence(String id) {
		this.id = id;
		this.chunks = new ArrayList<>();
	}
	
	public void addChunk(String chunk){
		this.chunks.add(chunk);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVoice() {
		return voice;
	}

	public void setVoice(String voice) {
		this.voice = voice;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Sentence){
			Sentence p = (Sentence) o;
			return this.id.equals(p.getId());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
	    return this.id.hashCode();
	}

	@Override
	public String toString() {
		return "Sentence [voice=" + voice + ", id=" + id + ", chunks=" + chunks
				+ "]";
	}
}
