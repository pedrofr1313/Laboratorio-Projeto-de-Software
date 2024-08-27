package business.Curso;


import java.util.List;
import business.Disciplina.Disciplina;

public class Curso {

    private String Nome;
    private int NumCreditos;
    private List<Disciplina> Disciplinas;
    // public Disciplina BuscarDisciplinas()
    // {
        
    // }
     public Curso(String nome, int numCreditos, List<Disciplina> disciplinas){
        this.Nome = nome;
        this.NumCreditos = numCreditos;
        this.Disciplinas = disciplinas;
     }

    public List<Disciplina> getDisciplinas() {
        return Disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.Disciplinas = disciplinas;
    }

}