package io.github.lijinhong11.pylonsdelight.util.map;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * The fast map for floor key find
 * @param <V> the value type
 */
public class FastFloorKeyMap<V> implements Map<Integer, V> {
    private final Map<Integer, V> map;
    private int[] sortedKeys = new int[0];

    public FastFloorKeyMap() {
        this(new HashMap<>());
    }

    public FastFloorKeyMap(Map<Integer, V> map) {
        this.map = map;
    }

    public Integer floorKey(int target) {
        int left = 0, right = sortedKeys.length - 1;
        Integer result = null;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (sortedKeys[mid] <= target) {
                result = sortedKeys[mid];
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    public Integer ceilingKey(int target) {
        int left = 0, right = sortedKeys.length - 1;
        Integer result = null;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (sortedKeys[mid] >= target) {
                result = sortedKeys[mid];
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    @Override
    public V put(Integer key, V value) {
        V old = map.put(key, value);
        if (old == null) {
            int idx = Arrays.binarySearch(sortedKeys, key);
            if (idx < 0) idx = -idx - 1;
            int[] newKeys = new int[sortedKeys.length + 1];
            System.arraycopy(sortedKeys, 0, newKeys, 0, idx);
            newKeys[idx] = key;
            System.arraycopy(sortedKeys, idx, newKeys, idx + 1, sortedKeys.length - idx);
            sortedKeys = newKeys;
        }
        return old;
    }

    @Override
    public V remove(Object key) {
        if (!(key instanceof Integer)) return null;
        V old = map.remove(key);
        if (old != null) {
            int idx = Arrays.binarySearch(sortedKeys, (Integer) key);
            if (idx >= 0) {
                int[] newKeys = new int[sortedKeys.length - 1];
                System.arraycopy(sortedKeys, 0, newKeys, 0, idx);
                System.arraycopy(sortedKeys, idx + 1, newKeys, idx, sortedKeys.length - idx - 1);
                sortedKeys = newKeys;
            }
        }
        return old;
    }

    @Override
    public void clear() {
        map.clear();
        sortedKeys = new int[0];
    }

    @Override public int size() { return map.size(); }
    @Override public boolean isEmpty() { return map.isEmpty(); }
    @Override public boolean containsKey(Object key) { return map.containsKey(key); }
    @Override public boolean containsValue(Object value) { return map.containsValue(value); }
    @Override public V get(Object key) { return map.get(key); }
    @Override public void putAll(Map<? extends Integer, ? extends V> m) {
        for (Entry<? extends Integer, ? extends V> e : m.entrySet()) put(e.getKey(), e.getValue());
    }
    @Override public @NotNull Set<Integer> keySet() { return Collections.unmodifiableSet(map.keySet()); }
    @Override public @NotNull Collection<V> values() { return Collections.unmodifiableCollection(map.values()); }
    @Override public @NotNull Set<Entry<Integer, V>> entrySet() { return Collections.unmodifiableSet(map.entrySet()); }
}
