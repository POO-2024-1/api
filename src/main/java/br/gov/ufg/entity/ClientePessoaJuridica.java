package br.gov.ufg.entity;

public class ClientePessoaJuridica extends Cliente {
    // atributos
    private String cnpj;
    private String razaoSocial;
    private String inscricaoEstadual;

    // construtor


    public ClientePessoaJuridica(int IdCliente, String nome, String email, String endereco, String telefone, String userName, String password, String cnpj, String razaoSocial, String inscricaoEstadual, String s) {
        super(IdCliente, nome, email, endereco, telefone, userName, password);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.inscricaoEstadual = inscricaoEstadual;
    }

    // getters e setters
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

}