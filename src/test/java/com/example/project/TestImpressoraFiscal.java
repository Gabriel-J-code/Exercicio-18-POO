package com.example.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TestImpressoraFiscal {
    String NOME_LOJA = "Loja 1";
	String LOGRADOURO = "Log 1";
	int NUMERO = 10;
	String COMPLEMENTO = "C1";
	String BAIRRO = "Bai 1";
	String MUNICIPIO = "Mun 1";
	String ESTADO = "E1";
	String CEP = "11111-111";
	String TELEFONE = "(11) 1111-1111";
	String OBSERVACAO = "Obs 1";
	String CNPJ = "11.111.111/1111-11";
    String INSCRICAO_ESTADUAL = "123456789";

	//testes venda

	Calendar datahora = new GregorianCalendar(2015,10-1,29,11,9,47);
	String ccf = "021784";
    String coo = "035804";
    
    String BREAK = System.lineSeparator();

    Produto produto1 = new Produto(123456, "Produto1", "kg", 4.35, "");
    Produto produto2 = new Produto(234567, "Produto2", "m", 1.01, "");

    Double valorTotal = 12.74;
    Double pagamento = 20.00;
    String tipoDinheiro = "Dinheiro";
    String tipoCredito = "Cartao de credito";
    String tipoDebito = "Cartao de debito";

    String lei = "Lei 12.741";
    Double taxaFederal = 7.54;
    Double taxaEstadual = 4.81;
    Imposto IMPOSTO = new Imposto(lei, taxaFederal, taxaEstadual);

    Loja LOJA_COMPLETA_COM_IMPOSTO = new Loja(NOME_LOJA,
    new Endereco(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, MUNICIPIO, ESTADO, CEP), TELEFONE, OBSERVACAO,
    CNPJ, INSCRICAO_ESTADUAL,IMPOSTO);

    String CODIGO_OPERADOR = "494715";
    Operador OPERADOR = new Operador(CODIGO_OPERADOR);

    String modelo = "SWEDA IF ST200";
    String versaoECF_IF = "01.00.05";
    String ECF = "067";
    String numeroSerial = "SW031300000000045629";

    ImpressoraFiscal impressoraFiscal = new ImpressoraFiscal(modelo, versaoECF_IF, ECF, numeroSerial);

    String TEXTO_ESPERADO_IMPRESSAO_CUPOM_FISCAL = "Loja 1" + BREAK +
    "Log 1, 10 C1" + BREAK +
    "Bai 1 - Mun 1 - E1" + BREAK +
    "CEP:11111-111 Tel (11) 1111-1111" + BREAK +
    "Obs 1" + BREAK +
    "CNPJ: 11.111.111/1111-11" + BREAK +
    "IE: 123456789" + BREAK +
    "------------------------------" + BREAK +
    "29/10/2015 11:09:47V CCF:021784 COO:035804" + BREAK +
    "   CUPOM FISCAL" + BREAK +
    "ITEM CODIGO DESCRICAO QTD UN VL UNIT(R$) ST VL ITEM(R$)" + BREAK +
    "1 123456 Produto1 2 kg 4.35  8.70" + BREAK +
    "2 234567 Produto2 4 m 1.01  4.04" + BREAK +
    "------------------------------" + BREAK +
    "TOTAL R$ 12.74" + BREAK +
    "Dinheiro 20.00" + BREAK +
    "Troco R$ 7.26" + BREAK +
    "Lei 12.741, Valor aprox., Imposto F=0.96 (7.54%), E=0.61 (4.81%)" + BREAK +
    "------------------------------" + BREAK +
    "OPERADOR: 494715" + BREAK +
    "------------------------------" + BREAK +
    "SWEDA IF ST200" + BREAK +
    "ECF-IF VERSÃO: 01.00.05 ECF: 067" + BREAK +
    "FAB: SW031300000000045629";

    @Test
    public void test_impressora_fiscal_imprimir_venda(){
        Venda venda = LOJA_COMPLETA_COM_IMPOSTO.vender(datahora,ccf,coo);
        venda.adicionarItem(1,produto1,2);
        venda.adicionarItem(2,produto2,4);        
        venda.pagar(pagamento, tipoDinheiro);
        venda.setOperador(OPERADOR);
        String notaFiscal = impressoraFiscal.ImprimirNotaFiscal(venda);
        assertEquals(TEXTO_ESPERADO_IMPRESSAO_CUPOM_FISCAL, notaFiscal);
    }    
}
