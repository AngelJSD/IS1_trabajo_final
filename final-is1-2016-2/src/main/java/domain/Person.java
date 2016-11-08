package domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
public abstract class Person {

	@Id
	@SequenceGenerator(name = "person_id_generator", sequenceName = "person_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id_generator")
	protected Long id;

	@Column(length = 64)
	private String firstName;

	@Column(length = 64)
	private String lastName;
	
	@Column(length = 64)
	private String correo;
	
	@Column(length = 64)
	private String contraseña;

	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


}
