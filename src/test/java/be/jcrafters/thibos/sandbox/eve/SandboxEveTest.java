package be.jcrafters.thibos.sandbox.eve;

import org.junit.jupiter.api.Test;

public class SandboxEveTest {

	@Test
	void basicReactionsPrintStatus() throws Exception {
		new ReactionsRestService(new ReactionsService(new ReactionsRepository(new DummyReactionsFileService()))).printStatus();
	}

	private class DummyReactionsFileService extends ReactionsFileService {

	}
}
