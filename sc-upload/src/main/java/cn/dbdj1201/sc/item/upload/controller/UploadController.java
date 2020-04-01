package cn.dbdj1201.sc.item.upload.controller;

import cn.dbdj1201.sc.item.upload.service.IUploadService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author tyz1201
 * @datetime 2020-03-18 22:14
 **/
@Controller
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private IUploadService uploadService;

    @PostMapping("image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        String url = uploadService.upload(file);
        if (StringUtils.isBlank(url))
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(url);
    }
}
