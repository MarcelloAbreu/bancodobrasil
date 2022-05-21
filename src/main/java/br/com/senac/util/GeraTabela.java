package br.com.senac.util;
import javax.persistence.*;

/**
 * Gerar Tabelas no MySql
 * @author marcello.nunes
 */
public class GeraTabela {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bbrasil_pu");
        emf.close();
    }
}