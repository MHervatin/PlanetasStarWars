package com.msilva.controller;

import com.msilva.controller.integracaoExterna.IntegracaoExternaSWAPI;
import com.msilva.model.Planeta;
import com.msilva.service.PlanetaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller para {@code Planete}.
 *
 * @author Mateus
 */
@RestController
@RequestMapping("/planeta")
public class PlanetaController {

    /**
     * Provê os serviços de {@code Planeta}.
     */
    @Autowired
    private PlanetaService planetaService;

    /**
     * Efetua a inserção de um novo Planeta.
     *
     * @param planeta O {@code Planeta} a ser inserido.
     *
     * @return O {@code ResponseEntity} da inserção.
     */
    @PostMapping
    public ResponseEntity inserirPlaneta(@RequestBody Planeta planeta) {
        IntegracaoExternaSWAPI ws = new IntegracaoExternaSWAPI();

        Integer aparicoesEmFilmes
                = ws.aparicoesEmFilmes(planeta.getNome().replace(" ", ""));

        planeta.setAparicaoEmFilmes(
                aparicoesEmFilmes != null ? aparicoesEmFilmes : 0);

        return this.planetaService.inserirPlaneta(planeta);
    }

    /**
     * Retorna uma lista com todos os {@code Planetas}.
     *
     * @return Uma lista com todos os {@code Planeta}.
     */
    @GetMapping
    public List<Planeta> buscaTodosPlanetas() {
        return this.planetaService.buscaTodosPlanetas();
    }

    /**
     * Retorna o {@code Planeta} com o {@code nome} informado.
     *
     * @param nome Nome do {@code Planeta}.
     *
     * @return O {@code Planeta} com o nome buscado.
     */
    @GetMapping(path = {"/"})
    public ResponseEntity buscaPlanetaPorNome(
            @RequestParam("nome") String nome) {
        return this.planetaService.buscaPlanetaPorNome(nome);
    }

    /**
     * Retorna o {@code Planeta} com o {@code idPlanete} informado.
     *
     * @param id O ID do {@code Planeta}
     *
     * @return O Planeta com o ID buscado.
     */
    @GetMapping(path = {"/{id}"})
    public ResponseEntity buscaPlanetaPorID(@PathVariable String id) {
        return this.planetaService.buscaPlanetaPorID(id);
    }

    /**
     * Remove o {@code Planeta} com o {@code idPlanete} informado.
     *
     * @param idPlaneta ID do {@code Planeta} a ser removido.
     *
     * @return Resposta positiva ou negativa a remoção do {@code Planeta}.
     */
    @DeleteMapping(path = {"/{idPlaneta}"})
    public ResponseEntity removePlaneta(@PathVariable String idPlaneta) {
        return this.planetaService.removePlaneta(idPlaneta);
    }
}
