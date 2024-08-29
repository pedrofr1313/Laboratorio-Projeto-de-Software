
package business;
import business.Usuarios.Aluno;
import business.Usuarios.Usuario;
import business.Usuarios.UsuarioDAO;
import business.Usuarios.Secretaria;
import business.Usuarios.Serializacao;
import business.Usuarios.Professor;
import business.Matricula.Matricula;
import business.Turma.Turma;
import business.Disciplina.Disciplina;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.IOException;

public class Main {
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
        MenuProfessor(scanner);
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
               BuscarDisciplinas(scanner); 
                break;
            case 2:
               // ExibirMatricula(scanner);
            default:
                break;
        }

    }while(op>2 || op<0);
   }
   




   public static void CargaDeDadosUsuario() {
    String nomeArquivo = "Usuarios.dat";
    File arquivo = new File(nomeArquivo);

    if (arquivo.exists()) {
        try {
            List<Usuario> usuariosCarregados = Serializacao.carregarUsuarios(nomeArquivo);
            if (usuariosCarregados != null) {
                setUsuarios(usuariosCarregados);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar os usuários: " + e.getMessage());
        }
    } else {
        // Criar o arquivo e adicionar dados iniciais se não existir
        try {
            if (arquivo.createNewFile()) {
                System.out.println("Arquivo criado: " + nomeArquivo);
            } else {
                System.out.println("Arquivo já existe.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao criar o arquivo: " + e.getMessage());
        }

        // Instanciar usuários
        List<Usuario> usuarios = new ArrayList<>();
        
        // Instanciando 8 alunos
        List<Aluno> alunos = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            alunos.add(new Aluno("Aluno" + i, i % 4 + 1, i, null, null)); // Alunos em diferentes períodos
        }

        // Instanciando 6 professores
        List<Professor> professores = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            professores.add(new Professor("Professor" + i, 100 + i));
        }

        // Instanciando 6 secretarias
        List<Secretaria> secretarias = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            secretarias.add(new Secretaria("Secretaria" + i, 200 + i));
        }

        // Criar disciplinas e turmas
        List<Turma> turmas = new ArrayList<>();
        List<Disciplina> disciplinas = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            Professor professor = professores.get(i % professores.size());
            Turma turma = new Turma(i, professor, i % 4 + 1);
            turmas.add(turma);

            // Associando alunos às turmas
            for (Aluno aluno : alunos) {
                if (aluno.getPeriodo() == turma.getPeriodo()) {
                    turma.adicionarAluno(aluno);
                }
            }
        }

        // Criando disciplinas e associando turmas a elas
        for (int i = 1; i <= 3; i++) {
            List<Turma> turmaList = new ArrayList<>(turmas.subList(i * 2 - 2, i * 2)); // Cria uma nova lista a partir da sublist
            Disciplina disciplina = new Disciplina("Disciplina" + i, turmaList, i, i);
            disciplinas.add(disciplina);
        }

        // Associando disciplinas aos professores
        for (Professor professor : professores) {
            professor.setDisciplinas(disciplinas);
        }

        // Adicionando todos os usuários
        usuarios.addAll(alunos);
        usuarios.addAll(professores);
        usuarios.addAll(secretarias);

        // Salvar os dados no arquivo
        try {
            Serializacao.salvarUsuarios(usuarios, nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar os usuários: " + e.getMessage());
        }
        setUsuarios(usuarios);
    }

    // Exibir usuários
    for (Usuario usuario : Usuarios) {
        System.out.println(usuario.getNome());
        System.out.println("ID: " + usuario.getId());
    }
}





     public static void BuscarDisciplinas(Scanner scanner)
     {
        List<Disciplina> disciplinas = AlunoUtilizado.getCurso().getDisciplinasPorPeriodo(AlunoUtilizado.getPeriodo());
        System.out.println("Disciplinas do"+ AlunoUtilizado.getPeriodo() + "disponíveis no curso "+ AlunoUtilizado.getCurso()+":");
        for(Disciplina d: disciplinas)
        {
            System.out.println("Disciplina "+ d.getId()+":" + d.getNome());
        }
        
        int op;
        do{
        System.out.println("O que deseja fazer?\n 1-Matricular-se em uma turma \n 2-Voltar ao menu do Aluno");   
         op = scanner.nextInt();
         scanner.nextLine();
         switch (op) {
            case 1:
                EscolherDisciplina(disciplinas,scanner);
                break;
            case 2:
                MenuAluno(scanner);
            default:
                break;
         }
         if(op>2||op<1)
         {
            System.out.println("Opção inválida, por favor tente novamente\n");
         }
        }while(op>2||op<1);

     }
     
     
     
     
     public static void EscolherDisciplina(List<Disciplina> disciplinas,Scanner scanner)
     {
      Disciplina disciplina = null;
      do{
     System.out.println("Digite o número do id da disciplina pelo qual deseja se matricular\n");
       
      int id = scanner.nextInt();
      
      
      for(Disciplina d:disciplinas)
      {
        if(d.getId()==id)
        {
            disciplina = d;
        }
      }
      if(disciplina==null)
      {
        System.out.println("O id escolhido nao se refere a nenhuma disciplina.");
      }
    }while(disciplina==null);
    RealizarMatricula( disciplina, scanner);
       }
   
   
   
   
   
   
   
   
   
     public static void RealizarMatricula(Disciplina disciplina,Scanner scanner)
   {
    System.out.println("Turmas disponíveis na disciplina \n"+ disciplina.getNome());
    for(Turma t:disciplina.getTurmas())
    {
      System.out.println("Turma "+ t.getId()+ "do professor " + t.getProfessor());
    }
    Turma TurmaEscolhida = null;
    do{
      System.out.println("Digite o número do id da turma pela qual deseja se matricular\n");
        
       int id = scanner.nextInt();
       
       
       for(Turma t :disciplina.getTurmas())
       {
         if(t.getId()==id)
         {
             TurmaEscolhida = t;
         }
       }
       if(TurmaEscolhida==null)
       {
         System.out.println("O id escolhido nao se refere a nenhuma turma da disciplina.");
       }
     }while(TurmaEscolhida==null);
     
     if(AlunoUtilizado.getMatricula().PodeSeMatricular(TurmaEscolhida))
     {
     AlunoUtilizado.getMatricula().addTurma(TurmaEscolhida);
     System.out.println("Matricula");
     }
     else
     {
        System.out.println("Matrícula nao concluida. Possibilidades de erro:\n 1-O aluno ja está matriculado nessa disciplina.\n 2-O limite de turmas optativas foi excedido.\n 3-O limite de materias obrigatorias foi excedido.");
     }
     System.out.println("O que deseja fazer?(Digite uma das opções) \n 1-Voltar ao menu do aluno\n 2-Buscar disciplinas.");
     int op = scanner.nextInt();
     switch (op) {
        case 1:
          MenuAluno(scanner);  
            break;
        case 2:
          BuscarDisciplinas(scanner);
        default:
            break;
     }
     

   }
   
   public static void MenuProfessor(Scanner scanner) {
    System.out.println("Bem-vindo, Professor!");
    System.out.println("1 - Ver disciplinas");
    System.out.println("2 - Ver alunos em disciplina");

    int op = scanner.nextInt();
    scanner.nextLine();
    switch (op) {
        case 1:
            VerDisciplinas(scanner);
            break;
        case 2:
            VerAlunosDisciplina(scanner);
            break;
        default:
            System.out.println("Opção inválida.");
            MenuProfessor(scanner);
            break;
    }

    
}
   
public static void VerDisciplinas(Scanner scanner) {
    List<String> disciplinas = ProfessorUtilizado.visualizarDisciplinas();
    if (disciplinas.isEmpty()) {
        System.out.println("Nenhuma disciplina atribuída.");
    } else {
        System.out.println("Disciplinas ministradas pelo professor " + ProfessorUtilizado.getNome() + ":");
        for (String disciplina : disciplinas) {
            System.out.println(disciplina);
        }
    }
}

public static void VerAlunosDisciplina(Scanner scanner) {
    System.out.println("Digite o ID da disciplina para visualizar os alunos:");
    int idDisciplina = scanner.nextInt();
    scanner.nextLine();

    List<String> alunos = ProfessorUtilizado.visualizarAlunosNaDisciplina(idDisciplina);
    if (alunos.isEmpty()) {
        System.out.println("Nenhum aluno encontrado para esta disciplina.");
    } else {
        System.out.println("Alunos matriculados na disciplina:");
        for (String aluno : alunos) {
            System.out.println(aluno);
        }
    }
    }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}