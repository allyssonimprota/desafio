/**
 * DesafioController.java
 * Versão: 1.0.0-SNAPSHOT
 * 26/06/2018
 * Copyright (c) 2018.
 * Todos os direitos reservados.
 */
package com.improta.desafio.controller;

import java.util.stream.Stream;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
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

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Desafio realizarDesafio(@RequestBody Stream input) {
		return processarDesafio(input);
	}

	/**
	 * 	1 - Você deverá desenvolver uma API (Restful) simples que receba uma
	 * 		requisição HTTP com uma stream, e encontre o primeiro caractere Vogal, 
	 * 		após uma consoante, onde a mesma é antecessora a uma vogal e que não se repita na stream.
	 * @param stream
	 * @return Desafio
	 */
	private Desafio processarDesafio(Stream input) {
		String stream = "";
		String vogal = "";
		String tempoTotal = "";
		Desafio desafio = new Desafio();

		desafio.setStream(stream);
		desafio.setVogal(vogal);
		desafio.setTempoTotal(tempoTotal);
		return desafio;
	}

}
