package business.Usuarios;
import java.io.Serializable;
import business.Curso.Curso;

public class Aluno extends Usuario implements Serializable {
    private int periodo;
    private Curso curso;
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
    public Aluno(String nome, int id,  int periodo,Curso curso) {
        super(nome, id);
        this.periodo = periodo;
        this.curso = curso;
    }
    public void buscarDisciplina(){

        
    }
}
