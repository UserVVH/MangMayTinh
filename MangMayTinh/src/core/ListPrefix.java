package core;

import java.util.ArrayList;
import java.util.List;

public class ListPrefix {
	private List<Prefix> dsPrefix;

	public ListPrefix() {
		dsPrefix = new ArrayList<Prefix>(30);
		dsPrefix.add(new Prefix(8));
		dsPrefix.add(new Prefix(9));
		dsPrefix.add(new Prefix(10));
		dsPrefix.add(new Prefix(11));
		dsPrefix.add(new Prefix(12));
		dsPrefix.add(new Prefix(13));
		dsPrefix.add(new Prefix(14));
		dsPrefix.add(new Prefix(15));
		dsPrefix.add(new Prefix(16));
		dsPrefix.add(new Prefix(17));
		dsPrefix.add(new Prefix(18));
		dsPrefix.add(new Prefix(19));
		dsPrefix.add(new Prefix(20));
		dsPrefix.add(new Prefix(21));
		dsPrefix.add(new Prefix(22));
		dsPrefix.add(new Prefix(23));
		dsPrefix.add(new Prefix(24));
		dsPrefix.add(new Prefix(25));
		dsPrefix.add(new Prefix(26));
		dsPrefix.add(new Prefix(27));
		dsPrefix.add(new Prefix(28));
		dsPrefix.add(new Prefix(29));
		dsPrefix.add(new Prefix(30));
		dsPrefix.add(new Prefix(31));
	}

	public boolean checkPrefix(int prefix) {
		for (Prefix lenPrefix : dsPrefix) {
			if (lenPrefix.getPrefix() == prefix) {
				return true;
			}
		}
		return false;

	}
}
