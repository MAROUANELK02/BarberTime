package com.barbertime.barbertime_backend.services;

import com.barbertime.barbertime_backend.entities.BarberShop;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImagesService {
    byte[] downloadImageFromStorage(Long id) throws IOException;
    void uploadImageToStorage(BarberShop barberShop, MultipartFile file) throws IOException;
}
