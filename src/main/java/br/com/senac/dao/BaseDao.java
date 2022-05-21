package br.com.senac.dao;
import org.hibernate.*;

/**
 *
 * @author marcello.nunes
 * @param <T>
 * @param <ID>
 */
public interface BaseDao<T, ID> {
    
  public abstract void salvarOuAlterar(T entidade, 
                        Session sessao) throws HibernateException;
  
  void excluir(T entidade, Session sessao) throws HibernateException;
  
  T pesquisarPorId(ID id, Session sessao) throws HibernateException;
    
}
