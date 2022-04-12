package dataflow.abs;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ZeroAbstractSet {
	private final HashMap<String, ZeroAbstractValue> map;

	public ZeroAbstractSet(){
		this.map = new HashMap<String, ZeroAbstractValue>();
	}

	public Boolean hasValue(String variable) {
		return this.map.containsKey(variable);
	}

	public ZeroAbstractValue getValue(String variable) {
		return this.map.get(variable);
	}

	public void setValue(String variable, ZeroAbstractValue value) {
		if (value != null) {
			this.map.put(variable, value);
		}
	}

	public ZeroAbstractSet union(ZeroAbstractSet another) {
		for (Map.Entry<String, ZeroAbstractValue> v : another.map.entrySet()) {
			String key = v.getKey();
			if (this.hasValue(key)) {
				ZeroAbstractValue mergedValue = this.getValue(key).merge(v.getValue());
				this.setValue(key, mergedValue);
			} else {
				this.setValue(key, v.getValue());
			}
			System.out.print(this + "\n");
		}
		return this;
	}

	public void clear() {
		this.map.clear();
	}

	public void putAll(ZeroAbstractSet another) {
		this.map.putAll(another.map);
	}

	@Override
	public String toString() {
		return "ZeroAbstractSet{" + this.map + "}";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ZeroAbstractSet)) return false;
		ZeroAbstractSet that = (ZeroAbstractSet) o;
		return Objects.equals(this.map, that.map);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.map);
	}
}
