package net.zoostar.roughcut.entity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;

@Entity
@Table
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries ({
	@NamedQuery(name="company.retrieveAllByName", query="SELECT company FROM Company company ORDER BY company.lowerName"),
	@NamedQuery(name="company.retrieveByName", query="SELECT company FROM Company company WHERE company.lowerName = :name")
})
public class Company extends AbstractAuditableEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3391533566959400692L;
	
	@Audited
	@Column(nullable=false, length=50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		setLowerName(name);
		this.name = name;
	}
	private String name;

	@Column(unique=true, length=50)
	public String getLowerName() {
		return this.lowerName;
	}
	protected void setLowerName(String name) {
		this.lowerName = name.trim().toLowerCase();
	}
	private String lowerName;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Company [name=").append(name).append(", getId()=")
				.append(getId()).append(", getCreatedBy()=")
				.append(getCreatedBy()).append(", getCreatedDate()=")
				.append(getCreatedDate()).append(", getLastModifiedBy()=")
				.append(getLastModifiedBy()).append(", getLastModifiedDate()=")
				.append(getLastModifiedDate()).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
