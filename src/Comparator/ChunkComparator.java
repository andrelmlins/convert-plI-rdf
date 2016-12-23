package Comparator;

import java.util.Comparator;

import Predicados.Chunk;

public class ChunkComparator implements Comparator<Chunk> {

	@Override
	public int compare(Chunk c0, Chunk c1) {
		Integer id0 = Integer.parseInt(c0.getId().split("_")[1]);
		Integer id1 = Integer.parseInt(c1.getId().split("_")[1]);
		return id0.compareTo(id1);
	}

}
