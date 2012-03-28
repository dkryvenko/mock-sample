import java.util.List;

public class DefaultSimpleStack<T> implements SimpleStack<T> {

	List<T> adaptee;

	public DefaultSimpleStack(List<T> adaptee) {
		if (adaptee == null)
			throw new IllegalArgumentException();

		this.adaptee = adaptee;
	}

	public void push(T o) {
		adaptee.add(o);
	}

	public T pop() {
		int index = adaptee.size() - 1;
		T element = adaptee.get(index);
		adaptee.remove(index);
		return element;
	}

}
