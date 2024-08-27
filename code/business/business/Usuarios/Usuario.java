package business.Usuarios;
import java.io.Serializable;
import business.Curso.Curso;


public abstract class Usuario implements Serializable {
    private String nome;
    private int id;
    
    public Usuario(String nome, int id) {
        this.nome = nome;
        this.id = id;
        
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
      
    


   
    
}