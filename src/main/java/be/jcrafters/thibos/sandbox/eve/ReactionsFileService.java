package be.jcrafters.thibos.sandbox.eve;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	public Map<OwnStatus, Set<Reaction>> loadAll() {
		loadOwnedFile();
		loadRequestsFile();

		return null;
	}

	private void loadRequestsFile() {
		loadFile("src/main/resources/reactions-requested.csv");
	}

	private void loadOwnedFile() {
		loadFile("src/main/resources/reactions-owned.csv");
	}

	private void loadFile(String filePath) {
		try {
			List<CSVRecord> records = CSVParser.parse(FileUtils.getFile(filePath),
													  StandardCharsets.UTF_8,
													  CSVFormat.DEFAULT)
					.getRecords();

			records.forEach(record -> record.get(CsvHeader.ID));
		} catch (IOException e) {
			LOGGER.error("kaput; ", e);
		}
	}

}
