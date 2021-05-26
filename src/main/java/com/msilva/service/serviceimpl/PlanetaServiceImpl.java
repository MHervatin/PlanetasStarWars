package com.msilva.service.serviceimpl;

import com.msilva.model.Planeta;
import com.msilva.repository.PlanetaRepository;
import com.msilva.service.PlanetaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Implementação dos serviços de {@code Planete}.
 *
 * @author Mateus
 */
@Service
public class PlanetaServiceImpl implements PlanetaService {

    /**
     * Representa o {@code PlanetaRepository}.
     */
    @Autowired
    private PlanetaRepository planetaRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity inserirPlaneta(Planeta planeta) {
        ResponseEntity resposta = buscaPlanetaPorNome(planeta.getNome());

        if (resposta.getStatusCode() != HttpStatus.NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("O Planeta '" + planeta.getNome() + "' já existe!");
        } else {
            Planeta planteInserido = this.planetaRepository.insert(planeta);
            return ResponseEntity.ok().body(planteInserido);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Planeta> buscaTodosPlanetas() {
        return this.planetaRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity buscaPlanetaPorNome(String nome) {
        return this.planetaRepository.findByNome(nome)
                .map(p -> ResponseEntity.ok().body(p))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity buscaPlanetaPorID(String id) {
        return this.planetaRepository
                .findById(id)
                .map(p -> ResponseEntity.ok().body(p))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity removePlaneta(String idPlaneta) {
        return this.planetaRepository.findById(idPlaneta).map(p
                -> {
            this.planetaRepository.deleteById(idPlaneta);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
