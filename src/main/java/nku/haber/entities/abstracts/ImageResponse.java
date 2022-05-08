package nku.haber.entities.abstracts;

import nku.haber.entities.concretes.Image;

public class ImageResponse {
	private String uuid;
	private String fileName;
	private String fileType;
	private long size;

	public ImageResponse(Image image) {
		setUuid(image.getUuid());
		setFileName(image.getFileName());
		setFileType(image.getFileType());
		setSize(image.getSize());
	}

	public ImageResponse() {
		super();
	}

	public ImageResponse(String uuid, String fileName, String fileType, long size) {
		super();
		this.uuid = uuid;
		this.fileName = fileName;
		this.fileType = fileType;
		this.size = size;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
	
	
}
