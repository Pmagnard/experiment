package pmag.experiment.services.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import pmag.experiment.beans.FileInfo;
import pmag.experiment.beans.TextContent;
import pmag.experiment.services.DebugService;
import pmag.experiment.services.FileContentService;
import pmag.experiment.services.ServiceException;

@RestController
public class ApplicationRestController {
	//TODO Read, learn and implement error handling properly
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private DebugService debugService;
	
	@Autowired
	private FileContentService fileContentService;
	
	@GetMapping("/hello")
	public String getHello() {
		return debugService.run("Hello");

	}
	
	@PostMapping("/multi") 
	public FileInfo postMulti(MultipartHttpServletRequest multipartRequest) throws IOException, ServiceException {
		FileInfo fileInfo = null;
		logger.debug("Processing POST request on /multi");
		if (multipartRequest != null) {
			//reading parameters
			Map<String, String[]> parameterMap = multipartRequest.getParameterMap();
			if (parameterMap != null && !parameterMap.isEmpty()) {
				for (String paramName : parameterMap.keySet()) {
					String paramValue = parameterMap.get(paramName)[0];
					logger.debug("Parameter " + paramName + " = " + paramValue);
				}
			}
			//reading multipart files
			MultiValueMap<String, MultipartFile> multiFileMap = multipartRequest.getMultiFileMap();
			if (multiFileMap != null && !multiFileMap.isEmpty()) {
				for (String multiFileName : multiFileMap.keySet()) {
					List<MultipartFile> multiPartFileList = multiFileMap.get(multiFileName);
					if (multiPartFileList != null && !multiPartFileList.isEmpty()) {
						for (MultipartFile multipartFile : multiPartFileList) {
							String originalName = multipartFile.getOriginalFilename();
							String name = multipartFile.getName();
							long size = multipartFile.getSize();
							if (size != 0) {
							logger.debug("Parameter " + multiFileName + " is a file with name " + name + " (original name is " + originalName + ") and size is " + size);
							fileInfo = new FileInfo();
							fileInfo.setName(name);
							fileInfo.setSize(size);
							InputStream stream = multipartFile.getInputStream();
							TextContent textContent = fileContentService.extractContent(stream);
							fileInfo.setTextContent(textContent);
							} else {
								logger.debug("File parameter " + multiFileName + " has not been set or is empty");
							}
						}
					}
				}
			}
		}

		return fileInfo;
	}
}
