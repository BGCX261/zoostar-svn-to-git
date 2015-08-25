package net.zoostar.jj.app.model;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name="StockMaster")
@NamedQuery(name=Stock.FIND_STOCK_BY_SYMBOL, query="SELECT s FROM Stock s WHERE s.symbol = :symbol")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Stock implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4685078704144721742L;
	
	static final Logger L = LoggerFactory.getLogger(Stock.class);
	
	public static final String PARAM_SYMBOL = "symbol";
	public static final String FIND_STOCK_BY_SYMBOL = "Stock.findBySymbol";
	
	public Stock() {
		this(null);
	}
	
	public Stock(String symbol) {
		this(null, null);
	}
	
	public Stock(String symbol, String name) {
		this.symbol = symbol;
		this.name = name;
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
		builder.append("Stock [stockId=");
		builder.append(stockId);
		builder.append(", symbol=");
		builder.append(symbol);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
}
