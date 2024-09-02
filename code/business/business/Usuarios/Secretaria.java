package business.Usuarios;
import java.io.Serializable;
import business.Curso.Curso;
import business.Disciplina.Disciplina;
import java.util.List;

public class Secretaria extends Usuario implements Serializable {
    
    public Secretaria(String nome, int id) {
        super(nome, id);
    }

    public  Curso getCurso()
    {
        return null;
    }

}
