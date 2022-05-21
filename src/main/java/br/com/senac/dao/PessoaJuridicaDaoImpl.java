package br.com.senac.dao;

import br.com.senac.entidade.PessoaJuridica;
import java.io.Serializable;
import java.util.List;
import org.hibernate.*;
import org.hibernate.query.Query;

/**
 * Classe Implementada PJ
 * @author marcello.nunes
 */
public class PessoaJuridicaDaoImpl extends BaseDaoImpl<PessoaJuridica, Long> implements PessoaJuridicaDao, Serializable{

    @Override
    public PessoaJuridica pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return sessao.find(PessoaJuridica.class, id);
    }

    @Override
    public List<PessoaJuridica> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
        Query<PessoaJuridica> consulta = sessao.createQuery("from PessoaJuridica pj where pj.nome like :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.getResultList();
    }
}
