package br.com.senac.dao;
import br.com.senac.entidade.PessoaFisica;
import java.io.Serializable;
import java.util.List;
import org.hibernate.*;
import org.hibernate.query.Query;

/**
 * Classe Implementada PF
 * @author marcello.nunes
 */
public class PessoaFisicaDaoImpl extends BaseDaoImpl<PessoaFisica, Long> implements PessoaFisicaDao, Serializable{

    @Override
    public PessoaFisica pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return sessao.find(PessoaFisica.class, id);
    }

    @Override
    public List<PessoaFisica> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
        Query<PessoaFisica> consulta = sessao.createQuery("from PessoaFisica pf where pf.nome like :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.getResultList();
    }
}