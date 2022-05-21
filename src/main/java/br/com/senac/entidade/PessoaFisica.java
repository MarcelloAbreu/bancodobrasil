package br.com.senac.entidade;
import javax.persistence.*;

/**
 * Pessoa Física
 * id_pessoa chave primaria e chave estrangeira junto
 * Se terminar com One = 1 coisa
 * Se terminar com Many = Uma lista
 * ManyToOne = 1 profissão para várias pessoas Físicas
 * @author marcello.nunes
 */
@Entity
@Table(name = "pessoa_fisica")
@PrimaryKeyJoinColumn(name = "id_cliente")
public class PessoaFisica extends Cliente{
    
    @Column(nullable = false, unique = true)
    private String cpf;
    
    @Column(nullable = false)
    private String rg;
    
    @ManyToOne
    @JoinColumn(name = "id_profissao")
    private Profissao profissao;

    public PessoaFisica() {
    }

    public PessoaFisica(String nome, String email, String cpf, String rg) {
        super(nome, email);
        this.cpf = cpf;
        this.rg = rg;
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

    public Profissao getProfissao() {
        return profissao;
    }

    public void setProfissao(Profissao profissao) {
        this.profissao = profissao;
    }
}