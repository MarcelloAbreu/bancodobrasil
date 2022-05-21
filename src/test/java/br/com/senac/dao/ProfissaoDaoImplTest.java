package br.com.senac.dao;

import br.com.senac.entidade.Profissao;
import com.github.javafaker.Faker;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marcello.nunes
 */
public class ProfissaoDaoImplTest {
    
    private Profissao profissao;
    private ProfissaoDao profissaoDao;
    private Session sessao;
    
    
    public ProfissaoDaoImplTest() {
        profissaoDao = new ProfissaoDaoImpl();
    }
    
    //@Test
    public void testSalvar() {
        System.out.println("salvar");
        Faker faker = new Faker();
        profissao = new Profissao(faker.job().title(), faker.lorem().sentence());
        sessao = HibernateUtil.abrirConexao();
        profissaoDao.salvarOuAlterar(profissao, sessao);
        sessao.close();
        assertNotNull(profissao.getId());      
    }
    
    //@Test
    public void testAlterar() {
        System.out.println("alterar");
        buscarProfissao();
        profissao.setNome("Analista");
        profissao.setDescricao("Análise de Sistemas");
        sessao = HibernateUtil.abrirConexao();
        profissaoDao.salvarOuAlterar(profissao, sessao);
        sessao.close();
        // Verificar se Alterou
        sessao = HibernateUtil.abrirConexao();
        Profissao pAlterado = profissaoDao.pesquisarPorId(profissao.getId(), sessao);
        sessao.close();
        assertEquals(profissao.getNome(), pAlterado.getNome());
        assertEquals(profissao.getDescricao(), pAlterado.getDescricao());
    }
 
    //@Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
        buscarProfissao();
        sessao = HibernateUtil.abrirConexao();
        Profissao pPesquisado = profissaoDao.pesquisarPorId(profissao.getId(), sessao);
        sessao.close();
        assertNotNull(pPesquisado);
    }

    @Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisarPorNome");
        buscarProfissao();
        sessao = HibernateUtil.abrirConexao();
        String nome = profissao.getNome().substring(0);
        List<Profissao> profissoes = profissaoDao.pesquisarPorNome(profissao.getNome(), sessao);
        sessao.close();
        assertTrue(profissoes.size() > 0);
    }
    
    public Profissao buscarProfissao(){
        sessao = HibernateUtil.abrirConexao();
        Query<Profissao> consulta = sessao.createQuery("from Profissao p");
        List<Profissao> profissoes = consulta.getResultList();
        sessao.close();
        if (profissoes.isEmpty()) {
            testSalvar(); 
        } else {
            profissao = profissoes.get(0);
        }
        return profissao;
    }
}