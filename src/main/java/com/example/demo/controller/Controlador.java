package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Persona;
import com.example.demo.domain.Saludo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class Controlador {
	@Value("${customprop.vercambiosdeconfig}")
	private String vercambiosdeconfig;
	
	@Autowired
	Environment environment;

	@RequestMapping(value = "/hola", method = RequestMethod.POST)
	public Saludo holaMundo(@RequestBody Persona persona) throws IOException {
		if (persona.getNombre().equals("error")) {
			throw new IOException("Esto es un error provocado para probar un fallback");
		}
		Saludo saludo = new Saludo();
		saludo.setFrase("" + vercambiosdeconfig + " PUERTO: " + environment.getProperty("local.server.port"));
		saludo.setPersona(persona);
		log.info("Controlador2");
		return saludo;
	}
}
