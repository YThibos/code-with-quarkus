package be.jcrafters.thibos.sandbox.eve;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class SandboxEveTest {

	@Test
	void name() throws IOException {
		new ReactionsService(new ReactionsParser()).printStatus();
	}
}
