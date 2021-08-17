package be.jcrafters.thibos.sandbox.eve;

import static java.lang.Long.parseLong;
import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.inject.Singleton;

import org.apache.commons.csv.CSVRecord;

@Singleton
public class ReactionsParser {

	public List<CompositeReaction> parse(List<CSVRecord> in) {
		return in.stream()
				.flatMap(CSVRecord::stream)
				.map(line -> line.split(";"))
				.map(this::toReaction)
				.collect(toList());
	}

	private CompositeReaction toReaction(String[] values) {
		return new CompositeReaction(parseLong(values[0]), values[1]);
	}
}
