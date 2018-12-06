package com.lixavier.manage.controller;

import com.lixavier.manage.result.Result;
import com.lixavier.manage.result.ResultCode;
import com.lixavier.manage.storage.StorageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("storage")
@Validated
public class StorageController {
    private final Log logger = LogFactory.getLog(StorageController.class);

    @Autowired
    private StorageService storageService;

    @PostMapping("create")
    public Result<String> create(@RequestParam("file") MultipartFile file) {
        Result<String> result = Result.newSuccess();
        try {
            String originalFilename = file.getOriginalFilename();
            String url = storageService.store(file.getInputStream(), file.getSize(), file.getContentType(), originalFilename);
            result.setDataMap(url);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

}
