package com.msilva.controller.integracaoExterna;

import com.msilva.controller.integracaoExterna.ws.RetornoWS;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Classe responsável pela integração com a {@code SWAPI}
 *
 * @author Mateus
 */
public class IntegracaoExternaSWAPI {

    /**
     * Retorna o numero de aparições do {@code Planeta} com {@code nomePlaneta}.
     *
     * @param nomePlaneta Nome do {@code Planeta} buscado.
     *
     * @return O numero de aparições do {@code Planete} com o
     * {@code nomePlaneta} especificado.
     */
    public Integer aparicoesEmFilmes(String nomePlaneta) {
        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(IntegracaoExternaSWAPIConstants.URL_API)
                .path("planets/")
                .queryParam("search", nomePlaneta)
                .build();

        RestTemplate rt = new RestTemplate();
        
        ResponseEntity<RetornoWS> entity
                = rt.getForEntity(uri.toUriString(), RetornoWS.class);

        if (entity.getStatusCodeValue() != 200) {
            return null;
        } else {
            RetornoWS retorno = entity.getBody();
            if (retorno != null) {
                if (retorno.getResults() != null
                        && !retorno.getResults().isEmpty()) {
                    return retorno.getResults().stream()
                            .map(p -> p.getFilms().size())
                            .findFirst()
                            .get();
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
    }
}
