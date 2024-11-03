
package model;

public class UsuarioEnderecoDTO {
    private Usuario usuario;
    private Endereco endereco;

    public UsuarioEnderecoDTO(Usuario usuario, Endereco endereco) {
        this.usuario = usuario;
        this.endereco = endereco;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Endereco getEndereco() {
        return endereco;
    }
}

