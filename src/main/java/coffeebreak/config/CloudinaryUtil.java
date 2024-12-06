package coffeebreak.config;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class CloudinaryUtil {

    private static final Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
    		"cloud_name", "dxfgttrup",
            "api_key", "835172715959264",
            "api_secret", "YMvDEevIKQCbT27Rjny79UxT4NU"
    ));

    public static String uploadImage(String filePath) throws IOException {
        File file = new File(filePath);
        Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        return (String) uploadResult.get("secure_url");
    }
}
