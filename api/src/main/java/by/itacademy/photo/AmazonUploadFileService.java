package by.itacademy.photo;

public interface AmazonUploadFileService {

    String uploadFile(byte[] image, Long userId);
}
