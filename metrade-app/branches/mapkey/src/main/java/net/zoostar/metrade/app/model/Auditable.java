package net.zoostar.metrade.app.model;

import java.io.Serializable;
import java.util.Date;

public interface Auditable extends Serializable {
	public String getUpdatedBy();
	public Date getUpdatedTs();
	public String getCreatedBy();
	public Date getCreatedTs();
}
