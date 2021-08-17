package be.jcrafters.thibos.sandbox.eve;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ReactionsRepository {
	private List<Reaction> requestedReactionFormulae = new ArrayList<>();
	private List<Reaction> ownedReactionFormulae = new ArrayList<>();

	private ReactionsFileService reactionsFileService;

	@Inject
	public ReactionsRepository(ReactionsFileService reactionsFileService) {
		this.reactionsFileService = reactionsFileService;
	}

	@PostConstruct
	public void postConstruct() {

	}

	public List<Reaction> getOwnedReactionFormulae() {
		return new ArrayList<>(ownedReactionFormulae);
	}

	public List<Reaction> getRequestedReactionFormulae() {
		return new ArrayList<>(requestedReactionFormulae);
	}
}
