package it.polito.tdp.itunes.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.itunes.db.ItunesDAO;

public class Model {
	private ItunesDAO dao;
	private List<Genre> generi;
	private Graph<Track, DefaultWeightedEdge> grafo;
	private List<Arco> archi;
	private List<Track> best;
	private int nMax;
	
	
	public Model() {
		super();
		this.dao = new ItunesDAO();
		this.generi = dao.getAllGenres();
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.archi = new ArrayList<>();
		
	}
	public List<Genre> getGeneri(){
		return generi;
	}
	
	public void creaGrafo(Genre g) {
		List<Track> tracks = dao.getTracks(g);
		Graphs.addAllVertices(this.grafo, tracks);
		for (Track t1: this.grafo.vertexSet()) {
			for (Track t2: this.grafo.vertexSet()) {
				if (!t1.equals(t2) && t1.getMediaType() == t2.getMediaType()) {
					int peso = t1.getMilliseconds()-t2.getMilliseconds();
					Graphs.addEdgeWithVertices(this.grafo, t1, t2, Math.abs(peso));
					Arco a = new Arco(t1,t2,Math.abs(peso));
					archi.add(a);
				}
				
			}
		}
		
	}
	
	public List<Arco> getMaxDelta(){
		double max = 0.0;
		for (Arco a : this.archi) {
			if (a.getPeso()>max) {
				max = a.getPeso();
			}
		}
		List<Arco> result = new ArrayList<>();
		for (DefaultWeightedEdge e: this.grafo.edgeSet()) {
			if (this.grafo.getEdgeWeight(e)== max) {
				Arco a = new Arco(this.grafo.getEdgeSource(e), this.grafo.getEdgeTarget(e), this.grafo.getEdgeWeight(e));  
				result.add(a);
			}
		}
		
		return result;
	}
	
	public int getV() {
		return this.grafo.vertexSet().size();
	}
	
	public int getA() {
		return this.grafo.edgeSet().size();
	}
	public Set<Track> getVertici(){
		return this.grafo.vertexSet();
	}
	public List<Track> trovaLista(Track t, int memoria) {
		this.best = new ArrayList<>();
		List<Track> parziale = new ArrayList<>();
		this.nMax =0;
		//aggiungo la canzone preferita
		parziale.add(t);
		//creo una lista dove lascio solo le canzoni che hanno lo stesso mediaType 
		//di quella scelta
		
		ConnectivityInspector< Track, DefaultWeightedEdge> ci =new ConnectivityInspector<>(grafo);
		Set <Track> connessi = ci.connectedSetOf(t);
		List<Track> rimanenti = new ArrayList<>(connessi);
		List<Track> copia = new ArrayList<>(connessi);
		//ora rimanenti contiene solo le track nella componente connessa di t
		rimanenti.remove(t); //assicurati che non crei problemi
		//togli le track con un mediaType diverso
		for (Track t1 : copia) {
			if (t.getMediaType() != t1.getMediaType()) {
				rimanenti.remove(t1);
			}
		}
		
		ricorsione(rimanenti, parziale,memoria);
		return best;
	}
	private void ricorsione(List<Track> rimanenti, List<Track> parziale, int memoria) {
		//condizione di uscita 
		if (parziale.size()>= nMax) {
			nMax = parziale.size();
			this.best = new ArrayList<>(parziale);
		}
		List<Track> nuoveRimanenti = new ArrayList<>(rimanenti);
				
		//fai funzione per calcolare i byte di parziale 
		//verifica che la somma di parziale più quelli che si vogliono aggiungere 
		//non superi la dimMax
		for (Track t : rimanenti) {
			if ((calcolaByte(parziale)+t.getBytes())<=memoria && !parziale.contains(t)){
				parziale.add(t);
				nuoveRimanenti.remove(t);
				ricorsione(nuoveRimanenti, parziale, memoria);
				parziale.remove(parziale.size()-1);
			}
			
		}
		
	}
	private int calcolaByte(List<Track> parziale) {
		int tot =0;
		for (Track t : parziale) {
			tot+= t.getBytes();
		}
		return tot;
	}
	
	
	
}
