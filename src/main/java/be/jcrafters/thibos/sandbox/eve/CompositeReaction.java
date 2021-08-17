package be.jcrafters.thibos.sandbox.eve;

import java.util.HashSet;
import java.util.Set;

public class CompositeReaction implements Reaction, Composite, Item {

	private Long id;
	private String name;

	private Set<CompositeReaction> childReactions;

	public CompositeReaction(long id, String name) {
		this.id = id;
		this.name = name;

		childReactions = fetchChildReactions();
	}

	private Set<CompositeReaction> fetchChildReactions() {
		return new HashSet<>();
	}

	@Override
	public Set<CompositeReaction> getChildReactions() {
		return Set.copyOf(childReactions);
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "CompositeReaction{" +
			   "id=" + id +
			   ", name='" + name + '\'' +
			   ", childReactions=" + childReactions +
			   '}';
	}
}
