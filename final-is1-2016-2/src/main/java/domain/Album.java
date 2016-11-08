package domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Album extends ListaCanciones implements BaseEntity<Long> {

	@ManyToMany(mappedBy = "albumes")
	private Collection<Artista> autores;
	
	@Override
	public Long getId() {
		return super.id;
	}

	@Override
	public void setId(Long id) {
		super.id = id;

	}

}
