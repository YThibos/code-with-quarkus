package be.jcrafters.pastebin;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class DumpRecogniser {

	public Dump handle(String stringDump) {
		return new Dump(stringDump);
	}
}
