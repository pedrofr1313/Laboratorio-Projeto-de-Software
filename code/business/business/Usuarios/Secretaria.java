package business.Usuarios;
import java.io.Serializable;
import business.Curso.Curso;
import business.Disciplina.Disciplina;
import java.util.List;

public class Secretaria extends Usuario implements Serializable {
    
    public Secretaria(String nome, int id) {
        super(nome, id);
    }


    public void gerarCurriculo(Curso curso, int periodo, List<Integer> idsDisciplinas) {
        List<Disciplina> disciplinasPorPeriodo = curso.getDisciplinasPorPeriodo(periodo);

        List<Disciplina> disciplinasSelecionadas = disciplinasPorPeriodo.stream()
            .filter(disciplina -> idsDisciplinas.contains(disciplina.getId()))
            .toList();

        curso.setDisciplinas(disciplinasSelecionadas);
    }

    public void gerarCurriculo(){

    }
    public void armazenarInformacoes(){

    }
    public  Curso getCurso()
    {
        return null;
    }

}
