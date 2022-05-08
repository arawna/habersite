package nku.haber.entities.concretes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import nku.haber.core.utilities.helpers.FileNameHelper;

@Entity
@Table(name = "images")
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "file_type")
	private String fileType;

	@Column(name = "size")
	private long size;

	@Column(name = "uuid")
	private String uuid;

	@Column(name = "system_name")
	private String systemName;

	@Lob
	@Column(name = "data")
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] data;

	
	public Image() {
		super();
	}
	
	

	public Image(int id, String fileName, String fileType, long size, String uuid, String systemName, byte[] data) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.fileType = fileType;
		this.size = size;
		this.uuid = uuid;
		this.systemName = systemName;
		this.data = data;
	}

	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
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



	public String getUuid() {
		return uuid;
	}



	public void setUuid(String uuid) {
		this.uuid = uuid;
	}



	public String getSystemName() {
		return systemName;
	}



	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}



	public byte[] getData() {
		return data;
	}



	public void setData(byte[] data) {
		this.data = data;
	}



	/**
	 * Create new Image class.
	 * 
	 * @return new Image.
	 */
	@Transient
	public static Image build() {
		String uuid = UUID.randomUUID().toString();
		Image image = new Image();
		image.setUuid(uuid);
		image.setSystemName("default");
		return image;
	}

	@Transient
	public void setFiles(MultipartFile file) {
		setFileType(file.getContentType());
		setSize(file.getSize());
	}

	/**
	 * Scale image data with given width and height.
	 * 
	 * @param width  scale width
	 * @param height scale height
	 * @return scaled image byte array and change to class data.
	 */
	@Transient
	public byte[] scale(int width, int height) throws Exception {

		if (width == 0 || height == 0)
			return data;

		ByteArrayInputStream in = new ByteArrayInputStream(data);

		try {
			BufferedImage img = ImageIO.read(in);

			java.awt.Image scaledImage = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
			BufferedImage imgBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			imgBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();

			ImageIO.write(imgBuff, "jpg", buffer);
			setData(buffer.toByteArray());
			return buffer.toByteArray();

		} catch (Exception e) {
			throw new Exception("IOException in scale");
		}
	}

	/**
	 * @param fileName - filename of the resources.
	 * 
	 * @return inputstream for image
	 * */
	private static InputStream getResourceFileAsInputStream(String fileName) {
	    ClassLoader classLoader = Image.class.getClassLoader();
	    return classLoader.getResourceAsStream(fileName);
	}
	
	/**
	 * Generate no context image with `notfound.jpg` image in asset.
	 * 
	 * @return create default image.
	 */
	@Transient
	public static Image defaultImage() throws Exception {
	    InputStream is = getResourceFileAsInputStream("notfound.jpg");
		String fileType = "image/jpeg";
		byte[] bdata = FileCopyUtils.copyToByteArray(is);
		Image image = new Image();
		image.setFileType(fileType);
		image.setData(bdata);
		return image;
	}

	/**
	 * Generate scaled no context image with `notfound.jpg` image in asset with
	 * given width and height.
	 * 
	 * @param width  scale width
	 * @param height scale height
	 * @return create scaled default image.
	 */
	@Transient
	public static Image defaultImage(int width, int height) throws Exception {
		Image defaultImage = defaultImage();
		defaultImage.scale(width, height);
		return defaultImage;
	}

	/**
	 * Generate scaled no context image with `notfound.jpg` image in asset with
	 * given width and height.
	 * 
	 * @param file   multipartfile data to build.
	 * @param helper filenamehelper class to generate name.
	 * @return return new Image class related with file.
	 */
	@Transient
	public static Image buildImage(MultipartFile file, FileNameHelper helper) {
		String fileName = helper.generateDisplayName(file.getOriginalFilename());

		Image image = Image.build();
		image.setFileName(fileName);
		image.setFiles(file);

		try {
			image.setData(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

}
