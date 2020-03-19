package cn.dbdj1201.sc.upload.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author tyz1201
 * @datetime 2020-03-18 22:16
 **/
public interface IUploadService {

    /**
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
