package map;

import java.util.ArrayList;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private Entry<K,V>[] table;
	private int size;
	
	/** Constructs an empty hashmap with the default initial capacity (16)
	and the default load factor (0.75). */
	public SimpleHashMap() {
		table = (Entry<K,V>[]) new Entry[16];
		size = 0;
	}
	
	/** Constructs an empty hashmap with the specified initial capacity
	and the default load factor (0.75). */
	public SimpleHashMap(int capacity) {
		table = (Entry<K,V>[]) new Entry[capacity];
		size = 0;
	}


	public String show() {
		Entry<K,V> first;
		for (int i=0; i<table.length; i++) {
			first=table[i];
			System.out.print(i + " ");
			while (first != null) {
				System.out.print(first.toString() + " ");
				first=first.next;
			}
			System.out.println(" ");
		}
		return "";
	}
	
	private int index(K key) {
		return Math.abs(key.hashCode() % table.length);
	}
	
	private Entry<K,V> find(int index, K key) {
		Entry<K,V> first = table[index];
		while (first != null) {
			if (first.getKey().equals(key)) {
				return first;
			}
			first=first.next;
		}
		return null;
	}
	
	@Override
	public V get(Object arg0) {
		K key = (K) arg0;
		for (int i=0;i<table.length;i++) {
			
			if (find(i,key) != null) {
				return find(i,key).getValue();
			}
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		if (size==0) {
			return true;
		}
		return false;
	}

	@Override
	public V put(K key, V value) {
		Entry<K,V> entry = new Entry(key, value);
		int index = index(key);
		Entry<K,V> first = table[index];
		if (find(index,key)==null) {
			entry.next=first;
			table[index]=entry;
			size++;
			if (size/table.length > 0.75) {
				rehash();
			}
			return null;
		}
		else {
			V oldvalue = find(index,key).getValue();
			find(index,key).setValue(value);
			return oldvalue;
		}
		
	}
	
	private void rehash() {
		Entry<K,V>[] rehashedtable = (Entry<K,V>[]) new Entry[table.length*2];
		ArrayList<Entry<K,V>> tableentries = new ArrayList<Entry<K,V>>();
		Entry<K,V> first;
		for (int i=0; i<table.length; i++) {
			first=table[i];
			while (first != null) {
				tableentries.add(first);
				first=first.next;
			}
			
		}
		table=rehashedtable;
		size=0;
		for (int i=0;i<tableentries.size();i++) {
			put(tableentries.get(i).getKey(),tableentries.get(i).getValue());
		}
	}

	@Override
	public V remove(Object arg0) {
		K key = (K) arg0;
		int index = index(key);
		Entry<K,V> first=table[index];
		if (first == null) {
			return null;
		}
		else if (first.getKey().equals(key)) {
			table[index] = first.next;
			size=size-1;
			return first.getValue();
		}
		else {
			while (first != null && find(index,key) != null) {
				if (first.next.getKey().equals(key)) {
					Entry<K,V> removedentry=first.next;
					first.next=removedentry.next;
					size=size-1;
					return removedentry.getValue();
				}
				first=first.next;
			}
		}
		return null;
	}

	@Override
	public int size() {
		return size;
	}
	
	public static class Entry<K, V> implements Map.Entry<K, V>{
		private K key;
		private V value;
		private Entry<K,V> next;
		
		public Entry(K key, V value) {
			this.key=key;
			this.value=value;
			next=null;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			V oldvalue=this.value;
			this.value=value;
			return oldvalue;
		}
		
		@Override
		public String toString() {
			return getKey() + " = " + getValue();
		}

	}

}
