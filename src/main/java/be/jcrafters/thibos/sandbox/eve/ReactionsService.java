package be.jcrafters.thibos.sandbox.eve;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;

@Singleton
public class ReactionsService {

	ReactionsParser reactionsParser;

	@Inject
	public ReactionsService(ReactionsParser reactionsParser) {
		this.reactionsParser = reactionsParser;
	}

	public void printStatus() throws IOException {
		System.out.println("\nOWNED REACTIONS>\n-----------------");
		List<Reaction> ownedReactions = read("src/main/resources/reactions-mine.csv");
		ownedReactions.forEach(System.out::println);

		System.out.println("\nREQUESTED REACTIONS>\n-----------------");
		List<Reaction> requestedReactions = read("src/main/resources/reactions-requested.csv");
		requestedReactions.forEach(System.out::println);

		ArrayList<Reaction> neededReactions = new ArrayList<>(requestedReactions);
		neededReactions.removeAll(ownedReactions);
		System.out.println("\nTO OBTAIN>\n____________");
		neededReactions.forEach(System.out::println);
	}

	private List<Reaction> read(String filename) throws IOException {
		File requestedReactionsCsv = FileUtils.getFile(filename);

		List<CSVRecord> records = CSVParser.parse(requestedReactionsCsv, StandardCharsets.UTF_8, CSVFormat.DEFAULT).getRecords();

		reactionsParser = new ReactionsParser();
		return reactionsParser.parse(records);
	}
}
