package com.paula.TheNextBookSpring.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.paula.TheNextBookSpring.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Optional<Usuario> findByUsername(String username);
	Optional<Usuario> findByEmail(String email);
}

