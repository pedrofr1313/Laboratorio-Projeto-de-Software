package business.Turma;
import java.util.ArrayList;
import java.util.List;

import business.Disciplina.Disciplina;
import business.Usuarios.Aluno;
import business.Usuarios.Professor;

public class Turma {
    private int id;
    private Disciplina disciplina;
    private List<Aluno> alunos;
    private Professor professor;
    private int periodo;


    public Turma(int id, Disciplina disciplina, Professor professor, int periodo) {
        this.id = id;
        this.disciplina = disciplina;
        this.professor = professor;
        this.periodo = periodo;
        this.alunos = new ArrayList<>();
    }
    
    public void removerAluno(){


    }

    public void adicionarAluno(Aluno aluno) {
        if (aluno != null && !alunos.contains(aluno)) {
            alunos.add(aluno);
            }
    }

}
