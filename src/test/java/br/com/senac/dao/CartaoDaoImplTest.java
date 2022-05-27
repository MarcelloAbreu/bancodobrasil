package br.com.senac.dao;

import br.com.senac.entidade.Cartao;
import br.com.senac.entidade.Cliente;
import static br.com.senac.util.GeradorUtil.*;
import com.github.javafaker.*;
import java.util.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testes Unitarios do Cartão
 * @author Nunes
 */
public class CartaoDaoImplTest {
    
    private Cartao cartao;
    private CartaoDao cartaoDao;
    private Session sessao;
    
    public CartaoDaoImplTest() {
        cartaoDao = new CartaoDaoImpl();
    }
    
    @Test
    public void testSalvar() {
        System.out.println("salvar");
        Faker faker = new Faker();
        PessoaFisicaDaoImplTest pfdit = new PessoaFisicaDaoImplTest();
        pfdit.buscarPF();
        PessoaJuridicaDaoImplTest pjdit = new PessoaJuridicaDaoImplTest();
        pjdit.buscarPJ();
        cartao = new Cartao(faker.finance().creditCard(CreditCardType.MASTERCARD),gerarBandeirasCartao(), "202" + gerarNumero(1));
        cartao.setCliente(buscarCliente()); 
        sessao = HibernateUtil.abrirConexao();
        cartaoDao.salvarOuAlterar(cartao, sessao);
        sessao.close();
        assertNotNull(cartao.getId());
    }
        
    @Test
    public void testAlterar() {
        System.out.println("alterar");
        buscarCartao();
        cartao.setBandeira(gerarBandeirasCartao());
        sessao = HibernateUtil.abrirConexao();
        cartaoDao.salvarOuAlterar(cartao, sessao);
        sessao.close();
        //Verificar se Alterou
        sessao = HibernateUtil.abrirConexao();
        Cartao cAlterado = cartaoDao.pesquisarPorId(cartao.getId(), sessao);
        sessao.close();
        assertEquals(cartao.getBandeira(), cAlterado.getBandeira());
    }
        
    @Test
    public void testExcluir() {
        System.out.println("excluir");
        buscarCartao();
        sessao = HibernateUtil.abrirConexao();
        cartaoDao.excluir(cartao, sessao);
        //Verificar se Excluiu
        Cartao cExcluido = cartaoDao.pesquisarPorId(cartao.getId(), sessao);
        assertNull(cExcluido);
    }

    @Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
        buscarCartao();
        sessao = HibernateUtil.abrirConexao();
        Cartao cartaoPesquisado = cartaoDao.pesquisarPorId(cartao.getId(), sessao);
        sessao.close();
        assertNotNull(cartaoPesquisado);
    }

    @Test
    public void testPesquisarPorNumero() {
        System.out.println("pesquisarPorNumero");
        buscarCartao();
        sessao = HibernateUtil.abrirConexao();
        int elemento = cartao.getNumero().indexOf(" ");
        String numero = cartao.getNumero().substring(0, elemento);
        Cartao pesquisaPorNumero = cartaoDao.pesquisarPorNumero(cartao.getNumero(), sessao);
        sessao.close();
        assertEquals(cartao.getNumero(), pesquisaPorNumero.getNumero());
    }
    
    public Cartao buscarCartao(){
        sessao = HibernateUtil.abrirConexao();
        Query<Cartao> consulta = sessao.createQuery("from Cartao c");
        List<Cartao> cartoes = consulta.getResultList();
        sessao.close();
        if (cartoes.isEmpty()) {
            testSalvar();
        } else {
            cartao = cartoes.get(0);
        }
        return cartao;
    }
    
    public Cliente buscarCliente(){
        sessao = HibernateUtil.abrirConexao();
        Query<Cliente> consulta = sessao.createQuery("from Cliente c");
        List<Cliente> clientes = consulta.getResultList();
        sessao.close();
        Collections.shuffle(clientes);
        return clientes.get(0);
    }
}
