package com.msilva.service;

import com.msilva.model.Planeta;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Prove serviços para {@code Planete}.
 *
 * @author Mateus
 */
public interface PlanetaService {

    /**
     * Efetua a inserção de um novo planeta.
     *
     * @param planeta O {@code Planeta} a ser inserido.
     *
     * @return O {@code ResponseEntity} da inserção.
     */
    public ResponseEntity inserirPlaneta(@RequestBody Planeta planeta);

    /**
     * Retorna uma lista com todos os {@code Planetas}.
     *
     * @return Uma lista com todos os {@code Planeta}.
     */
    public List<Planeta> buscaTodosPlanetas();

    /**
     * Retorna o {@code Planeta} com o {@code nome} informado.
     *
     * @param nome Nome do {@code Planeta}.
     *
     * @return O {@code Planeta} com o nome buscado.
     */
    public ResponseEntity buscaPlanetaPorNome(@PathVariable String nome);

    /**
     * Retorna o {@code Planeta} com o {@code idPlanete} informado.
     *
     * @param id O ID do {@code Planeta}
     *
     * @return O Planeta com o ID buscado.
     */
    public ResponseEntity buscaPlanetaPorID(@PathVariable String id);

    /**
     * Remove o {@code Planeta} com o {@code idPlanete} informado.
     *
     * @param idPlaneta ID do {@code Planeta} a ser removido.
     *
     * @return Resposta positiva ou negativa a remoção do {@code Planeta}.
     */
    public ResponseEntity removePlaneta(@PathVariable String idPlaneta);
}
