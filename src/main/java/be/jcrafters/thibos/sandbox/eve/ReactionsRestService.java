package be.jcrafters.thibos.sandbox.eve;

import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import java.util.Random;
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
	public String printInventory() throws Exception {

		LOGGER.info("Received GET request on /reactions");

		List<Reaction> ownedReactionFormulae = findOwnedReactionFormulae();
		Map<Long, Reaction> ownedReactionFormulaeById = ownedReactionFormulae.stream().collect(
				toMap(r -> new Random().nextLong(), Function.identity())
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

		return "#" + Ownership.OWNED.name() + "\n" +
			   ownedReactionFormulaeById.entrySet().stream().map(entry -> entry.getKey() + ";" + entry.getValue() + "\n").collect(Collectors.joining("\n")) +
			   "#" + Ownership.REQUESTED.name() + "\n" +
			   requestedReactionFormulaeById.entrySet().stream().map(entry -> entry.getKey() + ";" + entry.getValue() + "\n").collect(Collectors.joining("\n")) +
			   "#" + Ownership.NEEDED.name() + "\n" +
			   neededReactionFormulaeById.entrySet().stream().map(entry -> entry.getKey() + ";" + entry.getValue() + "\n").collect(Collectors.joining("\n"));
	}

	public List<Reaction> findOwnedReactionFormulae() {
		return reactionsService.findOwnedReactions();
	}

	private List<Reaction> findRequestedReactionFormulae() {
		return reactionsService.findRequestedReactions();
	}

	private List<Reaction> compileNeededReactionFormulae() {
		return reactionsService.compileNeededReactionFormulae();
	}

}
