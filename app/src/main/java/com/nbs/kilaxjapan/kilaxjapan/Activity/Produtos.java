package com.nbs.kilaxjapan.kilaxjapan.Activity;
public class Produtos {



    private String ean;
    private String descricao;
    private String material;
    private String medidas;
    private String origem;
    private String peso;
    private String precaucoes;


    public Produtos() {

    }

    public Produtos(String ean, String descricao, String material, String medidas, String origem, String peso, String precaucoes) {
        this.ean = ean;
        this.descricao = descricao;
        this.material = material;
        this.medidas = medidas;
        this.origem = origem;
        this.peso = peso;
        this.precaucoes = precaucoes;

    }

    public String getEan() {
        return ean;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getMaterial() {
        return material;
    }

    String getMedidas() {
        return medidas;
    }

    String getOrigem() {
        return origem;
    }

    String getPeso() {
        return peso;
    }

    String getPrecaucoes() {
        return precaucoes;
    }


    @Override
    public String toString() {
        return "\nCodigo de Barras: " + ean +
                "\nNome do Produto: " + descricao +
                "\nMaterial: " + material +
                "\nMedidas: " + medidas +
                "\nOrigem: " + origem +
                "\nPeso: " + peso +
                "\nPrecações: " + precaucoes;
    }

}
