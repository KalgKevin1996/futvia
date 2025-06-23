package com.futvia.service.equipo;

import com.futvia.dto.equipo.EquipoDTO;
import com.futvia.dto.equipo.EquipoFormDTO;
import com.futvia.model.equipo.Equipo;
import com.futvia.model.liga.CategoriaCompetencia;
import com.futvia.model.liga.Liga;
import com.futvia.repository.equipo.EquipoRepository;
import com.futvia.repository.liga.CategoriaCompetenciaRepository;
import com.futvia.repository.liga.LigaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipoServiceImpl implements EquipoService {

    private final EquipoRepository equipoRepository;
    private final LigaRepository ligaRepository;
    private final CategoriaCompetenciaRepository categoriaCompetenciaRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<EquipoDTO> listarTodos() {
        return equipoRepository.findAll().stream()
                .map(e -> modelMapper.map(e, EquipoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EquipoDTO buscarPorId(Long id) {
        Equipo equipo = equipoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipo no encontrado con id " + id));
        return modelMapper.map(equipo, EquipoDTO.class);
    }

    @Override
    public EquipoDTO crear(EquipoFormDTO dto) {
        Equipo equipo = new Equipo();
        equipo.setNombre(dto.getNombre());
        equipo.setEscudoUrl(dto.getEscudoUrl());
        equipo.setColorPrimario(dto.getColorPrimario());
        equipo.setContacto(dto.getContacto());
        equipo.setEncargado(dto.getEncargado());

        Liga liga = ligaRepository.findById(dto.getLigaId())
                .orElseThrow(() -> new EntityNotFoundException("Liga no encontrada con id " + dto.getLigaId()));
        equipo.setLiga(liga);

        CategoriaCompetencia categoria = categoriaCompetenciaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con id " + dto.getCategoriaId()));
        equipo.setCategoria(categoria);

        equipo = equipoRepository.save(equipo);
        return modelMapper.map(equipo, EquipoDTO.class);
    }

    @Override
    public EquipoDTO editar(Long id, EquipoFormDTO dto) {
        Equipo equipo = equipoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipo no encontrado con id " + id));

        equipo.setNombre(dto.getNombre());
        equipo.setEscudoUrl(dto.getEscudoUrl());
        equipo.setColorPrimario(dto.getColorPrimario());
        equipo.setContacto(dto.getContacto());
        equipo.setEncargado(dto.getEncargado());

        Liga liga = ligaRepository.findById(dto.getLigaId())
                .orElseThrow(() -> new EntityNotFoundException("Liga no encontrada con id " + dto.getLigaId()));
        equipo.setLiga(liga);

        CategoriaCompetencia categoria = categoriaCompetenciaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con id " + dto.getCategoriaId()));
        equipo.setCategoria(categoria);

        equipo = equipoRepository.save(equipo);
        return modelMapper.map(equipo, EquipoDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        if (!equipoRepository.existsById(id)) {
            throw new EntityNotFoundException("Equipo no encontrado con id " + id);
        }
        equipoRepository.deleteById(id);
    }
}
