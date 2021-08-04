package iyp.service.impl;

import iyp.entity.ImageByte;
import iyp.entity.User;
import iyp.mapper.UserMapper;
import iyp.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Base64;

/**
 * @author JYP
 * @date 2021/2/26
 **/
@Service
public class ImageServiceImpl implements ImageService {

    @Resource
    private UserMapper userMapper;

    /*
    以文件形式上传图片
     */
    @Override
    public int addImageByPack(MultipartFile file, int userId) throws Exception {
        byte[] data = file.getBytes();
//        FileInputStream is = new FileInputStream("C:\\Users\\56446\\Desktop\\pack.jpg");
//        int size = is.available();
//        byte[] bytes = new byte[size];
//        is.read(bytes);
//        is.close();
        User user = new User();
        user.setId(userId);
        user.setImage(data);
        return userMapper.updateById(user);
    }

    /*
    以base64编码字符流上传图片
     */
    @Override
    public int addImageByPack(String img, int userId) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(img);
//        return packageDao.addImage(packId,bytes);
        return 1;
    }

    /*
    将图片字节流读出后转为base64编码字符串返回
     */
    @Override
    public String getImageByPack(int userId) {
        ImageByte bytes = new ImageByte(userMapper.selectById(userId).getImage());
        Base64.Encoder encoder = Base64.getEncoder();
        String img = encoder.encodeToString(bytes.getImgBytes());
        return img;
    }
}
