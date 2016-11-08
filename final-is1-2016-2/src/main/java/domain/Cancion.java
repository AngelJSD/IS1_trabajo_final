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
public class Cancion implements BaseEntity<Long>{
	
	@Id
	@SequenceGenerator(name = "cancion_id_generator", sequenceName = "cancion_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cancion_id_generator")
	private Long id;
	
	@Column(length = 64)
	private String nombre;
	
	@ManyToMany(mappedBy = "favoritas")
	private Collection<Usuario> usuarios;
	
	@Column(nullable = false)
	private Date fecha = new Date();
	
	@Column(nullable = false)
	private double calificacion_prom;
	
	@Column(nullable = false)
	private int reproducciones;
	
	@Column(length = 64)
	private String genero;
	
	@ManyToMany(mappedBy = "canciones")
	private Collection<ListaCanciones> listsSongs;

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		
	}
	
	
}
