/**
 * @author bruel (taken from https://app.box.com/shared/yrlj0takyhjeg1mefacy)
 */
public class Transact {
	public static void main(String[] args) {
		try {       
			Class.forName("org.postgresql.Driver");                
			String url = "jdbc:postgresql://localhost:5432/Ecole";       
			String user = "postgres";       
			String passwd = "batterie";                
			Connection conn = DriverManager.getConnection(url, user, passwd);       
			conn.setAutoCommit(false);       
			Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);       
			String query = "UPDATE professeur SET ...";                
			ResultSet result = state.executeQuery("SELECT * FROM professeur...");       
			result.first();       
			System.out.println("NOM : " + result.getString("prof_nom") 
					+ " - PRENOM : " + result.getString("prof_prenom"));                
			state.executeUpdate(query);                
			result = state.executeQuery("SELECT * FROM professeur ...");       
			result.first();       
			System.out.println("NOM : " + result.getString("prof_nom") 
					+ " - PRENOM : " + result.getString("prof_prenom"));                
			result.close();       
			state.close();             
			} 
		catch (Exception e) {       e.printStackTrace();     }
		}
}
