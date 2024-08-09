package br.gov.ufg.entity;

import javax.xml.crypto.Data;

public class ClientePessoaFisica extends Cliente{
    //atributos
    String cpf;
    String rg;
    Data data;

    //construtor

    public ClientePessoaFisica(int IdCliente, String nome, String email, String endereco, String telefone, String userName, String password, String cpf, String rg, Data data) {
        super(IdCliente, nome, email, endereco, telefone, userName, password);
        this.cpf = cpf;
        this.rg = rg;
        this.data = data;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}