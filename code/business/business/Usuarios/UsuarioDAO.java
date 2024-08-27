package business.Usuarios;
import business.DAO.DAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements DAO<Usuario, Integer>{
    private File file;
    private FileOutputStream fos;
    private ObjectOutputStream outputFile;

    public UsuarioDAO(String filename) throws IOException
    {
        file = new File(filename);
      
        fos = new FileOutputStream(file, true);
        outputFile = new ObjectOutputStream(fos);
    }
    public void add(Usuario usuario) {
        try {
            outputFile.writeObject(usuario);
        } catch (Exception e) {
            System.out.println("ERRO ao gravar usuário no disco!");
            e.printStackTrace();
        }
    }
    public Usuario get(Integer id) {
        Usuario Usuario = null;

        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                Usuario = (Usuario) inputFile.readObject();

                if (id.equals(Usuario.getId())) {
                    return Usuario;
                }
            }
        } catch (Exception e) {
            System.out.println("ERRO ao ler o Usuario com id '" + id + "' do disco!");
            e.printStackTrace();
        }
        return null;
    }
    public void update(Usuario usuario) {
        List<Usuario> usuarios = getAll();
        int index = -1;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId() == usuario.getId()) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            usuarios.set(index, usuario);
        }
        saveToFile(usuarios);
    }

    public List<Usuario> getAll() {
        List<Usuario> Usuarios = new ArrayList<Usuario>();
        Usuario Usuario = null;
        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                Usuario = (Usuario) inputFile.readObject();
                Usuarios.add(Usuario);
            }
        } catch (Exception e) {
            System.out.println("ERRO ao dar getALL!");
            e.printStackTrace();
        }
        return Usuarios;
    }
    public void delete(Usuario Usuario) {
        List<Usuario> Usuarios = getAll();
        int index = -1;
        for (int i = 0; i < Usuarios.size(); i++) {
            if (Usuarios.get(i).getId() == Usuario.getId()) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            Usuarios.remove(index);
        }
        saveToFile(Usuarios);
    }

    public void saveToFile(List<Usuario> Usuarios) {
        try {
            close();
            fos = new FileOutputStream(file, false);
            outputFile = new ObjectOutputStream(fos);

            for (Usuario est : Usuarios) {
                outputFile.writeObject(est);
            }
            outputFile.flush();
        } catch (Exception e) {
            System.out.println("ERRO ao gravar Usuario no disco!");
            e.printStackTrace();
        }
    }

    public void close() throws IOException {
        outputFile.close();
        fos.close();
    }

    @Override
    protected void finalize() throws Throwable {
        this.close();
    }
}


