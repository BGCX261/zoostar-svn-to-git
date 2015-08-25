package net.zoostar.roughcut.entity.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.domain.Auditable;

@MappedSuperclass
public abstract class AbstractAuditableEntity extends AbstractPersistableEntity implements Auditable<String, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AbstractAuditableEntity() {
		 setCreatedDate(new DateTime());
	}
	
	@Override
	@Column(nullable=false, length=50)
	public String getCreatedBy() {
		return createdBy;
	}
	@Override
	public void setCreatedBy(String createdBy) {
		setLastModifiedBy(createdBy);
		this.createdBy = createdBy;
	}
	private String createdBy;

	@Override
	@Column(nullable=false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	public DateTime getCreatedDate() {
		return createdDate;
	}
	@Override
	public void setCreatedDate(DateTime createdDate) {
		setLastModifiedDate(createdDate);
		this.createdDate = createdDate;
	}
	private DateTime createdDate;

	@Override
	@Column(nullable=false, length=50)
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	@Override
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	private String lastModifiedBy;

	@Override
	@Column(nullable=false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	public DateTime getLastModifiedDate() {
		return lastModifiedDate;
	}
	@Override
	public void setLastModifiedDate(DateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	private DateTime lastModifiedDate;
}