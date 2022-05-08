package nku.haber.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nku.haber.business.abstracts.ImageService;
import nku.haber.dataAccess.abstracts.ImageDao;
import nku.haber.entities.abstracts.ImageResponse;
import nku.haber.entities.concretes.Image;

@Service
public class ImageManager implements ImageService {
	@Autowired
	private ImageDao imageRepository;

	@Override
	public Image save(Image image) throws NullPointerException {
		if (image == null)
			throw new NullPointerException("Image Data NULL");
		return imageRepository.save(image);
	}

	@Override
	public Image findByFileName(String fileName) {
		return this.imageRepository.findByFileName(fileName);
	}

	@Override
	public Image findByUuid(String uuid) {
		return this.imageRepository.findByUuid(uuid);
	}

	@Override
	public List<ImageResponse> findAllImageResponse() {
		return this.imageRepository.findAllImageResponse();
	}
}
