package be.jcrafters.thibos.sandbox.eve;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import javax.inject.Singleton;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class ReactionsFileService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReactionsFileService.class);

	private ReactionsParser reactionsParser;

	public ReactionsFileService(ReactionsParser reactionsParser) {
		this.reactionsParser = reactionsParser;
	}

	public Map<Ownership, List<Reaction>> loadAll() {
		List<Reaction> ownedFormulae = loadOwnedFile();
		List<Reaction> requestedReactions = loadRequestsFile();

		EnumMap<Ownership, List<Reaction>> formulaePerStatus = new EnumMap<>(Ownership.class);
		formulaePerStatus.put(Ownership.OWNED, ownedFormulae);
		formulaePerStatus.put(Ownership.REQUESTED, requestedReactions);
		return formulaePerStatus;
	}

	private List<Reaction> loadRequestsFile() {
		LOGGER.info("Parsing requested reactions file..");
		return parseFile("src/main/resources/reactions-requested.csv");
	}

	private List<Reaction> loadOwnedFile() {
		LOGGER.info("Parsing owned reactions file..");
		return parseFile("src/main/resources/reactions-owned.csv");
	}

	private List<Reaction> parseFile(String filePath) {
		try {
			List<CSVRecord> records = CSVParser.parse(FileUtils.getFile(filePath),
													  StandardCharsets.UTF_8,
													  CSVFormat.DEFAULT)
					.getRecords();

			records.forEach(r -> LOGGER.info("parsed: " + r.toString()));

			return reactionsParser.parse(records);
		} catch (IOException e) {
			LOGGER.error("kaput; ", e);
		}
		return new ArrayList<>();
	}

	public SortedSet<? extends Reaction> getRequestedFormulae() {
		return null;
	}
}
