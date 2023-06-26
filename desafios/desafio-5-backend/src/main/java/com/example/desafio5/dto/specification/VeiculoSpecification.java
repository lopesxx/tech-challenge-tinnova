package com.example.desafio5.dto.specification;

import com.example.desafio5.model.Veiculo;
import org.springframework.data.jpa.domain.Specification;

public class VeiculoSpecification {

  private VeiculoSpecification(){}

  public static Specification<Veiculo> porCor(String cor) {
    return (root, query, builder) ->
      builder.equal(root.get("cor"), cor);
  }

  public static Specification<Veiculo> porMarca(String marca) {
    return (root, query, builder) ->
      builder.equal(root.get("marca"), marca);
  }

  public static Specification<Veiculo> porAno(Integer ano) {
    return (root, query, builder) ->
      builder.equal(root.get("ano"), ano);
  }

}
