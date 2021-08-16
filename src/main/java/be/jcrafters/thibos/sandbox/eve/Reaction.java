package be.jcrafters.thibos.sandbox.eve;

public class Reaction {
	private Long id;
	private String name;

	public Reaction(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Reaction{" +
			   "id=" + id +
			   ", name='" + name + '\'' +
			   '}';
	}
}
