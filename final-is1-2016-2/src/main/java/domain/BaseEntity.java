package domain;

public interface BaseEntity<K> {
	K getId();
	void setId(K id);
}
