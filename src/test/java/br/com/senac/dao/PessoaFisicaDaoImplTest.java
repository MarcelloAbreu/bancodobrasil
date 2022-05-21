package br.com.senac.dao;

import br.com.senac.entidade.Endereco;
import br.com.senac.entidade.PessoaFisica;
import static br.com.senac.util.GeradorUtil.*;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Teste Unitario PF
 * Dao = New DaoImpl
 * Crtl + O = pesquisa para achar a classe
 * @author marcello.nunes
 */
public class PessoaFisicaDaoImplTest {
    
    private PessoaFisica pessoaFisica;
    private PessoaFisicaDao pessoaFisicaDao;
    private Session sessao;
    
    public PessoaFisicaDaoImplTest() {
        pessoaFisicaDao = new PessoaFisicaDaoImpl();
    }
    
    //@Test
    public void testSalvar() {
        System.out.println("Salvar");
        ProfissaoDaoImplTest pdit = new ProfissaoDaoImplTest(); // Instaciar para pegar o buscarProfissao()
        pessoaFisica = new PessoaFisica(gerarNome(), gerarLogin() + "gmail.com", gerarCpf(), gerarNumero(7));
        Endereco endereco = gerarEndereco();//Salvar o Endereço
        pessoaFisica.setEndereco(endereco);
        endereco.setCliente(pessoaFisica); // caso cascade
        pessoaFisica.setProfissao(pdit.buscarProfissao()); // Ele devolve o Objeto(ID)
        sessao = HibernateUtil.abrirConexao();
        pessoaFisicaDao.salvarOuAlterar(pessoaFisica, sessao);
        sessao.close();
        assertNotNull(pessoaFisica.getId());
    }
    
    private Endereco gerarEndereco(){
        Endereco end = new Endereco("Rua das Gásolina", "Centro", gerarNumero(3), gerarCidade(), gerarUfs(), "Casa", gerarCep());
        return end;
    }
    
    //@Test
    public void testAlterar() {
        System.out.println("Alterar");
        buscarPF();
        pessoaFisica.setNome(gerarNome());
        pessoaFisica.setCpf(gerarCpf());
        pessoaFisica.getEndereco().setLogradouro("Rua Nova");
        sessao = HibernateUtil.abrirConexao();
        pessoaFisicaDao.salvarOuAlterar(pessoaFisica, sessao);
        sessao.close();
        //Verificar se foi alterado
        sessao = HibernateUtil.abrirConexao();
        PessoaFisica pfAlt = pessoaFisicaDao.pesquisarPorId(pessoaFisica.getId(), sessao);
        sessao.close();
        assertEquals(pessoaFisica.getNome(), pfAlt.getNome());
        assertEquals(pessoaFisica.getCpf(), pfAlt.getCpf());
        assertEquals(pessoaFisica.getEndereco().getLogradouro(), pfAlt.getEndereco().getLogradouro());
    }
    
    //@Test
    public void testExcluir() {
        System.out.println("Excluir");
        buscarPF();
        sessao = HibernateUtil.abrirConexao();
        pessoaFisicaDao.excluir(pessoaFisica, sessao);
        //Verificar se foi Excluido
        PessoaFisica pfExcluido = pessoaFisicaDao.pesquisarPorId(pessoaFisica.getId(), sessao);
        assertNull(pfExcluido); 
    }

    //@Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
        buscarPF();
        sessao = HibernateUtil.abrirConexao();
        PessoaFisica pfPesquisado = pessoaFisicaDao.pesquisarPorId(pessoaFisica.getId(), sessao);
        sessao.close();
        assertNotNull(pfPesquisado);
    }
    
    @Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisarPorNome");
        buscarPF();
        sessao = HibernateUtil.abrirConexao();
        int elemento = pessoaFisica.getNome().indexOf(" ");
        String nome = pessoaFisica.getNome().substring(elemento);
        List<PessoaFisica> pessoaFisicas = pessoaFisicaDao.pesquisarPorNome(pessoaFisica.getNome(), sessao);
        sessao.close();
        assertTrue(pessoaFisicas.size() > 0);
    } 
    
    public PessoaFisica buscarPF(){
        String hql = "from PessoaFisica pf";
        sessao = HibernateUtil.abrirConexao();
        Query<PessoaFisica> consulta = sessao.createQuery(hql);
        List<PessoaFisica> pessoaFisicas = consulta.getResultList();
        sessao.close();
        if (pessoaFisicas.isEmpty()) {
            testSalvar();
        } else {
            pessoaFisica = pessoaFisicas.get(0);
        }
        return pessoaFisica;
    }     
}