package be.jcrafters.thibos.sandbox.eve;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ReactionsRepository {

	private List<Reaction> requestedReactionFormulae = new ArrayList<>();
	private List<Reaction> ownedReactionFormulae = new ArrayList<>();

	@Inject
	public ReactionsRepository(ReactionsFileService reactionsFileService) {
		Map<Ownership, List<Reaction>> reactionsByOwnership = reactionsFileService.loadAll();

		ownedReactionFormulae.addAll(reactionsByOwnership.get(Ownership.OWNED));
		requestedReactionFormulae.addAll(reactionsByOwnership.get(Ownership.REQUESTED));
	}

	public List<Reaction> getOwnedReactionFormulae() {
		return new ArrayList<>(ownedReactionFormulae);
	}

	public List<Reaction> getRequestedReactionFormulae() {
		return new ArrayList<>(requestedReactionFormulae);
	}
}
