package iyp.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    int addImageByPack(MultipartFile file, int userId) throws Exception;

    int addImageByPack(String img,int userId);


    String getImageByPack(int userId);

}
