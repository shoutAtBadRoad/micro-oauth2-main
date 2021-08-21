import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * @author JYP
 * @date 2021/8/18
 **/

public class iyp {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\56446\\Desktop");
        try {
            FileInputStream is = new FileInputStream("C:\\Users\\56446\\Desktop\\bt.txt");
            int i = is.available();
            byte[] bytes = new byte[i];
            int read = is.read(bytes);
            System.out.println(read);
            System.out.println(i);
            System.out.println(Arrays.toString(bytes));
            FileOutputStream outputStream = new FileOutputStream("C:\\Users\\56446\\Desktop\\bt1.txt");
            outputStream.write(bytes);

            File[] files = file.listFiles();
            for (File file1 : files) {
                System.out.println(file1.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
