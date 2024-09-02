
package business;
import business.Usuarios.Aluno;
import business.Usuarios.Usuario;
import business.Usuarios.Secretaria;
import business.Usuarios.Serializacao;
import business.Usuarios.Professor;
import business.Matricula.Matricula;
import business.Turma.Turma;
import business.Curso.Curso;
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
private static String nomeArquivo;

   


public static void setNomeArquivo(String nomeArquivo) {
    Main.nomeArquivo = nomeArquivo;
}
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
        MenuSecretaria(scanner);
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
        System.out.println("Fechar programa");
        op = scanner.nextInt();
        switch (op) {
            case 1:
               BuscarDisciplinas(scanner); 
                break;
            case 2:
               ExibirMatricula(scanner);
            case 3:
               fecharProgramaAluno();
            default:
                break;
        }

    }while(op>3 || op<0);
   }
   




   public static void CargaDeDadosUsuario() {
        setNomeArquivo("Arquivo.dat");
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
            try {
                if (arquivo.createNewFile()) {
                    System.out.println("Arquivo criado: " + nomeArquivo);
                } else {
                    System.out.println("Arquivo já existe.");
                }
            } catch (IOException e) {
                System.out.println("Erro ao criar o arquivo: " + e.getMessage());
            }

            List<Usuario> usuarios = new ArrayList<>();

            List<Curso> cursos = new ArrayList<>();
            String[] nomesCursos = {"Engenharia de Software", "Ciência da Computação"};
            int[] creditosCursos = {180, 160};

            for (int i = 0; i < nomesCursos.length; i++) {
                cursos.add(new Curso(nomesCursos[i], creditosCursos[i],null, i));
            }

            List<Aluno> alunos = new ArrayList<>();
            for (int i = 1; i <= 8; i++) {
                Curso curso = cursos.get(i % cursos.size());
                alunos.add(new Aluno("Aluno" + i, i, 1, curso, new Matricula()));
            }

            List<Professor> professores = new ArrayList<>();
            for (int i = 1; i <= 9; i++) {
                professores.add(new Professor("Professor" + i, 100 + i));
            }

            List<Secretaria> secretarias = new ArrayList<>();
            for (int i = 1; i <= 6; i++) {
                secretarias.add(new Secretaria("Secretaria" + i, 200 + i));
            }

            int contador =1 ;
            for (Curso curso: cursos) {
                List<Disciplina> disciplinas = new ArrayList<>();
                for (int j = 1; j <= 8; j++) {
                   
                    List<Turma> turmaList = new ArrayList<>();
                    Disciplina disciplina = new Disciplina("Disciplina de " + curso.getNome(), turmaList, 1, j * contador);
                    disciplinas.add(disciplina);
                    
                    
                }
                curso.setDisciplinas(disciplinas);
                contador ++;
            }

            List<Turma> turmas = new ArrayList<>();
            int cont=0;
            for(Curso curso:cursos)
            {
                int cont2=0;
            for (Disciplina disciplina : curso.getDisciplinas()) {
               
                
                    if(professores.get(cont2)!=null)
                    {
                   Turma turma = new Turma(cont,professores.get(cont2), disciplina.getPeriodo());
                    if(disciplina.getTurmas().isEmpty())
                    {
                        disciplina.addTurma(turma);
                        
                    }
                    professores.get(cont2).addDisciplina(disciplina);
                    cont++;
                    cont2++;
                    }
                   
                
                
                
                
                

            }
        }

            

            usuarios.addAll(alunos);
            usuarios.addAll(professores);
            usuarios.addAll(secretarias);

            try {
                Serializacao.salvarUsuarios(usuarios, nomeArquivo);
            } catch (IOException e) {
                System.out.println("Erro ao salvar os usuários: " + e.getMessage());
            }
            setUsuarios(usuarios);
        }

        for (Usuario usuario : getUsuarios()) {
            System.out.println(usuario.getNome());
            System.out.println("ID: " + usuario.getId());
        }
    }

    public static List<Usuario> getUsuarios() {
        return Usuarios;
    }






public static void ExibirMatricula(Scanner scanner)
{
    Matricula matricula =AlunoUtilizado.getMatricula();
    System.out.println("Turmas matriculadas:");
    for(Turma t: matricula.getTurmas())
    {
        System.out.println("Turma "+t.getId() +" do professor "+ t.getProfessor().getNome());
    }
    int op;
    do{
    System.out.println("Deseja cancelar matrícula em alguma das turmas?\n1-Sim\n2-Não");
    op =scanner.nextInt();
    if(op!=1 && op!=2)
    {
        System.out.println("Opção inválida, tente novamente.");
    }
    }while(op!=1 && op!=2);
    int id;
    if(op==1)
    {
        int validador =0;
        do{
        System.out.println("Digite o id da turma pela qual deseja cancelar sua matricula.");
         id = scanner.nextInt();
        for(Turma t: matricula.getTurmas())
        {
            if(id==t.getId())
            {
                validador++;
            }
        }
        if(validador==0)
        {
            System.out.println("Turma não encontrada.");
        }
        }while(validador==0);
       
       Turma t =null;
       
        for(Turma turma:matricula.getTurmas())
        {
           if(turma.getId()== id)
           {
            t = turma;
            
           }
        }
        if(t==null)
        {
            System.out.println("id inválido!");
        }
        matricula.getTurmas().remove(t);
        notificarSistemaCobranca();
        MenuAluno(scanner);
       
    }
    else{
     MenuAluno(scanner);
    }
}



     public static void BuscarDisciplinas(Scanner scanner)
     {
        List<Disciplina> disciplinas = AlunoUtilizado.getCurso().getDisciplinasPorPeriodo(AlunoUtilizado.getPeriodo());
        System.out.println("Disciplinas do "+ AlunoUtilizado.getPeriodo() + " periodo disponíveis no curso "+ AlunoUtilizado.getCurso().getNome()+":");
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
      System.out.println("Turma "+ t.getId()+ " do professor " + t.getProfessor().getNome());
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
     
     if(AlunoUtilizado.getMatricula().PodeSeMatricular(TurmaEscolhida)&& ChecaLimitedepessoas(TurmaEscolhida)==true)
     {
     AlunoUtilizado.getMatricula().addTurma(TurmaEscolhida);
     addAlunoTurmaProfessor(TurmaEscolhida);

     
     System.out.println("Matricula realizada com sucesso na turma de id " +TurmaEscolhida.getId());
     notificarSistemaCobranca();
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

        case 3: 
        System.out.println("Saindo do programa.");
        default:
            System.out.println("Opção inválida.");
            MenuProfessor(scanner);
            break;
    } while (op != 3);

    
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
    for(Disciplina disciplina:ProfessorUtilizado.getDisciplina())
    {
        System.out.println(disciplina.getNome() + "id: "+ disciplina.getId());
    }
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
    MenuProfessor(scanner);
    }
   
   
   
   
   public static void addAlunoTurmaProfessor(Turma turma)
   {
    int cont=0;
    for(Usuario u :Usuarios)
    {
        if(u instanceof Professor)
        {
            Professor professor = (Professor) u;
            for(Disciplina d:professor.getDisciplina())
            {
              for(Turma t:d.getTurmas())
              {
                if(t.equals(turma))
                {
                    t.adicionarAluno(AlunoUtilizado);
                }
              }
            }
            Usuarios.set(cont,(Usuario) professor);
        }
        cont++;
    }
   }
   
   
   
   public static void MenuSecretaria(Scanner scanner) {
        System.out.println("Bem-vindo, Secretaria!");
        int op;
        do {
            System.out.println("1 - Gerar Currículo");
            System.out.println("2 - Sair");
            op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1:
                    gerarCurriculo(scanner);
                    break;
                case 2:
                    System.out.println("Saindo do programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (op != 2);
    }
   public static void gerarCurriculo(Scanner scanner) {
    for (Usuario usuario : Usuarios) {
        if(usuario instanceof Professor)
        {
            Professor professor = (Professor) usuario;
            System.out.println("Nome do Professor: " + professor.getNome());
            System.out.println("Disciplinas:");
    
            for (Disciplina disciplina : professor.getDisciplina()) {
                for (Turma turma : disciplina.getTurmas()) {
                    int quantidadeAlunos = contador(turma); 
                    System.out.println("ID da Disciplina: " + disciplina.getId() + 
                                       ", Nome: " + disciplina.getNome() + 
                                       ", Turma: " + turma.getId() + 
                                       ", Alunos Matriculados: " + quantidadeAlunos);
                }
            }
        }
       
        
    }
}
    
  public static boolean ChecaLimitedepessoas(Turma turma)
  {
   
    if(contador(turma)<60)
    {
        return true;
    }
    else
    {
        return false;
    }
  }
   
   public static int contador(Turma turma) {
    int cont = 0;
    for (Usuario u : Usuarios) {
        if (u instanceof Aluno) {
            Aluno aluno = (Aluno) u;
            for (Turma t : aluno.getMatricula().getTurmas()) {
                if (turma.getId() == t.getId()) {
                    cont++;
                }
            }
        }
    }
    return cont;
}
   
   
   
   
   
   
   


public static void fecharProgramaAluno()
{
    int index =0;
    for(Usuario u:Usuarios)
    {
      if(u instanceof Aluno)
      {
        if(AlunoUtilizado.getId()==u.getId())
        {
            Usuarios.set(index,(Usuario) AlunoUtilizado );
        }
      }
      index++;
    }
    try {
        Serializacao.salvarUsuarios(Usuarios, nomeArquivo);
       

    } catch (IOException e) {
        System.out.println("Erro ao salvar Usuarios " + e.getMessage());
    }
    System.out.println("Saindo do programa.");
    System.exit(0);
}

public static void notificarSistemaCobranca() {
    System.out.println("Sistema de cobranca notificado");
}
 
}