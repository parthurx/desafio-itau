package desafio.itau.springboot.model;

import java.time.OffsetDateTime;

public class Transacao {
    private double valor;
    private OffsetDateTime dataHora;

    public Transacao() {
    }

    public Transacao(double valor, OffsetDateTime dataHora) {
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public double getValor() {
        return valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

}