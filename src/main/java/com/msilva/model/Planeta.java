package com.msilva.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Classe que representa um {@code Planeta}.
 *
 * @author Mateus
 */
@Data
@Document
public class Planeta {

    @Id
    String id;

    String nome;

    String clima;

    String terreno;

    Integer aparicaoEmFilmes;
}
