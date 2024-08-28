package business.Usuarios;
import business.Turma.Turma;
import business.Disciplina.Disciplina;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;
import business.Curso.Curso;

public class Professor extends Usuario{
    private List<Disciplina> Disciplina;

    public Professor(String nome, int id ) {
        super(nome, id );
    }
    public void visualizarAlunos(){

    }
    
    
   


}
