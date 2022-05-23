package br.com.senac.util;

import java.math.BigDecimal;

/**
 * Gerador Util
 * jar = clean and build
 * @author marcello.nunes
 */
public class GeradorUtil {
    
    public static String gerarNumero(int qtde){
       String numeroGerado = "";
       int indice;
        for (int i = 0; i < qtde; i++) {
            indice = (int)(Math.random() * 10);
            numeroGerado += indice;
            // numeroGerado = numeradoGerado + indice;
        }
        return numeroGerado;       
    }
    
    public static String gerarCpf(){
        return gerarNumero(3)+ "." + gerarNumero(3)+ "." + gerarNumero(3)+ "-" + gerarNumero(2);
    }     
    
    public static String gerarSenha(int qtde){
         String[] letras ={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","0","1","2","3","4","5","6","7","8","9"};
         int indice;
         String senha = "";
         for (int i = 0; i < qtde; i++) {
             indice = (int)(Math.random() * letras.length );
             senha += letras[indice];   
        }
        return senha;
    }
    
    public static String gerarCnpj(){
        return gerarNumero(2) + "." + gerarNumero(3) + "." + gerarNumero(3) + "/" + gerarNumero(4) + "-" + gerarNumero(2);
    }
    
    public static String gerarTelefoneFixo(){
        return "(48) 3" + gerarNumero(3)+ "-" + gerarNumero(4);
    }
    
    public static String gerarTelefoneCelular(){
        return "(48) 9" + gerarNumero(4)+ "-" + gerarNumero(4);
    }
    
    public static String gerarCep(){
        return "(" + gerarNumero(5) + "-" + gerarNumero(3)+ ")";
    }
    
    public static String gerarNome(){
        String[] nomes = {"Marcello","Lucas","João","Ana","Leticia","Silvio","Jessica","Thalita","Rafael","Marcos","Mateus","André","Rafael"};
         int indice = (int)(Math.random() * nomes.length);         
        return nomes[indice] + " " + gerarSobrenome();
    }
    
    public static String gerarLogin(){
        String nome = gerarNome();
        return nome.toLowerCase() + "@";
    }
    
    private static String gerarSobrenome(){
        String[] sobrenomes = {"Abreu","Rocha","Dias","Oliveira","Santos","Moura","Dutra","Da Silva","Pereira","Coutinho","Sousa"};
         int indice = (int)(Math.random() * sobrenomes.length);         
        return sobrenomes[indice];      
    }
    
    public static String gerarCidade(){
        String[] cidades = {"Florianópolis","Palhoça","São José","Curitiba","Porto Alegre","São Paulo","Rio de Janeiro","Salvador","Maceió" };
        int indice = (int)(Math.random() * cidades.length);         
        return cidades[indice];
    }
    
    public static String gerarBairros(){
        String[] bairros = {"Sertão do Maruim","Sítio Cercado","Forquilinhas","Lagoa da Conceição","Cidade Industrial","Fazenda Rio Grande","Portão","Água Verde", "Centro" };
        int indice = (int)(Math.random() * bairros.length);         
        return bairros[indice];
    }
    
        public static String gerarUfs(){
        String[] ufs = {"AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"};
        int indice = (int)(Math.random() * ufs.length);         
        return ufs[indice];
    }
        
        public static BigDecimal gerarSalario(){
            return new BigDecimal(gerarNumero(8)).setScale(2);
        }
        
        public static String gerarProfissao(){
        String[] profissoes = {"Programador","Analista","Pintor","Advgado","Professor","Pescador","Eletricista","Testador","Carpinteiro","Pedreiro","Gerente","Executivo","Consultor"};
         int indice = (int)(Math.random() * profissoes.length);         
        return profissoes[indice];
    }
        
        public static String gerarBandeirasCartao(){
        String[] cartoes = {"Elo","Hipercard","American Express","Visa","Mastercard","Diners Club","Nubank","Calcard","Caixa","PicPay","Banco Do Brasil","Santander","Itaú"};
         int indice = (int)(Math.random() * cartoes.length);         
        return cartoes[indice];
    }
       

    public static void main(String[] args) {
        //System.out.println("Número Gerado: " + gerarNumero(10));
        //System.out.println("CPF: " + gerarCpf());
        //System.out.println("Senha: " + gerarSenha(10));
        //System.out.println("CNPJ: " + gerarCnpj());
        //System.out.println("Telefone Fixo: " + gerarTelefoneFixo());
        //System.out.println("Telefone Celular: " + gerarTelefoneCelular());
        //System.out.println("CEP: " + gerarCep());
        //System.out.println("Nome e Sobrenome: " + gerarNome());
        //System.out.println("Login: " + gerarLogin());
        //System.out.println("Cidades: " + gerarCidade());
        //System.out.println("Bairros: " + gerarBairros());
        //System.out.println("UFs: " + gerarUfs());
        System.out.println("Salario: " + gerarSalario());

    }
}
