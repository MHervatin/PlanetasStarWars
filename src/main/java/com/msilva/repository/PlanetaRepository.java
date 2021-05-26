package com.msilva.repository;

import com.msilva.model.Planeta;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repositório para {@code Planeta}.
 *
 * @author Mateus
 */
public interface PlanetaRepository extends MongoRepository<Planeta, String> {

    /**
     * Retorna o {@code Planeta} com o {@code nome} informado.
     *
     * @param nome Nome do {@code Planeta}.
     *
     * @return O {@code Planeta} com o nome buscado.
     */
    public Optional<Planeta> findByNome(String nome);
}
