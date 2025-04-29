package desafio.itau.springboot.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import desafio.itau.springboot.model.Transacao;
import desafio.itau.springboot.service.TransacaoService;

public class TransacaoControllerTest {
    private final TransacaoService transacaoService = mock(TransacaoService.class);
    private final TransacaoController transacaoController = new TransacaoController(transacaoService);

    @Test
    void deveAdicionarTransacaoComSucesso() {
        Transacao transacao = new Transacao(100.0, OffsetDateTime.now().minusSeconds(30));
        ResponseEntity<Void> response = transacaoController.adicionarTransacao(transacao);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(transacaoService, times(1)).adicionarTransacao(transacao);
    }

    @Test
    void deveAdicionarTransacaoComValorZero() {
        Transacao transacao = new Transacao(0.0, OffsetDateTime.now().minusSeconds(30));
        ResponseEntity<Void> response = transacaoController.adicionarTransacao(transacao);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(transacaoService, times(1)).adicionarTransacao(transacao);
    }

    @Test
    void deveAdicionarTransacaoComValorExtremo() {
        Transacao transacao = new Transacao(Double.MAX_VALUE, OffsetDateTime.now().minusSeconds(30));
        ResponseEntity<Void> response = transacaoController.adicionarTransacao(transacao);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(transacaoService, times(1)).adicionarTransacao(transacao);
    }

    @Test
    void deveRetornarUnprocessableEntityParaValorNegativo() {
        Transacao transacao = new Transacao(-100.0, OffsetDateTime.now().minusSeconds(30));
        ResponseEntity<Void> response = transacaoController.adicionarTransacao(transacao);
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        verify(transacaoService, never()).adicionarTransacao(any());
    }

    @Test
    void deveRetornarUnprocessableEntityParaDataFutura() {
        Transacao transacao = new Transacao(100.0, OffsetDateTime.now().plusSeconds(30));

        ResponseEntity<Void> response = transacaoController.adicionarTransacao(transacao);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        verify(transacaoService, never()).adicionarTransacao(any());
    }

    @Test
    void deveRetornarBadRequestParaJsonInvalido() {
    }
}