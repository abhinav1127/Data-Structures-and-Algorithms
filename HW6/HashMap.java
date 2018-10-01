//import java.util.*;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.ArrayList;

/**
 * Your implementation of HashMap.
 * 
 * @author Abhinav Tirath
 * @userid atirath6
 * @GTID 903108718
 * @version 1.0
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */
    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        table = (MapEntry<K, V>[]) new MapEntry[initialCapacity];
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Cannot search for a null key"
                    + " or value.");
        }
        if (((double) (size + 1) / table.length) > MAX_LOAD_FACTOR) {
            resizeBackingTable((table.length * 2) + 1);
        }
        int hash = hash(key);
        MapEntry<K, V> curr = table[hash];
        if (curr == null) {
            table[hash] = new MapEntry<>(key, value);
        } else {
            while (curr != null) {
                if (curr.getKey().equals(key)) {
                    V toReturn = curr.getValue();
                    curr.setValue(value);
                    return toReturn;
                }
                curr = curr.getNext();
            }
            curr = new MapEntry<>(key, value, table[hash]);
            table[hash] = curr;
        }
        size++;
        return null;

    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot search for a null key.");
        }
        MapEntry<K, V> prev = table[hash(key)];
        if (prev == null) {
            throw new NoSuchElementException("The key does not exist in"
                    + " this HashMap.");
        }
        if (prev.getKey().equals(key)) {
            V toReturn = prev.getValue();
            table[hash(key)] = prev.getNext();
            size--;
            return toReturn;
        }
        if (prev.getNext() != null) {
            MapEntry<K, V> curr = prev.getNext();
            while (curr != null) {
                if (curr.getKey().equals(key)) {
                    prev.setNext(curr.getNext());
                    size--;
                    return curr.getValue();
                }
                curr = curr.getNext();
                prev = prev.getNext();
            }
        }
        throw new NoSuchElementException("The key does not exist in"
                + " this HashMap.");
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot search for a null key.");
        }
        MapEntry<K, V> curr = table[hash(key)];
        while (curr != null) {
            if (curr.getKey().equals(key)) {
                return curr.getValue();
            }
            curr = curr.getNext();
        }
        throw new NoSuchElementException("The key does not exist.");
    }

    /**
     * Computes and returns the hash of a key
     * @param key the key we are trying to find the hash of
     * @return the hash of the given key
     */
    private int hash(K key) {
        return Math.abs(key.hashCode() % table.length);
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot search for a null key.");
        }
        MapEntry<K, V> curr = table[hash(key)];
        while (curr != null) {
            if (curr.getKey().equals(key)) {
                return true;
            }
            curr = curr.getNext();
        }
        return false;
    }

    @Override
    public void clear() {
        table = (MapEntry<K, V>[]) (new MapEntry[INITIAL_CAPACITY]);
        size = 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    @Override
    public Set<K> keySet() {
        HashSet<K> setOfKeys = new HashSet<>(size);
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                MapEntry<K, V> curr = table[i];
                while (curr != null) {
                    setOfKeys.add(curr.getKey());
                    curr = curr.getNext();
                }
            }
        }
        return setOfKeys;
    }

    @Override
    public List<V> values() {
        ArrayList<V> vals = new ArrayList<>(size);
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                MapEntry<K, V> curr = table[i];
                while (curr != null) {
                    vals.add(curr.getValue());
                    curr = curr.getNext();
                }
            }
        }
        return vals;
    }

    @Override
    public void resizeBackingTable(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Cannot have a negative length");
        } else if (length < size) {
            throw new IllegalArgumentException("Cannot resize to a length"
                    + " smaller than current size.");
        }
        MapEntry<K, V>[] newBacking = (MapEntry<K, V>[]) new MapEntry[length];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                MapEntry<K, V> curr = table[i];
                while (curr != null) {
                    int hash = Math.abs(curr.getKey().hashCode()
                            % newBacking.length);
                    newBacking[hash] = new MapEntry<>(curr.getKey(),
                            curr.getValue(), newBacking[hash]);
                    curr = curr.getNext();
                }
            }
        }
        table = newBacking;
    }
    
    @Override
    public MapEntry<K, V>[] getTable() {
        // DO NOT EDIT THIS METHOD!
        return table;
    }

}
