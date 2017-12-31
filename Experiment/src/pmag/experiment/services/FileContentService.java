package pmag.experiment.services;

import java.io.File;
import java.io.InputStream;

import pmag.experiment.beans.TextContent;

public interface FileContentService {
	public TextContent extractContent(File file) throws ServiceException;
	public TextContent extractContent(InputStream stream) throws ServiceException;
}
