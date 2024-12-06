package coffeebreak.config;

public class CloudinaryExample {
	public static void main(String[] args) {
        try {
            // Path to the image file you want to upload
            String filePath = "C:/Users/msi/Downloads/missa.jpg";

            // Upload the image using CloudinaryUtil
            String secureUrl = CloudinaryUtil.uploadImage(filePath);

            // Output the secure URL of the uploaded image
            System.out.println("Image successfully uploaded!");
            System.out.println("Secure URL: " + secureUrl);
        } catch (Exception e) {
            // Handle exceptions
            System.err.println("Error during image upload: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
