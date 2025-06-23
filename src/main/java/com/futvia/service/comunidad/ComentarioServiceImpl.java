package com.futvia.service.comunidad;

import com.futvia.dto.comunidad.ComentarioDTO;
import com.futvia.dto.comunidad.ComentarioFormDTO;
import com.futvia.model.auth.Usuario;
import com.futvia.model.comunidad.Comentario;
import com.futvia.model.comunidad.EstadoModeracion;
import com.futvia.model.comunidad.Publicacion;
import com.futvia.repository.auth.UsuarioRepository;
import com.futvia.repository.comunidad.ComentarioRepository;
import com.futvia.repository.comunidad.PublicacionRepository;
import com.futvia.service.comunidad.ComentarioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComentarioServiceImpl implements ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final PublicacionRepository publicacionRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ComentarioDTO> listarPorPublicacion(Long publicacionId) {
        return comentarioRepository.findByPublicacionId(publicacionId).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public ComentarioDTO crear(ComentarioFormDTO dto, Long autorId) {
        Comentario comentario = new Comentario();
        comentario.setContenido(dto.getContenido());
        comentario.setFecha(LocalDateTime.now());
        comentario.setEstadoModeracion(EstadoModeracion.PENDIENTE);

        Usuario autor = usuarioRepository.findById(autorId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        comentario.setAutor(autor);

        Publicacion publicacion = publicacionRepository.findById(dto.getPublicacionId())
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));
        comentario.setPublicacion(publicacion);

        return convertirADTO(comentarioRepository.save(comentario));
    }

    @Override
    public ComentarioDTO editar(Long id, ComentarioFormDTO dto) {
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));

        comentario.setContenido(dto.getContenido());
        comentario.setFecha(LocalDateTime.now());
        return convertirADTO(comentarioRepository.save(comentario));
    }

    @Override
    public void eliminar(Long id) {
        if (!comentarioRepository.existsById(id)) {
            throw new RuntimeException("Comentario no encontrado");
        }
        comentarioRepository.deleteById(id);
    }

    @Override
    public ComentarioDTO buscarPorId(Long id) {
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));
        return convertirADTO(comentario);
    }

    private ComentarioDTO convertirADTO(Comentario comentario) {
        ComentarioDTO dto = modelMapper.map(comentario, ComentarioDTO.class);
        dto.setAutorNombreCompleto(comentario.getAutor().getNombre()); // Puedes concatenar nombre + apellido si lo deseas
        dto.setPublicacionId(comentario.getPublicacion().getId());
        return dto;
    }
}
