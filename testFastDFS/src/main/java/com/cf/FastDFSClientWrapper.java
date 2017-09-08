package com.cf;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;

@Component
public class FastDFSClientWrapper {

    @Autowired
    private FastFileStorageClient storageClient;
    
   public String uploadFile(MultipartFile file) throws IOException {
       StorePath storePath = storageClient.uploadFile((InputStream)file.getInputStream(),file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()),null);
       return getResAccessUrl(storePath);
   }
   
   // 封装文件完整URL地址
   private String getResAccessUrl(StorePath storePath) {
       String fileUrl = "http://192.168.7.73:8000" + "/" + storePath.getFullPath();
       return fileUrl;
   }
}
