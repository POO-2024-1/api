package br.gov.ufg.entity;
import java.util.Date;

public class Pedido {
    private int idPedido;
    private Date dataPedido;
    private String status;

    public Pedido(int idPedido, Date dataPedido, String status, int i) {
        this.idPedido = idPedido;
        this.dataPedido = dataPedido;
        this.status = status;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}