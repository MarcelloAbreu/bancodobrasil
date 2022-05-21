package br.com.senac.dao;
import br.com.senac.entidade.Profissao;
import java.io.Serializable;
import java.util.List;
import org.hibernate.*;
import org.hibernate.query.Query;

/**
 * Classe Implementada Profissão
 * @author marcello.nunes
 */
public class ProfissaoDaoImpl extends BaseDaoImpl<Profissao, Long> implements ProfissaoDao, Serializable{

    @Override
    public Profissao pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return sessao.find(Profissao.class, id);
    }

    @Override
    public List<Profissao> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
        Query<Profissao> consulta = sessao.createQuery("from Profissao p where p.nome like :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.getResultList();
    }
}