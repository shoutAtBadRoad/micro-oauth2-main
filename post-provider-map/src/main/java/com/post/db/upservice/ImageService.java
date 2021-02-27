package com.post.db.upservice;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;

public interface ImageService {

    int addImageByPack(MultipartFile file, String packId) throws Exception;

    int addImageByPack(String img,String packId);


    String getImageByPack(String packId);

}
