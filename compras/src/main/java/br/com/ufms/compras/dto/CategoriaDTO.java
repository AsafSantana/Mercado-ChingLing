package br.com.ufms.compras.dto;

import br.com.ufms.compras.entity.Categoria;

import java.io.Serializable;

public class CategoriaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long value;
    private String text;

    public CategoriaDTO() {
    }

    public CategoriaDTO(Categoria categoria) {
        this.value = categoria.getId();
        this.text = categoria.getNome();
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
