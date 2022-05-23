package br.com.senac.entidade;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author marcello.nunes
 */
@Entity
@Table(name = "cartao")
public class Cartao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String numero;
    
    @Column(nullable = false)
    private String bandeira;
    
    @Column(nullable = false,unique = true)
    private String validadeAno;

    public Cartao() {
    }

    public Cartao(String numero, String bandeira, String validadeAno) {
        this.numero = numero;
        this.bandeira = bandeira;
        this.validadeAno = validadeAno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getValidadeAno() {
        return validadeAno;
    }

    public void setValidadeAno(String validadeAno) {
        this.validadeAno = validadeAno;
    }
  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cartao)) {
            return false;
        }
        Cartao other = (Cartao) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "br.com.senac.entidade.Cartao[ id=" + id + " ]";
    }
}