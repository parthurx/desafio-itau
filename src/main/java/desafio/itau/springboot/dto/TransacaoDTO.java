package desafio.itau.springboot.dto;

import java.time.OffsetDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class TransacaoDTO {
    @Positive(message = "O valor deve ser maior que zero")
    private double valor;

    @NotNull(message = "É obrigatório informar a data e hora.")
    private OffsetDateTime dataHora;

    public TransacaoDTO() {
    }

    public TransacaoDTO(double valor, OffsetDateTime dataHora) {
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
