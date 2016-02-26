package za.co.dom.poc.exporter;

import java.io.OutputStream;

public interface Exporter {
	public void export(OutputStream out) throws Exception;
}
