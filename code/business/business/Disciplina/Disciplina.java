package business.Disciplina;
import java.util.List;

import business.Curso.Curso;
import business.Turma.Turma;
public class Disciplina{

    private String Nome;
   
    private int NumeroAlunos;
    private int MinimoAlunos;
    private int MaximoAlunos;
    private boolean status=true;
    private boolean Optativa; 
    private List<Turma> Turmas;


    public Disciplina(String nome, boolean status, boolean optativa, List<Turma> turmas) {
        this.Nome = nome; 
        this.status = status;
        this.NumeroAlunos=0;
        this.Optativa = optativa;
        this.Turmas = turmas;
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

}
