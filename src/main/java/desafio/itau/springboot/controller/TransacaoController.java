package desafio.itau.springboot.controller;

import java.time.OffsetDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import desafio.itau.springboot.model.Transacao;
import desafio.itau.springboot.service.TransacaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private static final Logger logger = LoggerFactory.getLogger(TransacaoController.class);

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<Void> adicionarTransacao(@Valid @RequestBody Transacao transacao) {
        logger.info("Recebendo nova transação: {} ", transacao);

        if (transacao.getValor() < 0) {
            logger.warn("Transação rejeitada: valor negativo {}", transacao.getValor());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        if (transacao.getDataHora().isAfter(OffsetDateTime.now())) {
            logger.warn("Transação rejeitada: data futura {}", transacao.getDataHora());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        if (transacao.getDataHora().isBefore(OffsetDateTime.MIN)) {
            logger.warn("Transação rejeitada: data/hora inválida (antes do limite mínimo permitido) {}",
                    transacao.getDataHora());

            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        transacaoService.adicionarTransacao(transacao);
        logger.info("Transação adicionada com sucesso: {} ", transacao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> limparTransacoes() {
        logger.info("Limpando todas as transações");
        transacaoService.limparTransacoes();
        logger.info("Todas as transações foram limpas com sucesso");
        return ResponseEntity.ok().build();
    }
}
