package br.com.academy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.academy.model.Aluno;
import java.util.List;

public interface AlunoDao extends JpaRepository<Aluno, Integer> {

  @Query("select i from Aluno i where i.status = 'ATIVO'")
  public List<Aluno> findByStatusAtivo();

  @Query("select i from Aluno i where i.status = 'INATIVO'")
  public List<Aluno> findByStatusInativo();

  @Query("select i from Aluno i where i.status = 'TRANCADO'")
  public List<Aluno> findByStatusTrancado();

  @Query("select i from Aluno i where i.status = 'CANCELADO'")
  public List<Aluno> findByStatusCancelado();

  public List<Aluno> findByNomeContainingIgnoreCase(String nome);
}
