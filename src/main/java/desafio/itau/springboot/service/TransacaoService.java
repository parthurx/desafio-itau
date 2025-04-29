package desafio.itau.springboot.service;

import org.springframework.stereotype.Service;

import desafio.itau.springboot.dto.EstatisticaDTO;
import desafio.itau.springboot.model.Transacao;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class TransacaoService {
    private final List<Transacao> transacoes = new ArrayList<>();

    public void adicionarTransacao(Transacao transacao) {
        transacoes.add(transacao);
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void limparTransacoes() {
        transacoes.clear();
    }

    public EstatisticaDTO calcularEstatisticas() {
        OffsetDateTime agora = OffsetDateTime.now();
        OffsetDateTime umMinutoAtras = agora.minusSeconds(60);

        DoubleSummaryStatistics statistics = transacoes.stream()
                .filter(t -> t.getDataHora().isAfter(umMinutoAtras))
                .mapToDouble(Transacao::getValor)
                .summaryStatistics();

        return new EstatisticaDTO(statistics);
    }

}
