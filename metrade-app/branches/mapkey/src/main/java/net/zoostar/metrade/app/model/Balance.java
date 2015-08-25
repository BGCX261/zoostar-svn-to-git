package net.zoostar.metrade.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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
@Table(schema="metrade", name="Balance",
	uniqueConstraints={@UniqueConstraint(columnNames={"clientId", "stockId"})
})
public class Balance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 394417521666871375L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getBalanceId() {
		return balanceId;
	}
	protected void setBalanceId(Long balanceId) {
		this.balanceId = balanceId;
	}
	private Long balanceId;

	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="clientId", nullable=false)
	public Client getClient() {
		return this.client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	private Client client;
	
	@ManyToOne(fetch=FetchType.EAGER, optional=false)
	@JoinColumn(name="stockId")
	public Stock getStock() {
		return this.stock;
	}
	public void setStock(Stock stock) {
		if(stock != null) setSymbol(stock.getSymbol());
		this.stock = stock;
	}
	private Stock stock;

	@Column(nullable=false, length=5)
	public String getSymbol() {
		return this.symbol;
	}
	protected void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	private String symbol;
	
	@Column
	public synchronized Integer getQuantity() {
		return quantity;
	}
	public synchronized void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	private Integer quantity = 0;
	
	@Column(scale=4)
	public synchronized Float getAmount() {
		return amount;
	}
	public synchronized void setAmount(Float amount) {
		this.amount = amount;
	}
	private Float amount = 0.0f;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	public synchronized Date getUpdatedTs() {
		return updatedTs;
	}
	public synchronized void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}
	private Date updatedTs;


	public Balance() {
		this(null, null);
	}
	
	public Balance(Client client, Stock stock) {
		if(client != null) setClient(client);
		if(stock != null) setStock(stock);
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
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
		Balance other = (Balance) obj;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
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
		builder.append("Balance [balanceId=").append(balanceId)
				.append(", client=").append(client).append(", stock=")
				.append(stock).append(", quantity=").append(quantity)
				.append(", amount=").append(amount).append(", updatedTs=")
				.append(updatedTs).append("]");
		return builder.toString();
	}
}