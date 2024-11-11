package org.lozunki.wavebackend.resource.controller;

import lombok.extern.slf4j.Slf4j;
import org.lozunki.wavebackend.common.ex.ServiceException;
import org.lozunki.wavebackend.common.web.Response;
import org.lozunki.wavebackend.common.web.ServiceCode;
import org.lozunki.wavebackend.resource.pojovo.UploadResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/resources")
public class ResourceController {

    @Value("${wavebackend.upload.host}")
    private String host;

    @Value("${wavebackend.upload.base-dir-name}")
    private String baseDirName;

    @Value("${wavebackend.upload.root-dir-name}")
    private String uploadRootDirName;

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");

    @Value("${wavebackend.upload.product-image.max-size}")
    private Integer articleImageMaxSize;

    @Value("${wavebackend.upload.product-image.types}")
    private List<String> articleImageValidTypes;

    private final String articleImageDirName = "product-image" + File.separator;

    @PostMapping("/upload/product/image/")
    public Response uploadArticleImage(@RequestParam("file") MultipartFile multipartFile) throws Throwable {
        if (multipartFile == null || multipartFile.isEmpty()) {
            String message = "上传文章图片失败，请选择您要上传的文件！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPLOAD_EMPTY, message);
        }

        long size = multipartFile.getSize();
        if (size > articleImageMaxSize * 1024 * 1024) {
            String message = "上传文章图片失败，不允许使用超过" + articleImageMaxSize + "MB的图片文件！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPLOAD_EXCEED_MAX_SIZE, message);
        }

        String contentType = multipartFile.getContentType();
        if (!articleImageValidTypes.contains(contentType)) {
            String message = "上传文章图片失败，请使用以下类型的图片文件：" + articleImageValidTypes;
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPLOAD_INVALID_TYPE, message);
        }

        String dirName = simpleDateFormat.format(new Date());
        File uploadBaseDir = new File(uploadRootDirName + File.separator + baseDirName);
        File articleImageDir = new File(uploadBaseDir, articleImageDirName);
        File uploadDir = new File(articleImageDir, dirName);

        if (!uploadDir.exists() && !uploadDir.mkdirs()) {
            String message = "上传文章图片失败，无法创建上传目录！";
            log.error(message);
            throw new ServiceException(ServiceCode.ERROR_UPLOAD_DIRECTORY_CREATION, message);
        }

        String originalFilename = multipartFile.getOriginalFilename();
        if (originalFilename == null || !originalFilename.contains(".")) {
            String message = "上传文章图片失败，文件名无效！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPLOAD_INVALID_NAME, message);
        }

        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID() + suffix;
        File newFile = new File(uploadDir, newFileName);

        try {
            multipartFile.transferTo(newFile);
        } catch (IOException e) {
            String message = "上传文章图片失败，文件保存失败！";
            log.error(message, e);
            throw new ServiceException(ServiceCode.ERROR_UPLOAD_SAVE, message);
        }

        String url = new StringBuilder()
                .append(host.endsWith("/") ? host : host + "/")
                .append(baseDirName.endsWith("/") ? baseDirName : baseDirName + "/")
                .append(articleImageDirName)
                .append(dirName)
                .append(newFileName)
                .toString();

        UploadResult uploadResult = new UploadResult();
        uploadResult.setUrl(url);
        uploadResult.setFileSize(size);
        uploadResult.setContentType(contentType);
        uploadResult.setFileName(newFileName);
        return Response.ok(uploadResult);
    }
}
