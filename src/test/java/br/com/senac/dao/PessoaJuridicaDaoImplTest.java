package br.com.senac.dao;

import br.com.senac.entidade.Endereco;
import br.com.senac.entidade.PessoaJuridica;
import static br.com.senac.util.GeradorUtil.*;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Teste Unitario PJ
 * @author marcello.nunes
 */
public class PessoaJuridicaDaoImplTest {
    
    private PessoaJuridica pessoaJuridica;
    private PessoaJuridicaDao pessoaJuridicaDao;
    private Session sessao;
    
    public PessoaJuridicaDaoImplTest() {
        pessoaJuridicaDao = new PessoaJuridicaDaoImpl();
    }
    
    @Test
    public void testSalvar() {
        System.out.println("Salvar");
        pessoaJuridica = new PessoaJuridica("Empresa " + gerarNome(), gerarLogin() + "gmail.com", gerarCnpj(), gerarNumero(6));
        Endereco endereco = gerarEndereco();
        pessoaJuridica.setEndereco(endereco);
        endereco.setCliente(pessoaJuridica);
        sessao = HibernateUtil.abrirConexao();
        pessoaJuridicaDao.salvarOuAlterar(pessoaJuridica, sessao);
        sessao.close();
        assertNotNull(pessoaJuridica.getId());
    }
    
    private Endereco gerarEndereco(){
        Endereco end = new Endereco("Rua Pedro Alvez Cabral", "Ilha", gerarNumero(3), gerarCidade(), gerarUfs(), "Casa", gerarCep());
        return end;
    }
    
    //@Test
    public void testAlterar(){
        System.out.println("Alterar");
        buscarPJ();
        pessoaJuridica.setNome(gerarNome());
        pessoaJuridica.getEndereco().setBairro("Cidade Industrial");
        sessao = HibernateUtil.abrirConexao();
        pessoaJuridicaDao.salvarOuAlterar(pessoaJuridica, sessao);
        sessao.close();
        //Verificando se Trocou o Nome
        sessao = HibernateUtil.abrirConexao();
        PessoaJuridica pjAlterado = pessoaJuridicaDao.pesquisarPorId(pessoaJuridica.getId(), sessao);
        sessao.close();
        assertEquals(pessoaJuridica.getNome(), pjAlterado.getNome());
        assertEquals(pessoaJuridica.getEndereco().getBairro(), pjAlterado.getEndereco().getBairro());
    }
    
    //@Test
    public void testExlcluir(){
        System.out.println("Excluir");
        buscarPJ();
        sessao = HibernateUtil.abrirConexao();
        pessoaJuridicaDao.excluir(pessoaJuridica, sessao);
        // Verificando se Excluiu
        PessoaJuridica pjExcluido = pessoaJuridicaDao.pesquisarPorId(pessoaJuridica.getId(), sessao);
        assertNull(pjExcluido);
    }

    //@Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
        buscarPJ();
        sessao = HibernateUtil.abrirConexao();
        PessoaJuridica pjPesquisado = pessoaJuridicaDao.pesquisarPorId(pessoaJuridica.getId(), sessao);
        sessao.close();
        assertNotNull(pjPesquisado);
    }
    
    public PessoaJuridica buscarPJ(){
        String hql = "from PessoaJuridica pj";
        sessao = HibernateUtil.abrirConexao();
        Query<PessoaJuridica> consulta = sessao.createQuery(hql);
        List<PessoaJuridica> pessoaJuridicas = consulta.getResultList();
        sessao.close();
        if (pessoaJuridicas.isEmpty()) {
            testSalvar();
        } else {
            pessoaJuridica = pessoaJuridicas.get(0);
        }
     return pessoaJuridica;   
    }   
}