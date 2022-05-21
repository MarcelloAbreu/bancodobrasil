package br.com.senac.dao;

import br.com.senac.entidade.PessoaJuridica;
import java.util.List;
import org.hibernate.*;

/**
 * Pessoa Juridica Dao
 * @author marcello.nunes
 */
public interface PessoaJuridicaDao extends BaseDao<PessoaJuridica, Long>{
    
    List<PessoaJuridica> pesquisarPorNome(String nome,Session sessao) throws HibernateException;
    
}
