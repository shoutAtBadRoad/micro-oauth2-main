package com.post.db.upservice;

import com.post.db.dao.PackageDao;
import com.post.db.entity.ImageByte;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;

/**
 * @author JYP
 * @date 2021/2/26
 **/
@Service
public class ImageServiceImpl implements ImageService{

    @Resource
    private PackageDao packageDao;

    /*
    以文件形式上传图片
     */
    @Override
    public int addImageByPack(MultipartFile file, String packId) throws Exception {
        byte[] data = file.getBytes();
//        FileInputStream is = new FileInputStream("C:\\Users\\56446\\Desktop\\pack.jpg");
//        int size = is.available();
//        byte[] bytes = new byte[size];
//        is.read(bytes);
//        is.close();
        return packageDao.addImage(packId, data);
    }

    /*
    以base64编码字符流上传图片
     */
    @Override
    public int addImageByPack(String img, String packId) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(img);
        return packageDao.addImage(packId,bytes);
    }

    /*
    将图片字节流读出后转为base64编码字符串返回
     */
    @Override
    public String getImageByPack(String packId) {
        ImageByte bytes = packageDao.getImage(packId);
        Base64.Encoder encoder = Base64.getEncoder();
        String img = encoder.encodeToString(bytes.getImgBytes());
        return img;
    }
}
