package coffeebreak.config;

public class CloudinaryExample {
	public static void main(String[] args) {
        try {
            String filePath = "C:/Users/msi/Downloads/missa.jpg";

            String secureUrl = CloudinaryUtil.uploadImage(filePath);

            System.out.println("Image successfully uploaded!");
            System.out.println("Secure URL: " + secureUrl);
        } catch (Exception e) {
            System.err.println("Error during image upload: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
