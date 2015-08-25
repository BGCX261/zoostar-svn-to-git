package net.zoostar.roughcut.entity.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

@MappedSuperclass
public abstract class AbstractPersistableEntity implements Persistable<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5733940410292243457L;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid2")
	@Column(nullable=false, unique=true, length=36)
	@Override
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String id;

	@Transient
	@Override
	public boolean isNew() {
		return this.id == null;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AbstractBaseEntity [id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}
}
