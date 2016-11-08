package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Artista extends Person implements BaseEntity<Long> {

	@Column
	private boolean activo = true;
	
	@Column
	private boolean verificado = false;
	
	@Column(length = 256)
	private String informacion;

	@ManyToMany
    @JoinTable(name="artista_album")
	private Collection<Album> albumes;
	
	/* TOOO: Relationship between followers
	 * 
	private Collection<Person> seguidores;
	
	private Collection<Person> seguidos;
	
	*/
	
	@Override
	public Long getId() {
		return super.id;
	}

	@Override
	public void setId(Long id) {
		super.id = id;
	}

}
