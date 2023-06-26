package com.example.desafio5.util;

import com.example.desafio5.dto.VeiculoDTO;
import com.example.desafio5.model.Veiculo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface VeiculoMapper {
  Veiculo toEntity(VeiculoDTO veiculoDTO);

}
