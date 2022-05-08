package nku.haber.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nku.haber.entities.abstracts.ImageResponse;
import nku.haber.entities.concretes.Image;

public interface ImageDao extends JpaRepository<Image, Integer> {
	Image findByFileName(String fileName);

	Image findByUuid(String uuid);

	@Query(value = "select new nku.haber.entities.abstracts.ImageResponse(im.uuid, im.fileName, im.fileType, im.size) from nku.haber.entities.concretes.Image im", nativeQuery = false)
	List<ImageResponse> findAllImageResponse();
}
