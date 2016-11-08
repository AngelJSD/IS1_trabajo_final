package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
public abstract class ListaCanciones {
	
	@Id
	@SequenceGenerator(name = "listacanciones_id_generator", sequenceName = "listacanciones_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "listacanciones_id_generator")
	protected Long id;
	
	@Column(length = 64)
	private String nombre;
	
	@Column(nullable = false)
	private Date fecha = new Date();
	
	@ManyToMany
    @JoinTable(name="listaCanciones_cancion")
	private Collection<Cancion> canciones;
	
	
}
