package pmag.experiment.beans;

public class FileInfo {
	String name;
	long size;
	TextContent textContent;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public TextContent getTextContent() {
		return textContent;
	}
	public void setTextContent(TextContent textContent) {
		this.textContent = textContent;
	}

}
