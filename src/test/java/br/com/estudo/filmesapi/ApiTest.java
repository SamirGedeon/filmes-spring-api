package br.com.estudo.filmesapi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
class ApiTest {
    @Autowired MockMvc mvc;
    @Autowired com.fasterxml.jackson.databind.ObjectMapper json;
    @Test void crudFiltroERegras() throws Exception {
        String body="{\"titulo\": \"Interestelar\", \"genero\": \"Ficcao cientifica\", \"ano\": 2014}";
        String response=mvc.perform(post("/filmes").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isCreated()).andExpect(header().exists("Location")).andReturn().getResponse().getContentAsString();
        long id=json.readTree(response).get("id").asLong();
        mvc.perform(get("/filmes/"+id)).andExpect(status().isOk()).andExpect(jsonPath("$.titulo").value("Interestelar"));
        mvc.perform(get("/filmes").param("genero","FICCAO CIENTIFICA")).andExpect(status().isOk()).andExpect(jsonPath("$[0].id").value(id));
        
        mvc.perform(put("/filmes/"+id).contentType(MediaType.APPLICATION_JSON).content("{\"titulo\": \"Interestelar Atualizado\", \"genero\": \"Ficcao cientifica\", \"ano\": 2014}")).andExpect(status().isOk()).andExpect(jsonPath("$.titulo").value("Interestelar Atualizado"));
        mvc.perform(delete("/filmes/"+id)).andExpect(status().isNoContent()).andExpect(content().string(""));
        mvc.perform(get("/filmes/"+id)).andExpect(status().isNotFound());
        mvc.perform(delete("/filmes/"+id)).andExpect(status().isNotFound());
    }
    @Test void validacaoEContratoHttp() throws Exception {
        mvc.perform(post("/filmes").contentType(MediaType.APPLICATION_JSON).content("{}")).andExpect(status().isBadRequest()).andExpect(jsonPath("$.campos").isArray());
        mvc.perform(post("/filmes").contentType(MediaType.APPLICATION_JSON).content("{")).andExpect(status().isBadRequest());
        mvc.perform(get("/filmes/abc")).andExpect(status().isBadRequest());
        mvc.perform(post("/filmes").contentType(MediaType.TEXT_PLAIN).content("teste")).andExpect(status().isUnsupportedMediaType());
        mvc.perform(get("/filmes").accept(MediaType.TEXT_HTML)).andExpect(status().isNotAcceptable());
    }
}
