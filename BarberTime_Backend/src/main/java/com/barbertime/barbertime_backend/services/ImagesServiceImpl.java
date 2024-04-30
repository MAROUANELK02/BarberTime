package com.barbertime.barbertime_backend.services;

import com.barbertime.barbertime_backend.entities.BarberShop;
import com.barbertime.barbertime_backend.entities.FileData;
import com.barbertime.barbertime_backend.repositories.FileDataRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ImagesServiceImpl implements ImagesService {
    private FileDataRepository fileDataRepository;

    @Override
    public byte[] downloadImageFromStorage(Long id) throws IOException {
        log.info("Downloading image from storage: ");
        Optional<FileData> fileData = fileDataRepository.findById(id);
        if (fileData.isEmpty()) {
            log.error("File not found: ");
            return new byte[0];
        }
        String filePath = fileData.get().getFilePath();
        return Files.readAllBytes(new File(filePath).toPath());
    }

    @Override
    public void uploadImageToStorage(BarberShop barberShop, MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String classpathResource = "static/images/" + fileName;
        File newFile = new File(getClass().getClassLoader().getResource(classpathResource).getFile());

        file.transferTo(newFile);

        fileDataRepository.save(FileData.builder()
                .name(fileName)
                .type(file.getContentType())
                .filePath(newFile.getPath())
                .barberShop(barberShop)
                .build());
    }
}
