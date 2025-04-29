package desafio.itau.springboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import desafio.itau.springboot.dto.EstatisticaDTO;
import desafio.itau.springboot.service.TransacaoService;

@RestController
@RequestMapping("/estatistica")
public class EstatisticasController {

    private final TransacaoService transacaoService;

    public EstatisticasController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping
    public ResponseEntity<EstatisticaDTO> obterEstatisticas() {
        EstatisticaDTO estatisticas = transacaoService.calcularEstatisticas();
        return ResponseEntity.ok(estatisticas);
    }
}
