package com.nbs.kilaxjapan.kilaxjapan.Activity;


import java.io.Serializable;

public class Produtos implements Serializable{

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

    public String getMedidas() {
        return medidas;
    }

    public String getOrigem() {
        return origem;
    }

    public String getPeso() {
        return peso;
    }

    public String getPrecaucoes() {
        return precaucoes;
    }

    @Override
    public String toString() {
        return "Codigo de Barras: " + ean + "\n" +
                "Nome do Produto: " + descricao + "\n" +
                "Material: " + material + "\n" +
                "Medidas: " + medidas + "\n" +
                "Origem: " + origem + "\n" +
                "Peso: "+ peso +"\n" +
                "Precações: "+ precaucoes;
    }
}
