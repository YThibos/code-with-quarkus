package be.jcrafters.thibos.sandbox.eve;

import static java.util.stream.Collectors.toMap;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/reactions")
public class ReactionsRestService {

	ReactionsService reactionsService;

	@Inject
	public ReactionsRestService(ReactionsService reactionsService) {
		this.reactionsService = reactionsService;
	}

	@GET
	@Path("/printStatus")
	@Produces(MediaType.TEXT_PLAIN)
	public String printStatus() throws Exception {

		List<Reaction> ownedReactionFormulae = findOwnedReactionFormulae();
		Map<Long, Reaction> ownedReactionFormulaeById = ownedReactionFormulae.stream().collect(
				toMap(Reaction::getId, Function.identity())
		);

		List<Reaction> requestedReactionFormulae = findRequestedReactionFormulae();
		Map<Long, Reaction> requestedReactionFormulaeById = requestedReactionFormulae.stream().collect(
				toMap(Reaction::getId, Function.identity())
		);

		List<Reaction> neededReactionFormulae = compileNeededReactionFormulae();
		requestedReactionFormulae.removeAll(ownedReactionFormulae);
		Map<Long, Reaction> neededReactionFormulaeById = neededReactionFormulae.stream().collect(
				toMap(Reaction::getId, Function.identity())
		);

		return "#OWNED FORMULAE\n" +
			   ownedReactionFormulaeById.entrySet().stream().map(entry -> entry.getKey() + ";" + entry.getValue() + "\n") +
			   "#REQUESTED FORMULAE\n" +
			   requestedReactionFormulaeById.entrySet().stream().map(entry -> entry.getKey() + ";" + entry.getValue() + "\n") +
			   "#NEEDED FORMULAE\n" +
			   neededReactionFormulaeById.entrySet().stream().map(entry -> entry.getKey() + ";" + entry.getValue() + "\n");
	}

	private List<Reaction> compileNeededReactionFormulae() {
		return reactionsService.compileNeededReactionFormulae();
	}

	private List<Reaction> findRequestedReactionFormulae() {
		return reactionsService.findRequestedReactions();
	}

	public List<Reaction> findOwnedReactionFormulae() throws IOException {
		return reactionsService.findOwnedReactions();
	}

}
