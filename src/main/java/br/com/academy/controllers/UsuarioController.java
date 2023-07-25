package br.com.academy.controllers;

import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.academy.Exceptions.EmailExistsException;
import br.com.academy.Exceptions.ServiceExc;
import br.com.academy.Exceptions.UserExistsException;
import br.com.academy.model.Aluno;
import br.com.academy.model.Usuario;
import br.com.academy.service.ServiceCadastroUsuario;
import br.com.academy.service.ServiceLogarUsuario;
import br.com.academy.util.Util;

@Controller
public class UsuarioController {

  @Autowired
  private ServiceLogarUsuario serviceLogarUsuario;
  @Autowired
  private ServiceCadastroUsuario serviceUsuario;

  @GetMapping(value = "/")
  public ModelAndView login() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("login_pasta/login");
    mv.addObject("usuario", new Usuario());
    return mv;
  }

  @GetMapping(value = "/cadastro")
  public ModelAndView cadastrar() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("usuario", new Usuario());
    mv.setViewName("login_pasta/cadastro");
    return mv;
  }

  @GetMapping("/index")
  public ModelAndView index() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("home/index");
    mv.addObject("aluno", new Aluno());
    return mv;
  }

  @GetMapping("/erro")
  public ModelAndView erro() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("login_pasta/erro");
    return mv;
  }

  @PostMapping("cadastrar-usuario")
  public ModelAndView cadastrar(Usuario usuario) {
    ModelAndView mv = new ModelAndView();
    try {
      serviceUsuario.salvarUsuario(usuario);
      mv.setViewName("redirect:/");
    } catch (EmailExistsException e) {
      mv.addObject("emailError", e.getMessage());
      mv.setViewName("login_pasta/cadastro");
    } catch (UserExistsException e) {
      mv.addObject("emailError", e.getMessage());
      mv.setViewName("login_pasta/cadastro");
    } catch (Exception e) {
      mv.setViewName("login_pasta/erro");
    }
    return mv;
  }

  @PostMapping("/login")
  public String fazerLogin(@ModelAttribute("usuario") Usuario usuario, Model model, HttpSession session)
      throws NoSuchAlgorithmException {
    if (serviceLogarUsuario.validarCredenciais(usuario.getUser(), usuario.getSenha())) {
      session.setAttribute("usuarioLogado", usuario.getUser());
      return "redirect:/index";
    } else {
      model.addAttribute("mensagemErro", "Usuário ou senha inválidos. Tente novamente.");
      return "login_pasta/login";
    }
  }

  @PostMapping("/logout")
  public ModelAndView logout(HttpSession session) {
    session.invalidate();
    return login();
  }
}

// @PostMapping("/login")
// public ModelAndView login(@Valid Usuario usuario, BindingResult br,
// HttpSession session)
// throws NoSuchAlgorithmException, ServiceExc {
// ModelAndView mv = new ModelAndView();
// mv.addObject("usuario", new Usuario());
// if (br.hasErrors()) {
// mv.setViewName("login_pasta/login");
// }
// Usuario userLogin = serviceLogarUsuario.Logar(usuario.getUser(),
// Util.md5(usuario.getSenha()));
// if (userLogin == null) {
// mv.addObject("msg", "Usuario nao encontrado. Tente novamente");
// } else {
// session.setAttribute("usuarioLogado", userLogin);
// return index();
// }
// return mv;
// }

// }
