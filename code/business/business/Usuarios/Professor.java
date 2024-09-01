package business.Usuarios;
import business.Turma.Turma;
import business.Disciplina.Disciplina;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;
import business.Curso.Curso;

public class Professor extends Usuario implements Serializable{
    private List<Disciplina> Disciplina;

    public Professor(String nome, int id ) {
        super(nome, id );
        this.Disciplina = new ArrayList<>();
    }
    public void addDisciplina(Disciplina d)
    {
        Disciplina.add(d);
    }
    public void visualizarAlunos(){

        
    }
    public List<Disciplina> getDisciplina() {
        return Disciplina;
    }
    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.Disciplina = disciplinas;
    }   

    public List<String> visualizarDisciplinas() {
        return Disciplina.stream()
                .map(d -> "ID: " + d.getId() + ", Nome: " + d.getNome())
                .collect(Collectors.toList());
    }
    
    public List<String> visualizarAlunosNaDisciplina(int disciplinaId) {
        Optional<Disciplina> disciplinaOpt = Disciplina.stream()
                .filter(d -> d.getId() == disciplinaId)
                .findFirst();

        if (disciplinaOpt.isPresent()) {
            Disciplina disciplina = disciplinaOpt.get();
            List<String> alunos = disciplina.getTurmas().stream()
                    .flatMap(turma -> turma.getAlunos().stream())
                    .map(aluno -> "ID: " + aluno.getId() + ", Nome: " + aluno.getNome())
                    .distinct()
                    .collect(Collectors.toList());
            return alunos;
        } else {
            return List.of("Disciplina não encontrada.");
        }
    }
   


}
