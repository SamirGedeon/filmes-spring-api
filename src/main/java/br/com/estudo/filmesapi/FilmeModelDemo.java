package br.com.estudo.filmesapi;

import java.math.BigDecimal;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
@Service
@Profile("demo")
public class FilmeModelDemo extends ArmazenamentoEmMemoria { public FilmeModelDemo() { criar(new Filme(null, "Interestelar", "Ficcao cientifica", 2014)); } }
