package nku.haber.business.abstracts;

import java.util.List;

import nku.haber.entities.abstracts.ImageResponse;
import nku.haber.entities.concretes.Image;

public interface ImageService {
	public Image save(Image image);

	public Image findByFileName(String fileName);

	public Image findByUuid(String uuid);
	
	public List<ImageResponse> findAllImageResponse();
}
