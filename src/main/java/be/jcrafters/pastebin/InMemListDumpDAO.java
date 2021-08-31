package be.jcrafters.pastebin;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.jcrafters.pastebin.event.DumpEvent;

@ApplicationScoped
public class InMemListDumpDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(InMemListDumpDAO.class);

	private List<Dump> storedDumps = new ArrayList<>();

	public void persist(DumpEvent dumpEvent) {
		LOGGER.info("Added dump to storedDumps.");
		//storedDumps.add(dumpEvent);

		LOGGER.info("storedDumps = " + storedDumps);
	}
}
