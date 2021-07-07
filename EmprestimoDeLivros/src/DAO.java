
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DAO {
   private static final String url = "jdbc:postgresql://localhost:5432/biblioteca";
    private static final String driver = "org.postgresql.Driver";
    private static final String usuario = "postgres";
    private static final String senha = "mocinha1407";
    Connection conn;
    PreparedStatement ppst;
    
    
    public Statement getConnection() throws SQLException {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, senha);
            return conn.createStatement ();
        }catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }
   
    public boolean incluirUsuario (Usuario usuario) {
        try{
            getConnection();
            String sql = "INSERT INTO usuario (cod,"
                    + "nome, "
                    + "endereco, "
                    + "telefone) "
                    + "VALUES (?,?,?,?)";
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, usuario.getCodigo());
            ppst.setString(2, usuario.getNome());
            ppst.setString(3, usuario.getEndereco());
            ppst.setString(4, usuario.getTelefone());
            
            ppst.executeUpdate();
            ppst.close();
            conn.close();
            return true;
            
        }catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean incluirLivro (Livro livro) {
        try{
            getConnection();
            String sql = "INSERT INTO livro (cod,"
                    + "titulo, "
                    + "autor, "
                    + "genero, "
                    + "disponibilidade) "
                    + "VALUES (?,?,?,?,?)";
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, livro.getCodigo());
            ppst.setString(2, livro.getTitulo());
            ppst.setString(3, livro.getAutor());
            ppst.setString(4, livro.getGenero());
            ppst.setString(5, livro.getDisponibilidade());
            
            ppst.executeUpdate();
            ppst.close();
            conn.close();
            return true;
            
        }catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public ResultSet consultarNomesUsuario (){
                String sql = "SELECT * FROM usuario order by nome";
        try {
            getConnection();
            ppst = conn.prepareStatement (sql);
                ResultSet rs = ppst.executeQuery();
                return rs;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
            }
    public void encerraConexao() throws SQLException {
                ppst.close();
                conn.close();
            }
    
    public ResultSet consultarTitulosLivros (){
                String sql = "SELECT * FROM livro order by titulo";
        try {
            getConnection();
            ppst = conn.prepareStatement (sql);
                ResultSet rs = ppst.executeQuery();
                return rs;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
            }
    
    public boolean removeUsuario (String nome) {
                String sql = "delete from usuario where nome=?";
        try {
            getConnection();
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, nome);
            int r = ppst.executeUpdate();
            ppst.close();
            conn.close();
            if (r == 1){
                return true;
            }else{
                return false;
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                        
                        
            }
    
    public boolean removeTitulosLivros (String nome) {
                String sql = "delete from livro where Titulo=?";
        try {
            getConnection();
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, nome);
            int r = ppst.executeUpdate();
            ppst.close();
            conn.close();
            if (r == 1){
                return true;
            }else{
                return false;
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                        
                        
            }
    
    public boolean alterarUsuario (Usuario usuario, int cod){
         try {
            getConnection();
            String sql = "UPDATE usuario SET Nome=?,"
                    + "Endereco=?, "
                    + "Telefone=? "
                    + "where cod=?";
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, usuario.getNome());
            ppst.setString(2, usuario.getEndereco());
            ppst.setString(3, usuario.getTelefone());
            ppst.setInt(4, cod);

            ppst.executeUpdate();
            ppst.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean alterarTitulosLivros (Livro livro, int cod){
         try {
            getConnection();
            String sql = "UPDATE livro SET Titulo=?,"
                    + "Autor=?, "
                    + "Genero=?, "
                    + "Disponibilidade=? "
                    + "where cod=?";
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, livro.getTitulo());
            ppst.setString(2, livro.getAutor());
            ppst.setString(3, livro.getGenero());
            ppst.setString(4, livro.getDisponibilidade());
            ppst.setInt(5, cod);

            ppst.executeUpdate();
            ppst.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    
}
