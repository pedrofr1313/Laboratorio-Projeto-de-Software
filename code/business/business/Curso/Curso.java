package business.Curso;


import java.io.Serializable;
import java.util.List;
import business.Disciplina.Disciplina;


public class Curso implements Serializable {
    private int id;
    private String Nome;
    private int NumCreditos;
    private List<Disciplina> Disciplinas;
    // public Disciplina BuscarDisciplinas()
    // {
        
    // }
     public Curso(String nome, int numCreditos, List<Disciplina> disciplinas, int id){
        this.Nome = nome;
        this.NumCreditos = numCreditos;
        this.Disciplinas = disciplinas;
        this.id = id;
     }

    public List<Disciplina> getDisciplinas() {
        return Disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.Disciplinas = disciplinas;
    }
    public List<Disciplina> getDisciplinasPorPeriodo(int periodo)
    {
       return Disciplinas.stream()
       .filter(disciplina -> disciplina.getPeriodo() == periodo)
       .toList();
    }
   
    public int getId() {
        return id;
    }

	public String getNome() {
		return Nome;
	}

	public int getNumCreditos() {
		return NumCreditos;
	}

    
   

}