package br.com.academy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.academy.model.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {

  @Query("select i from Usuario i where i.email = :email")
  public Usuario findByEmail(String email);

  @Query("select j from Usuario j where j.user = :user")
  public Usuario buscarUser(String user);

  @Query("select j from Usuario j where j.user = :user and j.senha = :senha")
  public Usuario buscarLogin(String user, String senha);

  @Query("select k from Usuario k where k.senha = :senha")
  public Usuario buscarSenha(String senha);
}
