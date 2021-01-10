package by.itacademy.controller.request;

import by.itacademy.domain.User;
import by.itacademy.exception.RepositoryException;
import by.itacademy.photo.AmazonUploadFileService;
import by.itacademy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/rest/photos/user")
@RequiredArgsConstructor
public class UserPhotoController {

    private final UserRepository userRepository;
    private final AmazonUploadFileService amazonUploadFileService;

    @PostMapping("/{id}")
    public ResponseEntity<Map<Object, Object>> uploadUserPhoto(@PathVariable Long id,
                                                               @RequestBody MultipartFile file) throws IOException, RepositoryException {

        User user = userRepository.findById(id);
        byte[] imageBytes = file.getBytes();
        String imageLink = amazonUploadFileService.uploadFile(imageBytes, id);

        user.setPhotoLink(imageLink);
        userRepository.save(user);

        return new ResponseEntity<>(Collections.singletonMap("imageLink", imageLink), HttpStatus.CREATED);
    }
}
