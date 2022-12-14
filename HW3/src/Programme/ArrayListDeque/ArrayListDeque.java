package Programme.ArrayListDeque;

import java.util.ArrayList;

public class ArrayListDeque<T> implements ArrayListDequeInterface<T>{
	private ArrayList<T> list;
	
	public ArrayListDeque(ArrayList<T> list2){
		this.list = new ArrayList<T>(list2);
	}
	public ArrayListDeque() {
		this.list = new ArrayList<T>();
	}
	public ArrayListDeque(ArrayListDeque<T> other) {
		this(new ArrayList<T>(other.list));
	}
	
	public ArrayList<T> getContent(){
		return new ArrayList<T>(this.list);
	}
	
	@Override
	public void addFront(T entry) {
		this.list.add(0, entry);
	}
	@Override
	public T lookFront() {
		return this.list.get(0);
	}
	@Override
	public T removeFront() {
		T result = this.list.remove(0);
		return result;
	}
	@Override
	public void addBack(T entry) {
		this.list.add(this.list.size(), entry);
	}
	@Override
	public T lookBack() {
		return this.list.get(this.list.size()-1);
	}
	@Override
	public T removeBack() {
		T result = this.list.remove(this.list.size()-1);
		return result;
	}
	@Override
	public void clear() {
		this.list = new ArrayList<T>();
	}
	@Override
	public boolean isEmpty() {
		return this.list.isEmpty();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		return (T[]) this.list.toArray();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(this.getClass() != obj.getClass())
			return false;
		ArrayListDeque<T> temp = ((ArrayListDeque<T>) obj);
		
		return this.list.equals(temp.getContent());
	}
	
	@Override
	public String toString() {
		String result = "";
		for(T element: this.list) {
			result += element.toString() + "\n";
		}
		return result;
	}
}
