package com.futvia.service.equipo;

import com.futvia.dto.equipo.JugadorDTO;
import com.futvia.dto.equipo.JugadorFormDTO;
import com.futvia.model.equipo.Equipo;
import com.futvia.model.equipo.Jugador;
import com.futvia.repository.equipo.EquipoRepository;
import com.futvia.repository.equipo.JugadorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JugadorServiceImpl implements JugadorService {

    private final JugadorRepository jugadorRepository;
    private final EquipoRepository equipoRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<JugadorDTO> listarTodos() {
        return jugadorRepository.findAll().stream()
                .map(jugador -> modelMapper.map(jugador, JugadorDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<JugadorDTO> listarPorEquipo(Long equipoId) {
        return jugadorRepository.findByEquipoId(equipoId).stream()
                .map(jugador -> modelMapper.map(jugador, JugadorDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public JugadorDTO buscarPorId(Long id) {
        Jugador jugador = jugadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Jugador no encontrado"));
        return modelMapper.map(jugador, JugadorDTO.class);
    }

    @Override
    public JugadorDTO crear(JugadorFormDTO dto) {
        Equipo equipo = equipoRepository.findById(dto.getEquipoId())
                .orElseThrow(() -> new EntityNotFoundException("Equipo no encontrado"));

        Jugador jugador = modelMapper.map(dto, Jugador.class);
        jugador.setEquipo(equipo);

        jugador = jugadorRepository.save(jugador);
        return modelMapper.map(jugador, JugadorDTO.class);
    }

    @Override
    public JugadorDTO editar(Long id, JugadorFormDTO dto) {
        Jugador jugador = jugadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Jugador no encontrado"));

        modelMapper.map(dto, jugador);

        if (dto.getEquipoId() != null) {
            Equipo equipo = equipoRepository.findById(dto.getEquipoId())
                    .orElseThrow(() -> new EntityNotFoundException("Equipo no encontrado"));
            jugador.setEquipo(equipo);
        }

        jugador = jugadorRepository.save(jugador);
        return modelMapper.map(jugador, JugadorDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        jugadorRepository.deleteById(id);
    }
}
