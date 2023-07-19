package br.com.academy.enums;

public enum Curso {

  ADMINISTRACAO("Administraçao"),
  INFORMATICA("Informatica"),
  CONTABILIDADE("Contabilidade"),
  PROGRAMACAO("Programacao"),
  INFERMAGEM("Enfermagem");

  private String curso;

  private Curso(String curso){
    this.curso = curso;
  }
}
