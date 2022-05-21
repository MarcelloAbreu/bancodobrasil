package br.com.senac.entidade;
import javax.persistence.*;

/**
 * Pessoa Jurídica
 * @author marcello.nunes
 */

@Entity
@Table(name = "pessoa_juridica")
@PrimaryKeyJoinColumn(name = "id_cliente")
public class PessoaJuridica extends Cliente{
    
   @Column(nullable = false,unique = true)
   private String cnpj;
   
   @Column(nullable = false)
   private String inscricaoEstadual;

    public PessoaJuridica() {
    }

    public PessoaJuridica(String nome, String email,String cnpj, String inscricaoEstadual) {
        super(nome, email);
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }    
}