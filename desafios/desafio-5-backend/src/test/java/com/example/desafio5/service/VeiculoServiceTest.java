package com.example.desafio5.service;

import com.example.desafio5.dto.VeiculoDTO;
import com.example.desafio5.dto.exception.VeiculoNotFound;
import com.example.desafio5.dto.specification.VeiculoSpecification;
import com.example.desafio5.model.Veiculo;
import com.example.desafio5.repository.VeiculoRepository;
import com.example.desafio5.util.VeiculoMapper;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VeiculoServiceTest {

  @Mock
  private VeiculoRepository veiculoRepository;

  @Mock
  private VeiculoMapper veiculoMapper;

  @InjectMocks
  private VeiculoService veiculoService;

  private static final Long id = 1L;

  @Test
  public void shouldReturnVeiculosFiltrados() {
    String cor = "azul";
    String marca = "Ford";
    Integer ano = 2020;
    List<Veiculo> expectedVeiculos = Arrays.asList(new Veiculo(), new Veiculo());

    Specification<Veiculo> expectedSpecification = Specification.where(null);

    expectedSpecification
      .and(VeiculoSpecification.porCor(cor))
      .and(VeiculoSpecification.porMarca(marca))
      .and(VeiculoSpecification.porAno(ano));

    when(veiculoRepository.findAll(any(Specification.class))).thenReturn(expectedVeiculos);

    List<Veiculo> result = veiculoService.searchVeiculos(cor, marca, ano);

    assertEquals(expectedVeiculos, result);
  }

  @Test
  public void shouldReturnVeiculo() {
    Veiculo expectedVeiculo = new Veiculo();

    when(veiculoRepository.findById(id)).thenReturn(Optional.of(expectedVeiculo));

    Veiculo result = veiculoService.findById(id);

    assertEquals(expectedVeiculo, result);
  }

  @Test
  public void shouldThrowVeiculoNotFoundOnFindById() {
    when(veiculoRepository.findById(id)).thenReturn(Optional.empty());

    assertThrows(VeiculoNotFound.class, () -> veiculoService.findById(id));
  }

  @Test
  public void shouldCreateVeiculo() {
    VeiculoDTO veiculoDTO = new VeiculoDTO();
    Veiculo expectedVeiculo = new Veiculo();

    when(veiculoMapper.toEntity(veiculoDTO)).thenReturn(expectedVeiculo);

    when(veiculoRepository.save(expectedVeiculo)).thenReturn(expectedVeiculo);

    Veiculo result = veiculoService.create(veiculoDTO);

    assertEquals(expectedVeiculo, result);
    assertNotNull(result.getCreated());
  }

  @Test
  public void shouldUpdateVeiculo() {
    VeiculoDTO veiculoDTO = new VeiculoDTO();
    Veiculo veiculo = new Veiculo();
    LocalDateTime initialUpdated = veiculo.getUpdated();

    when(veiculoRepository.findById(id)).thenReturn(Optional.of(veiculo));
    when(veiculoRepository.save(veiculo)).thenReturn(veiculo);

    Veiculo result = veiculoService.update(id, veiculoDTO);

    assertEquals(veiculo, result);
    assertNotEquals(initialUpdated, result.getUpdated());
  }

  @Test
  public void shouldThrowVeiculoNotFoundOnUpdate() {
    VeiculoDTO veiculoDTO = new VeiculoDTO();

    when(veiculoRepository.findById(id)).thenReturn(Optional.empty());

    assertThrows(VeiculoNotFound.class, () -> veiculoService.update(id, veiculoDTO));
  }

  @Test
  public void shouldDeleteVeiculo() {
    Veiculo veiculo = new Veiculo();

    when(veiculoRepository.findById(id)).thenReturn(Optional.of(veiculo));

    veiculoService.delete(id);

    verify(veiculoRepository, times(1)).delete(veiculo);
  }

  @Test
  public void shouldThrowVeiculoNotFoundOnDelete() {
    when(veiculoRepository.findById(id)).thenReturn(Optional.empty());
    assertThrows(VeiculoNotFound.class, () -> veiculoService.delete(id));
  }
}

