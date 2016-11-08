package domain;

import java.util.Collection;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

public class Playlist implements BaseEntity<Long> {
	
	@Id
	@SequenceGenerator(name = "playlist_id_generator", sequenceName = "playlist_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "playlist_id_generator")
	private Long id;
	
	
	private Collection<Long> songsId;
	
	public void addSong(Cancion song){
		songsId.add(song.getId());
	}
	
	public void deleteSong(Cancion song){
		songsId.remove(song.getId());
	}
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
		
	}
	
}
