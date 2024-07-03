package it.polito.tdp.itunes.model;

import java.util.Objects;

public class Arco {

	private Track t1;
	private Track t2;
	private double peso;
	
	public Arco(Track t1, Track t2, double peso) {
		super();
		this.t1 = t1;
		this.t2 = t2;
		this.peso = peso;
	}

	public Track getT1() {
		return t1;
	}

	public void setT1(Track t1) {
		this.t1 = t1;
	}

	public Track getT2() {
		return t2;
	}

	public void setT2(Track t2) {
		this.t2 = t2;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	@Override
	public int hashCode() {
		return Objects.hash(peso, t1, t2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arco other = (Arco) obj;
		return Double.doubleToLongBits(peso) == Double.doubleToLongBits(other.peso) && Objects.equals(t1, other.t1)
				&& Objects.equals(t2, other.t2);
	}

	@Override
	public String toString() {
		return  t1.getName() + "****" + t2.getName() + "--->" + peso ;
	}
	
	
	
}
