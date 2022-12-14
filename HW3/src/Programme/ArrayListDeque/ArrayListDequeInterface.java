package Programme.ArrayListDeque;

public interface ArrayListDequeInterface<T> {
	public void addFront(T entry);
	public T lookFront();
	public T removeFront();
	public void addBack(T entry);
	public T lookBack();
	public T removeBack();
	public void clear();
	public boolean isEmpty();
	public T[] toArray();
}
