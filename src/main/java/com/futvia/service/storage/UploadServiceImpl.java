package com.futvia.service.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

@Service
@Slf4j
public class UploadServiceImpl implements UploadService {

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads";

    @Override
    public String subirArchivo(MultipartFile archivo, String carpetaDestino) {
        try {
            Path carpetaPath = Paths.get(UPLOAD_DIR, carpetaDestino);
            Files.createDirectories(carpetaPath);

            String nombreArchivo = System.currentTimeMillis() + "_" + archivo.getOriginalFilename();
            Path destino = carpetaPath.resolve(nombreArchivo);

            archivo.transferTo(destino);

            log.info("Archivo guardado en: {}", destino.toString());

            // Ruta para mostrar en navegador
            return "/uploads/" + carpetaDestino + "/" + nombreArchivo;

        } catch (IOException e) {
            log.error("Error subiendo archivo", e);
            throw new RuntimeException("No se pudo subir el archivo");
        }
    }

    @Override
    public boolean eliminarArchivo(String nombreArchivo, String carpetaDestino) {
        Path ruta = Paths.get(UPLOAD_DIR, carpetaDestino, nombreArchivo);
        try {
            return Files.deleteIfExists(ruta);
        } catch (IOException e) {
            log.error("Error al eliminar archivo: {}", nombreArchivo, e);
            return false;
        }
    }
}
