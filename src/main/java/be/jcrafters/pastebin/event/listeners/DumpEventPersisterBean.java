package be.jcrafters.pastebin.event.listeners;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.jcrafters.pastebin.InMemListDumpDAO;
import be.jcrafters.pastebin.event.DumpEvent;

@ApplicationScoped
public class DumpEventPersisterBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(DumpEventPersisterBean.class);

	@Inject InMemListDumpDAO inMemListDumpDAO;

	public void onDumpEvent(@Observes DumpEvent dumpEvent) {
		LOGGER.info(this.getClass().getSimpleName() + " notified of dumpEvent. Doing stuff.");

		inMemListDumpDAO.persist(dumpEvent
		);
	}

}
