package com.cmazxiaoma.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/9/3 9:19
 */
public class MyLru02<K, V> {

    private int maxSize;
    private Map<K, Entry<K, V>> map;
    private Entry<K, V> head;
    private Entry<K, V> tail;

    public MyLru02(int maxSize) {
        this.maxSize = maxSize;
        map = new HashMap<>();
    }

    public void put(K key, V value) {
        Entry<K, V> entry = new Entry<>();
        entry.key = key;
        entry.value = value;
        afterEntryInsertion(entry);
        map.put(key, entry);

        if (map.size() > maxSize) {
            map.remove(tail.key);
            afterEntryRemoval(tail);
        }
    }

    public void afterEntryInsertion(Entry<K, V> entry) {
        if (entry != null) {
            if (head == null) {
                head = entry;
                tail = head;
                return;
            }

            // if entry is not head
            if (head != entry) {
                entry.after = head;
                entry.before = null;
                head.before = entry;
                head = entry;
            }
        }
    }

    public void afterEntryRemoval(Entry<K, V> entry) {
        if (entry != null) {
            Entry<K, V> p = entry, b = p.before, a = p.after;
            p.before = p.after = null;

            if (b == null) {
                head = a;
            } else {
                b.after = a;
            }

            if (a == null) {
                tail = b;
            } else {
                a.before = b;
            }
        }
    }

    public void afterEntryAccess(Entry<K, V> entry) {
        Entry<K, V> first;

        if ((first = head) != entry) {
            Entry<K, V> p = entry, b = p.before, a = p.after;
            p.before = p.after = null;

            if (b == null) {
                first = a;
            } else {
                b.after = a;
            }

            if (a == null) {
                tail = b;
            } else {
                a.before = b;
            }

            if (first == null) {
                tail = p;
            } else {
                p.after = first;
                first.before = p;
            }

            head = p;
        }
    }

    public void remove(K key) {
        Entry<K, V> entry = this.getEntry(key);
        afterEntryRemoval(entry);
    }

    public V get(K key) {
        Entry<K, V> entry = this.getEntry(key);

        if (entry == null) {
            return null;
        }
        afterEntryAccess(entry);
        return entry.value;
    }


    private Entry<K, V> getEntry(K key) {
        Entry<K, V> entry = map.get(key);

        if (entry == null) {
            return null;
        }

        return entry;
    }

    @Override
    public String toString() {
        Entry<K, V> p = head;
        StringBuffer sb = new StringBuffer();

        while(p != null) {
            sb.append(String.format("%s:%s", p.key, p.value));
            sb.append(" ");
            p = p.after;
        }

        return sb.toString();
    }

    static final class Entry<K, V> {
        private Entry<K, V> before;
        private Entry<K, V> after;
        private K key;
        private V value;
    }

    public static void main(String[] args) {
        MyLru02<String, String> map = new MyLru02<>(5);
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");
        map.put("5", "5");
        System.out.println(map.toString());

        map.put("6", "6");
        map.get("2");
        map.put("7", "7");
        map.get("4");

        System.out.println(map.toString());
    }
}
