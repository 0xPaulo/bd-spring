package br.com.academy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.academy.Exceptions.ServiceExc;
import br.com.academy.dao.UsuarioDao;
import br.com.academy.model.Usuario;

@Service
public class ServiceLogarUsuario {

  @Autowired
  private UsuarioDao userRepository;

  public Usuario Logar(String user, String senha) throws ServiceExc {
    Usuario userLogin = userRepository.buscarLogin(user, senha);
    return userLogin;
  }
}
