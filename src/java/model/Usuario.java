/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

public class Usuario {

    private int id_user;
    private String nome;
    private String sobrenome;
    private String nome_social;
    private String cpf;
    private LocalDate data_nascimento;
    private String email;
    private String tipo_pagamento;
    //private Curso curso;  model curso ainda nao implementado

    
    //Getters e Setters da classe usuario
    public int getID() {
        return id_user;
    }

    public void setID(int id_user) {
        this.id_user = id_user;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getNomeSocial() {
        return nome_social;
    }

    public void setNomeSocial(String nome_social) {
        this.nome_social = nome_social;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return data_nascimento;
    }
    
    public void setDataNascimento(LocalDate data_nascimento) {
        if (data_nascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de nascimento não pode ser no futuro");
        }
        this.data_nascimento = data_nascimento;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getTipoPagamento(){
        return tipo_pagamento;
    }
    
    public void setTipoPagamento(String tipo_pagamento){
        this.tipo_pagamento = tipo_pagamento;
    }
    
    //possiveis metodos futuros de tratamentos abaixo caso precise
    

}
