package net.zoostar.commons.rule;

public interface BusinessRule<T> {
	public void verify(T object) throws BusinessException;
}
