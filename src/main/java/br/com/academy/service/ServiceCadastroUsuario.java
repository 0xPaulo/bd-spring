package br.com.academy.service;

import java.security.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.academy.Exceptions.CriptoExistsException;
import br.com.academy.Exceptions.EmailExistsException;
import br.com.academy.Exceptions.UserExistsException;
import br.com.academy.Exceptions.ServiceExc;
import br.com.academy.dao.UsuarioDao;
import br.com.academy.model.Usuario;
import br.com.academy.util.Util;

@Service
public class ServiceCadastroUsuario {

  @Autowired
  private UsuarioDao repositorioUsuario;

  public void salvarUsuario(Usuario user) throws Exception {
    try {
      if (repositorioUsuario.findByEmail(user.getEmail()) != null) {
        throw new EmailExistsException("Ja existe um email cadastrado para " + user.getEmail());
      } else if (repositorioUsuario.buscarUser(user.getUser()) != null) {
        throw new UserExistsException("O nome de usuario " + user.getUser() + " ja foi cadastrado.");
      }
      user.setSenha(Util.md5(user.getSenha()));
    } catch (NoSuchAlgorithmException e) {
      throw new CriptoExistsException("Erro na criptografia da senha");
    }
    repositorioUsuario.save(user);
  }
}