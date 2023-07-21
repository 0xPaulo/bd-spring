package br.com.academy.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.dao.AlunoDao;
import br.com.academy.model.Aluno;

@Controller
public class AlunoController {
  @Autowired
  private AlunoDao alunorepositorio;

  @GetMapping(value = "/inserirAlunos")
  public ModelAndView InsertAlunas(Aluno aluno) {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("aluno_pasta/formAluno");
    mv.addObject("aluno", new Aluno());
    return mv;
  }

  @PostMapping("InsertAlunos")
  public ModelAndView inserirAluno(@Valid Aluno aluno, BindingResult br) {
    ModelAndView mv = new ModelAndView();
    if (br.hasErrors()) {
      System.out.println("DEU RUIM");
      mv.setViewName("aluno_pasta/formAluno");
      mv.addObject("aluno");
    } else {
      System.out.println("DEU BOM");
      mv.setViewName("redirect:/alunos");
      alunorepositorio.save(aluno);
    }
    return mv;
  }

  @GetMapping(value = "alunos") /* url */
  public ModelAndView ListarAlunos() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("aluno_pasta/listaAlunos"); /* arquivo */
    mv.addObject("listaAlunos", alunorepositorio.findAll());
    return mv;
  }

  @GetMapping(value = "/alterar/{id}")
  public ModelAndView alterar(@PathVariable("id") Integer id) {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("aluno_pasta/alterar");
    Aluno aluno = alunorepositorio.getReferenceById(id);
    mv.addObject("aluno", aluno);
    return mv;
  }

  @PostMapping("/alterar")
  public ModelAndView alterar(Aluno aluno) {
    ModelAndView mv = new ModelAndView();
    alunorepositorio.save(aluno);
    mv.setViewName("redirect:/alunos");
    return mv;
  }

  @GetMapping("/excluir/{id}")
  public String excluirAluno(@PathVariable("id") Integer id) {
    alunorepositorio.deleteById(id);
    return "redirect:/alunos";
  }

  @GetMapping("filtro-alunos")
  public ModelAndView filtroAlunos() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("aluno_pasta/filtroAlunos");
    return mv;
  }

  @GetMapping(value = "alunos-ativos") /* url */
  public ModelAndView ListarAlunosAtivos() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("aluno_pasta/alunosAtivos"); /* arquivo */
    mv.addObject("alunosAtivoObj", alunorepositorio.findByStatusAtivo());
    return mv;
  }

  @GetMapping(value = "alunos-inativos") /* url */
  public ModelAndView ListarAlunosInativos() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("aluno_pasta/alunosInativos"); /* arquivo */
    mv.addObject("alunosInativoObj", alunorepositorio.findByStatusInativo());
    return mv;
  }

  @GetMapping(value = "alunos-cancelados") /* url */
  public ModelAndView ListarAlunosCancelados() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("aluno_pasta/alunosCancelados"); /* arquivo */
    mv.addObject("alunosCanceladosObj", alunorepositorio.findByStatusCancelado());
    return mv;
  }

  @GetMapping(value = "alunos-trancados") /* url */
  public ModelAndView ListarAlunosTrancados() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("aluno_pasta/alunosTrancados"); /* arquivo */
    mv.addObject("alunosTrancadosObj", alunorepositorio.findByStatusTrancado());
    return mv;
  }

  @PostMapping("pesquisar-aluno")
  public ModelAndView pesquisarAluno(@RequestParam(required = false) String nome) {
    ModelAndView mv = new ModelAndView();
    List<Aluno> listaAlunos;
    if (nome == null || nome.trim().isEmpty()) {
      listaAlunos = alunorepositorio.findAll();
    } else {
      listaAlunos = alunorepositorio.findByNomeContainingIgnoreCase(nome);
    }
    mv.addObject("listaDeAlunos", listaAlunos);
    mv.setViewName("aluno_pasta/pesquisa-resultados");
    return mv;
  }
}