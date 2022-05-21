package br.com.senac.dao;
import br.com.senac.entidade.Profissao;
import java.util.List;
import org.hibernate.*;

/**
 * Profissao Dao
 * Toda Interface tem que ter uma classe implementado ela
 * @author marcello.nunes
 */
public interface ProfissaoDao extends BaseDao<Profissao, Long>{
    
    List<Profissao> pesquisarPorNome(String nome,Session sessao) throws HibernateException;
    
}
