package be.jcrafters.thibos.sandbox.eve;

import org.junit.jupiter.api.Test;

public class SandboxEveTest {

	@Test
	void basicReactionsPrintStatus() throws Exception {
		new ReactionsRestService(new ReactionsService(new ReactionsRepository(new DummyReactionsFileService(new ReactionsParser())))).printStatus();
	}

	private static class DummyReactionsFileService extends ReactionsFileService {

		public DummyReactionsFileService(ReactionsParser reactionsParser) {
			super(new ReactionsParser());
		}
	}
}
