package cn.surveyking.server.service;

import cn.surveyking.server.core.constant.AppConsts;
import cn.surveyking.server.domain.dto.FileView;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * @author javahuang
 * @date 2021/9/10
 */
public interface FileService {

	List<String> SUPPORT_IMAGE_LIST = Arrays.asList(AppConsts.SUPPORT_IMAGE_TYPE);

	FileView upload(MultipartFile file, AppConsts.StorageType storageType);

	List<FileView> listImages(AppConsts.StorageType storageType);

	void deleteImage(String id);

	Resource loadAsResource(String attachmentId);

	/**
	 * 是否支持该格式的图片上传
	 * @param fileName
	 * @return
	 */
	default boolean isSupportImage(String fileName) {
		String extType = fileName.substring(fileName.lastIndexOf(".") + 1);
		return SUPPORT_IMAGE_LIST.contains(extType.toUpperCase());
	}

}
