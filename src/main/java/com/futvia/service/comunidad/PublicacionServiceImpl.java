package com.futvia.service.comunidad;

import com.futvia.dto.comunidad.PublicacionDTO;
import com.futvia.dto.comunidad.PublicacionFormDTO;
import com.futvia.model.auth.Usuario;
import com.futvia.model.comunidad.CategoriaPublicacion;
import com.futvia.model.comunidad.EstadoModeracion;
import com.futvia.model.comunidad.Publicacion;
import com.futvia.repository.auth.UsuarioRepository;
import com.futvia.repository.comunidad.CategoriaPublicacionRepository;
import com.futvia.repository.comunidad.PublicacionRepository;
import com.futvia.service.comunidad.PublicacionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicacionServiceImpl implements PublicacionService {

    private final PublicacionRepository publicacionRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaPublicacionRepository categoriaRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PublicacionDTO> listarTodas() {
        return publicacionRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PublicacionDTO> listarVisibles() {
        return publicacionRepository.findByVisibleTrue().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PublicacionDTO> listarPorAutor(Long autorId) {
        return publicacionRepository.findByAutorId(autorId).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PublicacionDTO> listarPorCategoria(Long categoriaId) {
        return publicacionRepository.findByCategoriaId(categoriaId).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public PublicacionDTO crear(PublicacionFormDTO dto) {
        // Simulamos un usuario autor por ahora
        Usuario autor = usuarioRepository.findById(1L) // ← puedes adaptar esto con el usuario logueado
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        CategoriaPublicacion categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada"));

        Publicacion publicacion = Publicacion.builder()
                .titulo(dto.getTitulo())
                .contenido(dto.getContenido())
                .imagenUrl(dto.getImagenUrl())
                .fecha(LocalDateTime.now())
                .visible(dto.isVisible())
                .estadoModeracion(EstadoModeracion.PENDIENTE)
                .autor(autor)
                .categoria(categoria)
                .build();

        return convertirADTO(publicacionRepository.save(publicacion));
    }

    @Override
    public PublicacionDTO editar(Long id, PublicacionFormDTO dto) {
        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Publicación no encontrada"));

        CategoriaPublicacion categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada"));

        publicacion.setTitulo(dto.getTitulo());
        publicacion.setContenido(dto.getContenido());
        publicacion.setImagenUrl(dto.getImagenUrl());
        publicacion.setVisible(dto.isVisible());
        publicacion.setCategoria(categoria);

        return convertirADTO(publicacionRepository.save(publicacion));
    }

    @Override
    public void eliminar(Long id) {
        publicacionRepository.deleteById(id);
    }

    @Override
    public PublicacionDTO buscarPorId(Long id) {
        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Publicación no encontrada"));
        return convertirADTO(publicacion);
    }

    private PublicacionDTO convertirADTO(Publicacion publicacion) {
        return PublicacionDTO.builder()
                .id(publicacion.getId())
                .titulo(publicacion.getTitulo())
                .contenido(publicacion.getContenido())
                .imagenUrl(publicacion.getImagenUrl())
                .fecha(publicacion.getFecha())
                .visible(publicacion.isVisible())
                .estadoModeracion(publicacion.getEstadoModeracion())
                .autorNombreCompleto(publicacion.getAutor().getNombre() + " " + publicacion.getAutor().getApellido())
                .categoriaNombre(publicacion.getCategoria().getNombre())
                .build();
    }
}
