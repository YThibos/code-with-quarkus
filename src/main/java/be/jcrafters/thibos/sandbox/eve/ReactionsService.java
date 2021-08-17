package be.jcrafters.thibos.sandbox.eve;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ReactionsService {

	private ReactionsRepository reactionsRepository;

	@Inject
	public ReactionsService(ReactionsRepository reactionsRepository) {
		this.reactionsRepository = reactionsRepository;
	}

	public List<Reaction> findOwnedReactions() {
		return reactionsRepository.getOwnedReactionFormulae();
	}

	public List<Reaction> findRequestedReactions() {
		return reactionsRepository.getRequestedReactionFormulae();
	}

	public List<Reaction> compileNeededReactionFormulae() {
		List<Reaction> safeCopy = new ArrayList<>(reactionsRepository.getRequestedReactionFormulae());
		safeCopy.removeAll(reactionsRepository.getOwnedReactionFormulae());
		return safeCopy;
	}
}
