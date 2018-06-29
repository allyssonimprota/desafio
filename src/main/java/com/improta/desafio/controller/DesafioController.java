/**
 * DesafioController.java
 * Versão: 1.0.0-SNAPSHOT
 * 26/06/2018
 * Copyright (c) 2018.
 * Todos os direitos reservados.
 */
package com.improta.desafio.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.improta.desafio.model.Desafio;

/**
 * @author Allysson Improta
 */
@RestController
@RequestMapping("/desafio")
public class DesafioController {

	private static final String VOGAIS = "AEIOU";

	@RequestMapping(value = "{input}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Desafio realizarDesafio(@PathVariable("input") String input) {

		// Contar Tempo
		long tempoInicial = System.currentTimeMillis();

		Desafio desafio = processarDesafio(input);

		// Retornar Tempo
		long tempoFinal = System.currentTimeMillis();

		desafio.setTempoTotal(String.valueOf((tempoFinal - tempoInicial)) + "ms");
		return desafio;
	}

	/**
	 * 1 - Você deverá desenvolver uma API (Restful) simples que receba uma
	 * requisição HTTP com uma stream, e encontre o primeiro caractere Vogal, após
	 * uma consoante, onde a mesma é antecessora a uma vogal e que não se repita na
	 * stream.
	 * @param stream
	 * @return Desafio
	 */
	private Desafio processarDesafio(String input) {
		Desafio desafio = new Desafio();
		String stream = input;
		String vogalLocalizada = "";
		String trinca = null;
		List<String> listaVogais = Arrays.asList(VOGAIS.split(""));
		List<String> vetor = Arrays.asList(input.split("")).stream().map(String::toUpperCase).collect(Collectors.toList());

		Map<String, String> mapa = new HashMap<String, String>();
		Map<String, Integer> mapaVogal = new HashMap<String, Integer>();

		if (vetor.size() > 3) {
			String char1, char2, char3 = null;
			for (int i = 0; i < vetor.size() - 2; i++) {
				char1 = vetor.get(i);
				char2 = vetor.get(i + 1);
				char3 = vetor.get(i + 2);

				if (listaVogais.contains(char3)) {
					Integer qtdVogal = mapaVogal.get(char3);
					if (qtdVogal == null) {
						mapaVogal.put(char3, 1);
					} else {
						mapaVogal.put(char3, qtdVogal + 1);
					}
				}
				if (listaVogais.contains(char1) && !listaVogais.contains(char2) && listaVogais.contains(char3)) {
					trinca = (char1 + char2 + char3);
					mapa.put(trinca, char3);
				}
			}
			for (Map.Entry<String, Integer> entry : mapaVogal.entrySet()) {
				if (entry.getValue() == 1) {
					vogalLocalizada = entry.getKey();
				}
			}
			if ("".equals(vogalLocalizada)) {
				vogalLocalizada = "Não foi possível localizar a vogal.";
			}
		} else {
			vogalLocalizada = "Não foi possível localizar a vogal.";
		}
		desafio.setStream(stream);
		desafio.setVogal(vogalLocalizada);
		return desafio;
	}
}
