package br.com.senac.dao;

import br.com.senac.entidade.Cartao;
import java.io.Serializable;
import org.hibernate.*;
import org.hibernate.query.Query;

/**
 *
 * @author Nunes
 */
public class CartaoDaoImpl extends BaseDaoImpl<Cartao, Long> implements CartaoDao, Serializable{

    @Override
    public Cartao pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return sessao.find(Cartao.class, id);
    }

    @Override
    public Cartao pesquisarPorNumero(String numero, Session sessao) throws HibernateException {
        Query<Cartao> consulta = sessao.createQuery("from Cartao c where c.numero = :numero");
        consulta.setParameter("numero", numero);
        return consulta.getSingleResult();
    }
}
