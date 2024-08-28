package business.Disciplina;
import java.util.List;

import business.Curso.Curso;
import business.Turma.Turma;
public class Disciplina{

    private String Nome;
    private int id;
    private int periodo;
    private List<Turma> Turmas;



    public Disciplina(String nome, List<Turma> turmas, int periodo, int id) {
        this.Nome = nome; 
        this.Turmas = turmas;
        this.periodo= periodo;
        this.id= id;
    }
    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public void verificarDisciplina()
    {

    }
    public void ExibirTurmas()
    {

    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPeriodo() {
        return periodo;
    }
    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }
    public List<Turma> getTurmas() {
        return Turmas;
    }
    public void setTurmas(List<Turma> turmas) {
        Turmas = turmas;
    }

    }
