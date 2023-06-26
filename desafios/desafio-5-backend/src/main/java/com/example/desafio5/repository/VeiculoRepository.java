package com.example.desafio5.repository;

import com.example.desafio5.model.Veiculo;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
  List<Veiculo> findAll(Specification<Veiculo> specification);
}
