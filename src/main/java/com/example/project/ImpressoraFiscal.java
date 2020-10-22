package com.example.project;

public class ImpressoraFiscal {
    private String modelo;
    private String versaoECF_IF;
    private String ECF;
    private String numeroSerial;

    public ImpressoraFiscal(String modelo, String versaoECF_IF, String ECF, String numeroSerial) {
        this.setModelo(modelo);
        this.setVersaoECF_IF(versaoECF_IF);
        this.setECF(ECF);
        this.setNumeroSerial(numeroSerial);
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getVersaoECF_IF() {
        return versaoECF_IF;
    }

    public void setVersaoECF_IF(String versaoECF_IF) {
        this.versaoECF_IF = versaoECF_IF;
    }

    public String getECF() {
        return ECF;
    }

    public void setECF(String eCF) {
        this.ECF = eCF;
    }

    public String getNumeroSerial() {
        return numeroSerial;
    }

    public void setNumeroSerial(String numeroSerial) {
        this.numeroSerial = numeroSerial;
    }

    public String ImprimirNotaFiscal(Venda venda) {
        String BREAK = System.lineSeparator();

        StringBuilder impressao = new StringBuilder();
        impressao.append(venda.imprimirCupom()+BREAK);
        impressao.append("------------------------------"+BREAK);
        impressao.append(getModelo()+BREAK);
        impressao.append(String.format("ECF-IF VERSÃO: %s ECF: %s",getVersaoECF_IF(),getECF())+BREAK);
        impressao.append(String.format("FAB: %s",getNumeroSerial()));

        return impressao.toString();        
    }
    
}
