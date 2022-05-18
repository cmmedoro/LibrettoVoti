package it.polito.tdp.librettovoti.model;

import java.util.*;

import it.polito.tdp.librettovoti.db.LibrettoDAO;

public class Libretto {
	
	

	public Libretto() {
	}
	
	public boolean add(Voto v) { //delego al DAO l'aggiunta di un voto dal db
		LibrettoDAO dao = new LibrettoDAO();
		boolean result = dao.creaVoto(v);
		return result;
	}
	
	
	public List<Voto> getVoti(){ //chiede al DAO di restituire una lista dei voti
		LibrettoDAO dao = new LibrettoDAO();
		return dao.readAllVoto();
	}

}
