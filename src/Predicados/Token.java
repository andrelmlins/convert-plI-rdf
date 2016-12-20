package Predicados;

import java.util.ArrayList;

public class Token {
	private String id;
	private String type;
	private String pos;
	private String chunk;
	private String chunkOT;
	private String orth;
	private String ner;
	private String gpos;
	private String next;
	private String neType;
	private int length;
	
	private String stem;
	private String bigPosAft;
	private String bigPosBef;
	private String trigPosAft;
	private String trigPosBef;
	private String mtype;
	private String subtype;
	private boolean headPP;
	private boolean headNP;
	private boolean headVP;
	private boolean root;
	
	private ArrayList<Dependency> dep = new ArrayList<>();
	
	public Token(String id) {
		this.id = id;
		this.headPP = false;
		this.headNP = false;
		this.headVP = false;
	}
	
	public Token(String id, String type, String pos, String chunk, String chunkOT, String orth, String ner, String gpos,
			String next, int length, String stem, String bigPosAft, String bigPosBef, String trigPosAft,
			String trigPosBef, String mtype, String subtype, boolean headPP, boolean headNP, boolean headVP) {
		super();
		this.id = id;
		this.type = type;
		this.pos = pos;
		this.chunk = chunk;
		this.chunkOT = chunkOT;
		this.orth = orth;
		this.ner = ner;
		this.gpos = gpos;
		this.next = next;
		this.length = length;
		this.stem = stem;
		this.bigPosAft = bigPosAft;
		this.bigPosBef = bigPosBef;
		this.trigPosAft = trigPosAft;
		this.trigPosBef = trigPosBef;
		this.mtype = mtype;
		this.subtype = subtype;
		this.headPP = headPP;
		this.headNP = headNP;
		this.headVP = headVP;
	}

	public void addDep(Dependency d){
		this.dep.add(d);
	}
	
	public ArrayList<Dependency> getDep(){
		return dep;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isRoot() {
		return root;
	}

	public void setRoot(boolean root) {
		this.root = root;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getChunk() {
		return chunk;
	}

	public void setChunk(String chunk) {
		this.chunk = chunk;
	}

	public String getChunkOT() {
		return chunkOT;
	}

	public void setChunkOT(String chunkOT) {
		this.chunkOT = chunkOT;
	}

	public String getOrth() {
		return orth;
	}

	public void setOrth(String orth) {
		this.orth = orth;
	}

	public String getNer() {
		return ner;
	}

	public void setNer(String ner) {
		this.ner = ner;
	}

	public String getGpos() {
		return gpos;
	}

	public void setGpos(String gpos) {
		this.gpos = gpos;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getStem() {
		return stem;
	}

	public void setStem(String stem) {
		this.stem = stem;
	}

	public String getBigPosAft() {
		return bigPosAft;
	}

	public void setBigPosAft(String bigPosAft) {
		this.bigPosAft = bigPosAft;
	}

	public String getBigPosBef() {
		return bigPosBef;
	}

	public void setBigPosBef(String bigPosBef) {
		this.bigPosBef = bigPosBef;
	}

	public String getTrigPosAft() {
		return trigPosAft;
	}

	public void setTrigPosAft(String trigPosAft) {
		this.trigPosAft = trigPosAft;
	}

	public String getTrigPosBef() {
		return trigPosBef;
	}

	public void setTrigPosBef(String trigPosBef) {
		this.trigPosBef = trigPosBef;
	}

	public String getMtype() {
		return mtype;
	}

	public void setMtype(String mtype) {
		this.mtype = mtype;
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public boolean isHeadPP() {
		return headPP;
	}

	public void setHeadPP(boolean headPP) {
		this.headPP = headPP;
	}

	public boolean isHeadNP() {
		return headNP;
	}

	public void setHeadNP(boolean headNP) {
		this.headNP = headNP;
	}

	public boolean isHeadVP() {
		return headVP;
	}

	public void setHeadVP(boolean headVP) {
		this.headVP = headVP;
	}

	public String getNeType() {
		return neType;
	}

	public void setNeType(String neType) {
		this.neType = neType;
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
		return "Token [id=" + id + ", type=" + type + ", pos=" + pos + ", chunk=" + chunk + ", chunkOT=" + chunkOT
				+ ", orth=" + orth + ", ner=" + ner + ", gpos=" + gpos + ", next=" + next + ", length=" + length
				+ ", stem=" + stem + ", bigPosAft=" + bigPosAft + ", bigPosBef=" + bigPosBef + ", trigPosAft="
				+ trigPosAft + ", trigPosBef=" + trigPosBef + ", mtype=" + mtype + ", subtype=" + subtype + ", headPP="
				+ headPP + ", headNP=" + headNP + ", headVP=" + headVP + "]";
	}
	
	public String string() {
		return id+" -- "+type+" -- "+pos+" -- "+chunk+" -- "+chunkOT+" -- "+orth+" -- "+ner+" -- "+gpos+" -- "+next
				+" -- "+length+" -- "+stem+" -- "+bigPosAft+" -- "+bigPosBef+" -- "+trigPosAft+" -- "+trigPosBef
				+" -- "+mtype+" -- "+subtype+" -- "+headPP+" -- "+headNP+" -- "+headVP;
	}
}
