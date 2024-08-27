package business.Usuarios;
import java.io.Serializable;
import business.Curso.Curso;

public class Secretaria extends Usuario implements Serializable {
    
    public Secretaria(String nome, int id) {
        super(nome, id);
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
