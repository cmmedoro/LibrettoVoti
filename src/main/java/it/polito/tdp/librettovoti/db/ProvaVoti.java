package it.polito.tdp.librettovoti.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProvaVoti {
	
	public void aggiungiVoto(String nome, int punti) {
		String url = "jdbc:mysql://localhost:3306/libretto?user=root&password=MDB.123";
		try {
			Connection conn = DriverManager.getConnection(url);
			String sql = "INSERT INTO voti (Nome, Valutazione) VALUES (?,?);";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1,nome);
			st.setInt(2, punti);
			int res = st.executeUpdate();
			conn.close();
			if(res == 1) {
				System.out.println("Dato correttamente inserito");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {// devo creare connessione fra programma Java e DB.
		ProvaVoti provaVoti = new ProvaVoti();
		provaVoti.aggiungiVoto("Economia Aziendale", 25);
		
		//identifico stringa di connessione
		String url = "jdbc:mysql://localhost:3306/libretto?user=root&password=MDB.123";
		//Connection è un'interfaccia ---> classe che gestirà connessione è dentro il Driver JDBC
		//getConnection è un metodo statico del DriverManager.
		
		//tutte le operazioni JDBC possono lanciare un'eccezione SQLException ---> blocco try-catch
		try {
			Connection conn = DriverManager.getConnection(url); //pattern di FACTORY: chiedo a un metodo di creare la classe
			//creo oggetto Statement per preparare la query; restituisce oggetto di tipo Statement
			Statement st = conn.createStatement(); 
			//Statement è interfaccia ---> FACTORY: la connessione crea oggetto
			String sql = "SELECT * FROM voti";
			//eseguo la query, che viene caricata nella classe ResultSet, che è un'interfaccia.
			ResultSet res = st.executeQuery(sql);
			//devo estrarre dal ResultSet i risultati, poichè esso è un meccanismo attraverso cui posso estrarre un risultato alla volta
			while(res.next()) { //prima chiamata: dentro il ciclo sono nella prima riga
				String nome = res.getString("Nome");
				int voto = res.getInt("Valutazione");
				System.out.println(nome+" "+voto);
			}
			//chiudo lo statement
			st.close();
			
			
			//qui ho aperto una connessione = canale di comunicazione attraverso cui posso mandare query e ricevere risultati
			//cosa che impiega spazio nel mio database, quindi devo sempre liberare poi la risorsa.
			conn.close();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		
	}

}
