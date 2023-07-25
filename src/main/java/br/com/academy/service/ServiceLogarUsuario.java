package br.com.academy.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.academy.Exceptions.ServiceExc;
import br.com.academy.dao.UsuarioDao;
import br.com.academy.model.Usuario;
import br.com.academy.util.Util;

@Service
public class ServiceLogarUsuario {
  @Autowired
  private UsuarioDao usuarioDAO;

  public boolean validarCredenciais(String username, String password) throws NoSuchAlgorithmException {
    Usuario usuario = usuarioDAO.buscarUser(username);

    // Verifica se o usuário existe e se a senha corresponde
    return usuario != null && (usuario.getSenha()).equals(Util.md5(password));
  }
}

// public Usuario Logar(String user, String senha) throws ServiceExc {
// Usuario userLogin = userRepository.buscarLogin(user, senha);
// return userLogin;
// }
// }
