package business.Usuarios;


import java.io.*;
import java.util.List;

public class Serializacao {

    
    public static void salvarUsuarios(List<Usuario> usuarios, String nomeArquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(usuarios);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Usuario> carregarUsuarios(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            return (List<Usuario>) ois.readObject();
        }
    }
}
