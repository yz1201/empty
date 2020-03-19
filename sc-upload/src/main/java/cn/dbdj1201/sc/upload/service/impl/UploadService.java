package cn.dbdj1201.sc.upload.service.impl;

import cn.dbdj1201.sc.upload.service.IUploadService;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-03-18 22:17
 **/
@Service
public class UploadService implements IUploadService {

    @Autowired
    private FastFileStorageClient storageClient;
    private static final List<String> CONTENT_TYPES = Arrays.asList("image/jpeg", "image/gif", "image/png", "image/bmp");
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);

    @Override
    public String upload(MultipartFile file) {
        //校验图片类型
        String originalName = file.getOriginalFilename();
        if (!CONTENT_TYPES.contains(file.getContentType())) {
            LOGGER.info("图片类型不合法： {}", originalName);
        }
        //校验图片内容
        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                LOGGER.info("图片内容不合法：{}", originalName);
                return null;
            }
            //上传图片,返回图片url进行回显
            //file.transferTo(new File("F:\\tools\\image\\" + originalName));
//            String ext = StringUtils.substringAfterLast(originalName, ".");
//            StorePath storePath = this.storageClient.uploadFile(file.getInputStream(), file.getSize(), ext, null);

            // 生成url地址，返回
//            return "http://image.sc.com/" + storePath.getFullPath();
            return "http://image.sc1.com/" + originalName;

        } catch (IOException e) {
            LOGGER.info("服务器内部错误：{}", originalName);
            e.printStackTrace();
        }
        return null;
    }
}
