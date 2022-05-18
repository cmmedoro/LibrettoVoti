package it.polito.tdp.librettovoti.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.librettovoti.model.Voto;

public class LibrettoDAO {
	
	public boolean creaVoto(Voto v) { //qui dentro faccio la INSERT ---> carico voto nuovo nel database
		try {
			Connection conn = DBConnect.getConnection();
			String sql = "INSERT INTO voti (Nome, Valutazione, Data) VALUES (?,?,?);";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, v.getNome());
			st.setInt(2, v.getVoto());
			st.setDate(3, Date.valueOf(v.getData()));
			int res = st.executeUpdate();
			st.close();
			conn.close();
			return(res == 1);
		}catch(SQLException e) {
			System.out.println(e);
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Voto> readAllVoto() {//legge dal db tutti i voti
		try {
			Connection conn = DBConnect.getConnection();
			String sql = "SELECT * FROM voti";
			PreparedStatement st = conn.prepareStatement(sql); 
			
			ResultSet res = st.executeQuery(sql);
			List<Voto> result = new ArrayList<Voto>();
			while(res.next()) { 
				String nome = res.getString("Nome");
				int voto = res.getInt("Valutazione");
				LocalDate dataEsame = res.getDate("Data").toLocalDate();
				result.add(new Voto(nome, voto, dataEsame));
			}
			st.close();
			conn.close(); //importante rilasciare la risorsa quando non serve più
			return result;
		}catch(SQLException e) {
			System.out.println(e);
			e.printStackTrace();
			return null;
		}
	}

	public Voto readVotoByNome(String nome) { //restituisce l'intero oggetto voto
		
		return null;
	}
}
