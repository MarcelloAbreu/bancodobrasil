package br.com.senac.dao;

import br.com.senac.entidade.Cartao;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * Cartão Dao
 * @author marcello.nunes
 */
public interface CartaoDao extends BaseDao<Cartao, Long>{
    
    Cartao pesquisarPorNumero(String numero, Session sessao) throws HibernateException;
    
}
