/**
 * Created by kleme on 08-Oct-17.
 */
import java.io.FileInputStream;
import java.security.MessageDigest;



public class Sum {
    public static String compute(String path, String algorithm) throws Exception{

        MessageDigest md = MessageDigest.getInstance(algorithm);

        FileInputStream fis =  new FileInputStream(path);

        byte[] pathBytes = new byte[1024];

        int nread = 0;

        while ((nread = fis.read(pathBytes)) != -1){
            md.update(pathBytes,0, nread);
        }

        byte[] mdbytes = md.digest();

        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < mdbytes.length; i++) {
            sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();


    }

    public static void main(String[] args) throws Exception {
        String pot = "C:/Users/kleme/Downloads/jre-8u144-windows-x64.exe";

        System.out.println("SHA1 " + compute(pot,"SHA-1"));
        System.out.println("MD5 " + compute(pot,"MD5"));
        System.out.println("SHA256 " + compute(pot,"SHA-256"));
    }
}
