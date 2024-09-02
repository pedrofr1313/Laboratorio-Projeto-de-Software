package business.Usuarios;
import java.io.Serializable;
import business.Curso.Curso;
import business.Matricula.Matricula;

public class Aluno extends Usuario implements Serializable {
    private int periodo;
    private Curso curso;
    private Matricula matricula;
    public Matricula getMatricula() {
        return matricula;
    }
    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }
    public int getPeriodo() {
        return periodo;
    }
    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }
    public Curso getCurso() {
        return curso;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    public Aluno(String nome, int id,  int periodo,Curso curso,Matricula matricula) {
        super(nome, id);
        this.periodo = periodo;
        this.curso = curso;
        this.matricula=matricula;
    }
  
}
