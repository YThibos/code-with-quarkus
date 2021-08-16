package be.jcrafters.thibos.sandbox.eve;

import static java.lang.Long.parseLong;
import static java.util.stream.Collectors.toList;

import java.util.List;

import org.apache.commons.csv.CSVRecord;

public class ReactionsParser {

	public List<Reaction> parse(List<CSVRecord> in) {
		return in.stream()
				.flatMap(CSVRecord::stream)
				.map(line -> line.split(";"))
				.map(this::toReaction)
				.collect(toList());
	}

	private Reaction toReaction(String[] values) {
		return new Reaction(parseLong(values[0]), values[1]);
	}
}
