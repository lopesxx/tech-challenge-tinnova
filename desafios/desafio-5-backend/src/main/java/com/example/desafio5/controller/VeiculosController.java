package com.example.desafio5.controller;

import com.example.desafio5.dto.VeiculoDTO;
import com.example.desafio5.model.Marca;
import com.example.desafio5.model.Veiculo;
import com.example.desafio5.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/veiculos")
public class VeiculosController {

  private final VeiculoService veiculoService;

  public VeiculosController(VeiculoService veiculoService) {
    this.veiculoService = veiculoService;
  }

  @GetMapping
  @Description("Retorna todos os veículos de acordo com os parâmetros passados")
  public List<Veiculo> getAllVeiculos(@RequestParam(required = false) String marca,
                                      @RequestParam(required = false) Integer ano,
                                      @RequestParam(required = false) String cor) {
    return veiculoService.searchVeiculos(cor, marca, ano);
  }
  @Operation(summary = "Get a book by its id")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Found the book",
      content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = Veiculo.class)) }),
    @ApiResponse(responseCode = "400", description = "Invalid id supplied",
      content = @Content),
    @ApiResponse(responseCode = "404", description = "Book not found",
      content = @Content) })
  @GetMapping("/{id}")
  public Veiculo getVeiculoById(@PathVariable Long id) {
    return veiculoService.findById(id);
  }

  @PostMapping
  public Veiculo createVeiculo(@RequestBody @Valid VeiculoDTO veiculoToBeCreated) {
    return veiculoService.create(veiculoToBeCreated);
  }

  @PutMapping("/{id}")
  public Veiculo updateVeiculo(@PathVariable Long id, @RequestBody @Valid VeiculoDTO veiculoUpdated) {
    return veiculoService.update(id, veiculoUpdated);
  }

  @PatchMapping("/{id}")
  public Veiculo patchVeiculo(@PathVariable Long id, @RequestBody VeiculoDTO veiculoUpdates) {
    return veiculoService.update(id, veiculoUpdates);
  }

  @DeleteMapping("/{id}")
  public void deleteVeiculo(@PathVariable Long id) {
    veiculoService.delete(id);
  }

  @GetMapping("/marcas")
  public List<Marca> getMarcas() {
    return veiculoService.getMarcas();
  }

  @PostMapping("/marcas")
  public Marca insertMarca(@RequestBody String nome) {
    return veiculoService.createMarca(nome);
  }
}
