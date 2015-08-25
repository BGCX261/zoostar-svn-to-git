package net.zoostar.metrade.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(schema="metrade", name="Activity",
	uniqueConstraints={@UniqueConstraint(columnNames={"clientId", "stockId", "activityTs"})
})
public class Activity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6251107305868129139L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getActivityId() {
		return activityId;
	}
	protected void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	private Long activityId;
	

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="stockId")
	public Stock getStock() {
		return this.stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	private Stock stock;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getActivityTs() {
		return activityTs;
	}
	public void setActivityTs(Date activityTs) {
		this.activityTs = activityTs;
	}
	private Date activityTs;

	@Column
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	private Integer quantity = 0;
	
	@Column(scale=4)
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	private Float amount = 0.0f;
	
	
	public Activity() {
		this(null, null);
	}
	
	public Activity(Stock stock, Date activityTs) {
		setStock(stock);
		setActivityTs(activityTs);
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((activityTs == null) ? 0 : activityTs.hashCode());
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
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
		Activity other = (Activity) obj;
		if (activityTs == null) {
			if (other.activityTs != null)
				return false;
		} else if (!activityTs.equals(other.activityTs))
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Activity [stock=").append(stock)
				.append(", activityTs=").append(activityTs)
				.append(", quantity=").append(quantity).append(", amount=")
				.append(amount).append("]");
		return builder.toString();
	}
}
