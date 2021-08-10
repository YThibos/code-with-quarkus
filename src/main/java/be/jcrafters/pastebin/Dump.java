package be.jcrafters.pastebin;

public class Dump {

	private final String dump;

	public Dump(String dump) {
		this.dump = dump;
	}

	public String getDump() {
		return dump;
	}

	@Override
	public String toString() {
		return getDump();
	}
}
