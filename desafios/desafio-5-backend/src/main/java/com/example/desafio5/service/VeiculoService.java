package com.example.desafio5.service;

import com.example.desafio5.dto.VeiculoDTO;
import com.example.desafio5.dto.exception.VeiculoNotFound;
import com.example.desafio5.dto.specification.VeiculoSpecification;
import com.example.desafio5.model.Marca;
import com.example.desafio5.model.Veiculo;
import com.example.desafio5.repository.MarcaRepository;
import com.example.desafio5.repository.VeiculoRepository;
import com.example.desafio5.util.VeiculoMapper;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {

  private final VeiculoRepository veiculoRepository;
  private final VeiculoMapper veiculoMapper;
  private final MarcaRepository marcaRepository;

  public VeiculoService(VeiculoRepository veiculoRepository,
                        VeiculoMapper veiculoMapper,
                        MarcaRepository marcaRepository) {
    this.veiculoRepository = veiculoRepository;
    this.veiculoMapper = veiculoMapper;
    this.marcaRepository = marcaRepository;
  }

  public List<Veiculo> searchVeiculos(String cor, String marca, Integer ano) {
    Specification<Veiculo> specification = Specification.where(null);

    if (cor != null) {
      specification = specification.and(VeiculoSpecification.porCor(cor));
    }

    if (marca != null) {
      specification = specification.and(VeiculoSpecification.porMarca(marca));
    }

    if (ano != null && ano != 0) {
      specification = specification.and(VeiculoSpecification.porAno(ano));
    }

    return veiculoRepository.findAll(specification);
  }

  public Veiculo findById(Long id) {
    return veiculoRepository.findById(id).orElseThrow(VeiculoNotFound::new);
  }

  public Veiculo create(VeiculoDTO veiculoToBeCreated) {
    Veiculo veiculo = veiculoMapper.toEntity(veiculoToBeCreated);
    veiculo.setCreated(LocalDateTime.now());
    return veiculoRepository.save(veiculo);
  }

  public Veiculo update(Long id, VeiculoDTO veiculoUpdated) {
    Veiculo veiculo = veiculoRepository.findById(id).orElseThrow(VeiculoNotFound::new);

    setVeiculoFields(veiculo, veiculoUpdated);

    return veiculoRepository.save(veiculo);
  }

  private static void setVeiculoFields(Veiculo veiculo, VeiculoDTO veiculoUpdated) {
    veiculo.setMarca(veiculoUpdated.getMarca() != null ? veiculoUpdated.getMarca() : veiculo.getMarca());
    veiculo.setCor(veiculoUpdated.getCor() != null ? veiculoUpdated.getCor() : veiculo.getCor());
    veiculo.setAno(veiculoUpdated.getAno() != null ? veiculoUpdated.getAno() : veiculo.getAno());
    veiculo.setDescricao(veiculoUpdated.getDescricao() != null ? veiculoUpdated.getDescricao() : veiculo.getDescricao());
    veiculo.setVendido(veiculoUpdated.getVendido() != null ? veiculoUpdated.getVendido() : veiculo.getVendido());
    veiculo.setUpdated(LocalDateTime.now());
  }

  public void delete(Long id) {
    Veiculo veiculo = veiculoRepository.findById(id).orElseThrow(VeiculoNotFound::new);
    veiculoRepository.delete(veiculo);
  }

  public List<Marca> getMarcas() {
    return marcaRepository.findAll();
  }

  public Marca createMarca(String nome) {
    Marca marca = new Marca();
    marca.setNome(nome);
    return marcaRepository.save(marca);
  }
}
