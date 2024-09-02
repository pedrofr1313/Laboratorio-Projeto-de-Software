package business.Turma;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import business.Disciplina.Disciplina;
import business.Usuarios.Aluno;
import business.Usuarios.Professor;

public class Turma implements Serializable{
    private int id;
    
    private List<Aluno> alunos;
    private Professor professor;
    private boolean optativa;  
    private int periodo;


    public Turma(int id, Professor professor, int periodo) {
        this.id = id;
        this.professor = professor;
        this.periodo = periodo;
        this.alunos = new ArrayList<>();
        
    }
    

    public void removerAluno(Aluno aluno){
        alunos.remove(aluno);

    }

    public void adicionarAluno(Aluno aluno) {
        if (aluno != null && !alunos.contains(aluno)) {
            alunos.add(aluno);
            }
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
    public boolean isOptativa() {
        return optativa;
    }

    public void setOptativa(boolean optativa) {
        this.optativa = optativa;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public int getPeriodo() {
        return periodo;
    }

   
}
