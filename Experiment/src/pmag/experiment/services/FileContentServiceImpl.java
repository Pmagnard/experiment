package pmag.experiment.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import pmag.experiment.beans.TextContent;

public class FileContentServiceImpl implements FileContentService {

	@Override
	public TextContent extractContent(File file) throws ServiceException {
		if (file == null) {
			return null;
		}
		InputStream stream;
		try {
			stream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new ServiceException(e);
		}
		return extractContent(stream);
	}

	@Override
	public TextContent extractContent(InputStream stream) throws ServiceException {
		if (stream == null) {
			return null;
		}
		// extract info with Tika
		AutoDetectParser parser = new AutoDetectParser();
		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		
		try {
			parser.parse(stream, handler, metadata);
		} catch (IOException | SAXException | TikaException e) {
			throw new ServiceException(e);
		}
		TextContent content = null;
		if (handler != null) {
			content = new TextContent();
			content.setText(handler.toString());
		}
		return content;
	}

}
