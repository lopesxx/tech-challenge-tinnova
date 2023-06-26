package com.example.desafio5.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class VeiculoDTO {
  @NotNull
  @NotBlank
  private String marca;

  @NotNull
  @NotBlank
  private String cor;

  @NotNull
  private Integer ano;

  @NotNull
  @NotBlank
  private String descricao;

  @NotNull
  private Boolean vendido;

  public String getMarca() {
    return marca;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }

  public String getCor() {
    return cor;
  }

  public void setCor(String cor) {
    this.cor = cor;
  }

  public Integer getAno() {
    return ano;
  }

  public void setAno(Integer ano) {
    this.ano = ano;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Boolean getVendido() {
    return vendido;
  }

  public void setVendido(Boolean vendido) {
    this.vendido = vendido;
  }
}

