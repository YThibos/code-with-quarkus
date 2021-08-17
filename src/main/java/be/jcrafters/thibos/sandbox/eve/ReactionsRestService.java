package be.jcrafters.thibos.sandbox.eve;

import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

@Path("/reactions")
public class ReactionsRestService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReactionsRestService.class);

	ReactionsService reactionsService;

	@Inject
	public ReactionsRestService(ReactionsService reactionsService) {
		this.reactionsService = reactionsService;
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String printStatus() throws Exception {

		LOGGER.info("Received GET request on /reactions");

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
			   ownedReactionFormulaeById.entrySet().stream().map(entry -> entry.getKey() + ";" + entry.getValue() + "\n").collect(Collectors.joining("\n")) +
			   "#REQUESTED FORMULAE\n" +
			   requestedReactionFormulaeById.entrySet().stream().map(entry -> entry.getKey() + ";" + entry.getValue() + "\n").collect(Collectors.joining("\n")) +
			   "#NEEDED FORMULAE\n" +
			   neededReactionFormulaeById.entrySet().stream().map(entry -> entry.getKey() + ";" + entry.getValue() + "\n").collect(Collectors.joining("\n"));
	}

	private List<Reaction> compileNeededReactionFormulae() {
		return reactionsService.compileNeededReactionFormulae();
	}

	private List<Reaction> findRequestedReactionFormulae() {
		return reactionsService.findRequestedReactions();
	}

	public List<Reaction> findOwnedReactionFormulae() {
		return reactionsService.findOwnedReactions();
	}

}
