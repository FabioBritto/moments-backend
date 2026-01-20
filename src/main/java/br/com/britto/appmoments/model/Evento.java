package br.com.britto.appmoments.model;

import br.com.britto.appmoments.model.enums.StatusFinanceiro;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.web.servlet.tags.form.TextareaTag;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Integer id;

    @Column(name = "uuid_evento", nullable = false, length = 45)
    private String uuid;

    @Column(name = "titulo_evento", nullable = false, length = 100)
    private String titulo;

    @Column(name = "data_hora_inicio", nullable = false)
    private LocalDateTime dataHoraInicio;

    @Column(name = "data_hora_fim", nullable = false)
    private LocalDateTime dataHoraFim;

    @Column(name = "frase_evento", length = 100)
    private String frase;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_financeiro")
    private StatusFinanceiro statusFinanceiro;

    @Column(name = "comprovante_transacao", columnDefinition = "TEXT")
    private String comprovanteTransacao;

    @Column(name = "link_moldura")
    private String linkMoldura;

    @Column(name = "id_pagamento", length = 255)
    private String idPagamento;

    @ManyToOne
    @JoinColumn(name = "tbl_cliente_id_cliente")
    private Cliente cliente;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public StatusFinanceiro getStatusFinanceiro() {
        return statusFinanceiro;
    }

    public void setStatusFinanceiro(StatusFinanceiro statusFinanceiro) {
        this.statusFinanceiro = statusFinanceiro;
    }

    public String getComprovanteTransacao() {
        return comprovanteTransacao;
    }

    public void setComprovanteTransacao(String comprovanteTransacao) {
        this.comprovanteTransacao = comprovanteTransacao;
    }

    public String getLinkMoldura() {
        return linkMoldura;
    }

    public void setLinkMoldura(String linkMoldura) {
        this.linkMoldura = linkMoldura;
    }

    public String getIdPagamento() { return  idPagamento; }

    public void setIdPagamento(String idPagamento) { this.idPagamento = idPagamento; }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", titulo='" + titulo + '\'' +
                ", dataHoraInicio=" + dataHoraInicio +
                ", dataHoraFim=" + dataHoraFim +
                ", frase='" + frase + '\'' +
                ", statusFinanceiro=" + statusFinanceiro +
                ", comprovanteTransacao='" + comprovanteTransacao + '\'' +
                ", linkMoldura='" + linkMoldura + '\'' +
                ", cliente=" + cliente +
                '}';
    }
}
