package it.polito.tdp.librettovoti.model;

import java.time.LocalDate;

public class Voto {
	private String nome;
	private int valutazione;
	private LocalDate data;
	
	public Voto(String nome, int valutazione, LocalDate data) {
		super();
		this.nome = nome;
		this.valutazione = valutazione;
		this.data = data;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getVoto() {
		return valutazione;
	}
	
	public void setVoto(int valutazione) {
		this.valutazione = valutazione;
	}
	
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public String toString() {
		return nome+": "+this.valutazione+ "("+this.data.toString()+")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + valutazione;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voto other = (Voto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (valutazione != other.valutazione)
			return false;
		return true;
	}

	

	
}
