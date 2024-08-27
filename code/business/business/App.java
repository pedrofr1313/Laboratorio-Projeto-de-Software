
package business;
import business.Usuarios.Aluno;
import business.Usuarios.Usuario;
import business.Usuarios.UsuarioDAO;
import business.Usuarios.Secretaria;
import business.Usuarios.Serializacao;
import business.Usuarios.Professor;
import business.Disciplina.Disciplina;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class App {
private static List<Usuario> Usuarios = new ArrayList<>();
private static Aluno AlunoUtilizado;
private static Professor ProfessorUtilizado;
private static Secretaria SecretariaUtilizada;
   

public static Aluno getAlunoUtilizado() {
    return AlunoUtilizado;
}
public static void setAlunoUtilizado(Aluno alunoUtilizado) {
    AlunoUtilizado = alunoUtilizado;
}
public static Professor getProfessorUtilizado() {
    return ProfessorUtilizado;
}
public static void setProfessorUtilizado(Professor professorUtilizado) {
    ProfessorUtilizado = professorUtilizado;
}
public static Secretaria getSecretariaUtilizada() {
    return SecretariaUtilizada;
}
public static void setSecretariaUtilizada(Secretaria secretariaUtilizada) {
    SecretariaUtilizada = secretariaUtilizada;
}
public static void setUsuarios(List<Usuario> usuarios) {
        Usuarios = usuarios;
    }
    public static void main(String[] args) throws IOException {
        CargaDeDadosUsuario();
         Usuario usuarioUtilizado= null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o id do usuario no qual deseja logar");
       int id = scanner.nextInt();
       scanner.nextLine();
       for(Usuario u: Usuarios)
       {
        if(u.getId() == id)
        {

            usuarioUtilizado= u;
            break;
        }
       }

       if(usuarioUtilizado instanceof Aluno)
       {
        setAlunoUtilizado((Aluno) usuarioUtilizado);
        MenuAluno(scanner);
       }
       if(usuarioUtilizado instanceof Professor)
       {
        setProfessorUtilizado((Professor) usuarioUtilizado);
       }
       if(usuarioUtilizado instanceof Secretaria)
       {
        setSecretariaUtilizada((Secretaria) usuarioUtilizado);
       }
 


    }
   
   
   public static void MenuAluno(Scanner scanner)
   {
    System.out.println("Bem vindo Aluno\n");
    System.out.println("O que deseja fazer? \n Digite a opção referente a tarefa que deseja realizar:");
    int op;
    do{
        System.out.println("1 - Buscar disciplinas");
        System.out.println("2-Exibir matrícula realizada");
        op = scanner.nextInt();
        switch (op) {
            case 1:
               BuscarDisciplinas(); 
                break;
            case 2:
                ExibirMatricula();
            default:
                break;
        }

    }while(op>2 || op<0);
   }
   




     public static void CargaDeDadosUsuario()
     {
     
        String nomeArquivo = "Usuario.dat";
        File arquivo = new File(nomeArquivo);
        
        try {
                List<Usuario> usuariosCarregados = Serializacao.carregarUsuarios(nomeArquivo);
                if (usuariosCarregados != null) {
                    setUsuarios(usuariosCarregados);
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Erro ao carregar os usuários: " + e.getMessage());
            }
    
            for (Usuario usuario : Usuarios) {
                System.out.println(usuario.getNome());
                System.out.println("ID:"+ usuario.getId());
            }
    
            if (Usuarios.isEmpty()) {
                // Instanciando 8 alunos
                for (int i = 1; i <= 8; i++) {
                    Usuarios.add(new Aluno("Aluno" + i, i, i % 4 + 1)); // Alunos em diferentes períodos
                }
    
                // Instanciando 6 professores
                for (int i = 1; i <= 6; i++) {
                    Usuarios.add(new Professor("Professor" + i, 100 + i));
                }
    
                for (int i=1; i<=6; i++){
                    Usuarios.add(new Secretaria( "Secretaria" + i, 200 + i));
                }
    
    
                try {
                    Serializacao.salvarUsuarios(Usuarios, nomeArquivo);
                } catch (IOException e) {
                    System.out.println("Erro ao salvar os usuários: " + e.getMessage());
                }
            }
    
     }
     public static void BuscarDisciplinas()
     {
        List<Disciplina> disciplinas = AlunoUtilizado.getCurso().getDisciplinas();
        System.out.println("Disciplinas disponíveis no curso "+ AlunoUtilizado.getCurso()+":");
        for(Disciplina d: disciplinas)
        {
            System.out.println(d.getNome());
        }
     }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
        //     UsuarioDAO DAOusuario = new UsuarioDAO("Usuario.dat");
    //  if(DAOusuario.getAll() != null)
    //  {
    //   setUsuarios(DAOusuario.getAll());
    //   for(Usuario usuario : Usuarios)
    //   {
    //     System.out.println(usuario.getEmail());
    //   }
    //  }

    //  if(Usuarios == null)
    //  {
    //     for (int i = 1; i <= 8; i++) {
    //         Usuarios.add(new Aluno("Aluno" + i, i, "aluno" + i + "@exemplo.com", "senha" + i, i % 4 + 1)); // Alunos em diferentes períodos
    //     }

    //     // Instanciando 6 professores
    //     for (int i = 1; i <= 6; i++) {
    //         Usuarios.add(new Professor("Professor" + i, 100 + i, "professor" + i + "@exemplo.com", "senha" + i));
    //     }

    //     // Instanciando 6 secretarias
    //     for (int i = 1; i <= 6; i++) {
    //         Usuarios.add(new Secretaria("Secretaria" + i, 200 + i, "secretaria" + i + "@exemplo.com", "senha" + i));
    //     }
    //  }
    //  DAOusuario.saveToFile(Usuarios);
    //             System.exit(0);
}