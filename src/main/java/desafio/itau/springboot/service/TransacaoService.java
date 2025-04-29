package desafio.itau.springboot.service;

import org.springframework.stereotype.Service;

import desafio.itau.springboot.dto.EstatisticaDTO;
import desafio.itau.springboot.model.Transacao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class TransacaoService {
    private static final Logger logger = LoggerFactory.getLogger(TransacaoService.class);
    private final List<Transacao> transacoes = new ArrayList<>();

    public void adicionarTransacao(Transacao transacao) {
        logger.debug("Adicionando transação: {}", transacao);
        transacoes.add(transacao);
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void limparTransacoes() {
        logger.debug("Limpando todas as transações");
        transacoes.clear();
    }

    public EstatisticaDTO calcularEstatisticas() {
        OffsetDateTime agora = OffsetDateTime.now();
        OffsetDateTime umMinutoAtras = agora.minusSeconds(60);

        logger.debug("Calculando estatísticas para transações nos últimos 60 segundos");

        DoubleSummaryStatistics statistics = transacoes.stream()
                .filter(t -> t.getDataHora().isAfter(umMinutoAtras))
                .mapToDouble(Transacao::getValor)
                .summaryStatistics();

                logger.info("Estatísticas calculadas: count={}, sum={}, avg={}, min={}, max={}",
                statistics.getCount(), statistics.getSum(), statistics.getAverage(), statistics.getMin(), statistics.getMax());

        return new EstatisticaDTO(statistics);
    }

}
