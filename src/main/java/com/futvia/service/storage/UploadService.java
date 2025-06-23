package com.futvia.service.storage;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    String subirArchivo(MultipartFile archivo, String carpetaDestino);
    boolean eliminarArchivo(String nombreArchivo, String carpetaDestino);
}
