package domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Usuario extends Person implements BaseEntity<Long>{

	@Column(nullable = false)
	private boolean activo;
	
	@OneToMany(mappedBy = "autor")
	private Collection<Playlist> playlists;
	
	@ManyToMany
    @JoinTable(name="cancion_usuario")
	private Collection<Cancion> favoritas;
	
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
