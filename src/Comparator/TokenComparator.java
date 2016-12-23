package Comparator;

import java.util.Comparator;

import Predicados.Token;

public class TokenComparator implements Comparator<Token> {

	@Override
	public int compare(Token t0, Token t1) {
		Integer id0 = Integer.parseInt(t0.getId().split("_")[1]);
		Integer id1 = Integer.parseInt(t1.getId().split("_")[1]);
		return id0.compareTo(id1);
	}

}
