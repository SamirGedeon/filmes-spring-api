package br.com.estudo.filmesapi;

import java.math.BigDecimal;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
@Service
@Profile("memoria")
public class FilmeModelMemoria extends ArmazenamentoEmMemoria {  }
