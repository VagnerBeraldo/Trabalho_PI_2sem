/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

//import java.time.LocalDate;
    import java.util.Date;

public class Usuario {

    private int id_user;
    private String nome;
    private String sobrenome;
    private String nome_social;
    private String cpf;
    private String data_nascimento;
    private String email;
    private String tipo_pagamento;
   
    //construtor padrao vazio
    public Usuario() {
    }
    
    //constructor para a classe completa metodo cadastrar
    public Usuario(int id_user, String nome, String sobrenome, String nome_social, String cpf, String data_nascimento, String email, String tipo_pagamento) {
        this.id_user = id_user;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nome_social = nome_social;
        this.cpf = cpf;
        this.data_nascimento = data_nascimento;
        this.email = email;
        this.tipo_pagamento = tipo_pagamento;
    }
    
    //metodo constructor personalizado so para o id Metodo deletar e buscar por id
    public Usuario(int id_user) {
        this.id_user = id_user;
    }

    
    
 
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

    public String getDataNascimento() {
        return data_nascimento;
    }
    
    public void setDataNascimento(String data_nascimento) {
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
