package br.com.academy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.dao.UsuarioDao;
import br.com.academy.model.Aluno;
import br.com.academy.model.Usuario;

@Controller
public class UsuarioController {

  @Autowired
  private UsuarioDao usuarioRepositorio;

  @GetMapping(value = "/")
  public ModelAndView login() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("login_pasta/login");
    return mv;
  }

  @GetMapping(value = "/cadastro")
  public ModelAndView cadastrar() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("usuario", new Usuario());
    mv.setViewName("login_pasta/cadastro");
    return mv;
  }

  @PostMapping("salvaUsuario")
  public ModelAndView cadastrar(Usuario usuario) {
    ModelAndView mv = new ModelAndView();
    usuarioRepositorio.save(usuario);
    mv.setViewName("redirect:/");
    return mv;
  }
}
