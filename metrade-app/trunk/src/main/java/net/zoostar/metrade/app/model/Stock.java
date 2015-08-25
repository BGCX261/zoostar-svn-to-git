package net.zoostar.metrade.app.model;

import static net.zoostar.metrade.app.model.NamedQueryConstants.FIND_STOCK_BY_SYMBOL;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="StockMaster")
@NamedQuery(name=FIND_STOCK_BY_SYMBOL, query="SELECT s FROM Stock s WHERE s.symbol = :symbol")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Stock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8407938662711520614L;

	public Stock() {
		this(null);
	}
	
	public Stock(String symbol) {
		this(symbol, null);
	}
	
	public Stock(String symbol, String name) {
		if(symbol != null) {
			setSymbol(symbol);
			if(name != null)
				setName(name);
		}
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getStockId() {
		return stockId;
	}
	protected void setStockId(Long stockId) {
		this.stockId = stockId;
	}
	private Long stockId;
	
	
	@Column(nullable=false, unique=true, length=5)
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	private String symbol;
	
	
	@Column(length=50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String name;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
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
		Stock other = (Stock) obj;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Stock [symbol=").append(symbol).append(", name=")
				.append(name).append("]");
		return builder.toString();
	}
}
