package com.ysk.source.controller.uit;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ysk.contants.UploadConstant;
import com.ysk.kxt.sourceUit.FileOperateUtils;

/**
 * 文件上传
 * 
 * @author admin
 *
 */
@RestController
@RequestMapping(value = "/communal")
public class CommunalContorller {

	Logger log = LoggerFactory.getLogger(CommunalContorller.class);

	@RequestMapping(value = "/upload")
	@ResponseBody
	public Object upload(HttpServletRequest request, @RequestParam("uploadType") String uploadType) throws Exception {
		// 图标图片路径
		String imagePath = "";
		// 用户头像
		if ("userImg".equals(uploadType)) {
			imagePath = UploadConstant.RESOURCES_URL + UploadConstant.IMAGES_USER;
			// 购药处方
		} else if ("prescription".equals(uploadType)) {
			imagePath = UploadConstant.RESOURCES_URL + UploadConstant.IMAGES_ORDER_PRESCRIPTION;
			// 药店banner(背景)
		} else if ("drugstoreBg".equals(uploadType)) {
			imagePath = UploadConstant.RESOURCES_URL + UploadConstant.IMAGES_DRUGSTORE_BANNER;
			// 药品banner
		} else if ("drugBanner".equals(uploadType)) {
			imagePath = UploadConstant.RESOURCES_URL + UploadConstant.IMAGES__DRUG_BANNER;
			// 药品图文详情
		} else if ("captionImg".equals(uploadType)) {
			imagePath = UploadConstant.RESOURCES_URL + UploadConstant.IMAGES_DRUG_CAPTIONIMG;
		}

		List<String> result = FileOperateUtils.upload(request, imagePath);

		Map<String, Object> map = new HashMap<String, Object>();
		// 文件名
		String fileName = "";

		if (result != null) {
			fileName = result.get(0);
			// 图片路径
			imagePath = imagePath.substring(imagePath.indexOf("/", 3)) + fileName;

			map.put("imagePath", imagePath);
		}
		return map;
	}

	/** 删除图片 */
	@RequestMapping(value = "/deleteImage")
	@ResponseBody
	public Object deleteImage(HttpServletRequest request, @RequestParam(value = "filePath") String filePath)
			throws Exception {
		// 图片的文件路径
		String imagePath = UploadConstant.RESOURCES_URL + filePath;
		// 构造文件对象
		File file = new File(imagePath);

		Map<String, Object> map = new HashMap<String, Object>();
		// 如果文件存在
		if (file.exists()) {
			// 判断是否是文件
			if (file.isFile()) {
				// 删除文件
				file.delete();
				map.put("success", 0);
			}
		}
		return map;
	}

}
