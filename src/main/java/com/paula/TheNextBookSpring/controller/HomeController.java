package com.paula.TheNextBookSpring.controller;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paula.TheNextBookSpring.model.Autor;
import com.paula.TheNextBookSpring.model.Libro;
import com.paula.TheNextBookSpring.service.IAutorService;
import com.paula.TheNextBookSpring.service.ICategoriaService;
import com.paula.TheNextBookSpring.service.ILibroService;



@Controller
public class HomeController {
	
	@Autowired
	//@Qualifier("libroServiceJpa") otra forma de definir el Servicio que queremos que utilice
	private ILibroService serviceLibro;
	
	@Autowired
	private IAutorService serviceautor;
	
	@Autowired
	private ICategoriaService serviceCategoria;
	
	@GetMapping("/")
	public String mostrarHome(Model model) {
		
		//Slider
				List<String> listaSlider = new LinkedList<String>();
				
				listaSlider.add("Slider1.png");
				listaSlider.add("Slider2.png");
				listaSlider.add("Slider3.png");
				listaSlider.add("Slider4.png");
				listaSlider.add("Slider5.png");
				
				model.addAttribute("listaSlider", listaSlider);
				
				//Lista para Tarjetas
				List<Libro> Libros = serviceLibro.obtenerLibros();
				List<Autor> autores= serviceautor.obtenerAutores();
				
				model.addAttribute("libros", Libros);
				model.addAttribute("autores", autores);
				
				return "home";
	}
	
	@GetMapping(value = {"/login"})
    public String login(){
        return "login";
    }
	
	@GetMapping(value = {"/registro"})
    public String registro(){
        return "registro";
    }
	
	@GetMapping(value = {"/search/{busqueda}"})
    public String search(@PathVariable("busqueda") String busqueda, Model model){
		List<Libro> Libros = serviceLibro.obtenerLibros();
		List<Libro> listaLibros= new LinkedList<Libro>();
		List<Autor> listaAutores = serviceautor.obtenerAutores();
		
		
		if(busqueda.equals("libros")){	
			model.addAttribute("libros", Libros);
			
		}
		
		if(busqueda.equals("autores")){
			model.addAttribute("autores", listaAutores);
		}
		
		if(serviceautor.obtenerAutorPorNombre(busqueda) != null) {
			for (Iterator<Libro> iterator = Libros.iterator(); iterator.hasNext();) {
				Libro libro = (Libro) iterator.next();
				
				if(libro.getAutor().getNombre().equals(busqueda)) {
					listaLibros.add(libro);
				}
				
			}
			model.addAttribute("libros", listaLibros);
		}
		
		if(serviceCategoria.obtenerCategoriaPorTipo(busqueda) != null) {
			for (Iterator<Libro> iterator = Libros.iterator(); iterator.hasNext();) {
				Libro libro = (Libro) iterator.next();
				
				if(libro.getCategoria().getTipo().equals(busqueda)) {
					listaLibros.add(libro);
				}
				
			}
			model.addAttribute("libros", listaLibros);
		}
		
        return "search";
    }
	
	@GetMapping(value = {"/view/{id}"})
    public String vistaLibro(@PathVariable("id") int idLibro, Model model){
		Libro libro = serviceLibro.obtenerLibroPorId(idLibro);
		
		model.addAttribute("libro", libro);
		
        return "view";
    }
	
	@PostMapping("/saveValuations/{id}")
	public String guardarValoracion(@RequestParam("titulo") String titulo,@RequestParam("puntuacion") int puntuacion, 
			@RequestParam("comentario") String comentario, @PathVariable("id") int idLibro, Model model) {
		
		
		
		return "view/{idLibro}";
	}
}
