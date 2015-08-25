package net.zoostar.metrade.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;

@MappedSuperclass
public class DefaultAuditable implements Auditable {
	
	public static final String DEFAULT_CREATED_BY = "constructor";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	@Column(nullable=false, length=50)
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		setUpdatedTs(new Date());
		this.updatedBy = updatedBy;
	}
	private String updatedBy;

	
	@Override
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdatedTs() {
		return updatedTs;
	}
	protected void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}
	private Date updatedTs;
	

	@Override
	@Column(nullable=false, length=50)
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		setCreatedTs(new Date());
		setUpdatedBy(createdBy);
		this.createdBy = createdBy;
	}
	private String createdBy;

	
	@Override
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedTs() {
		return createdTs;
	}
	protected void setCreatedTs(Date createdTs) {
		setUpdatedTs(createdTs);
		this.createdTs = createdTs;
	}
	private Date createdTs;
	
	public DefaultAuditable() {
		this(DEFAULT_CREATED_BY);
	}
	
	public DefaultAuditable(String createdBy) {
		if(StringUtils.isBlank(createdBy))
			throw new NullPointerException("\"Created By\" audit column cannot be NULL!");
		setCreatedBy(createdBy);
	}
}
