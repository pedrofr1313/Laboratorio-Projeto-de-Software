package business.Matricula;
import java.io.Serializable;
import java.util.List;

import business.Curso.Curso;
import business.Disciplina.Disciplina;
import business.Turma.Turma;
import business.Usuarios.Aluno;

public class Matricula implements Serializable {
   
    private List<Turma> turmas;
    
    public void addTurma(Turma turma)
    {
     turmas.add(turma);
    }

    public void cancelarMatricula(Disciplina disciplina) {
        
    }

    public void notificarSistemaCobranca() {

    }
    public Matricula(List<Turma> turmas) {
        this.turmas = turmas;
    }
    public boolean PodeSeMatricular(Turma t)
    {
        int cont1 = 0;
        int cont2=0;
        if(t.isOptativa())
        {
            for(Turma turma: turmas)
            {
               if(turma.isOptativa())
               {
                if(turma.getId()==t.getId())
                {
                 return false;
                }
                cont1++;
               }
            }

            if(cont1<2)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else 
        {
           for(Turma turma: turmas)
           {
            if(!turma.isOptativa())
            {
                if(turma.getId()==t.getId())
                {
                 return false;
                }
                cont2++;
            }
           }

           if(cont2<4)
           {
            return true;
           }
           else
           {
             return false;
           }
        }
        

    }
}