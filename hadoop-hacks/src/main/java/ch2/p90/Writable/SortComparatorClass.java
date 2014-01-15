package ch2.p90.Writable;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class SortComparatorClass extends WritableComparator{

	@SuppressWarnings("rawtypes")
	protected SortComparatorClass(Class<? extends WritableComparable> keyClass,
			boolean createInstances) {
		super(keyClass, createInstances);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		if (a instanceof CustomWritable && b instanceof CustomWritable) {
			Comparable onekey=CustomWritable.class.cast(a).getKey();
			Comparable anotherKey=CustomWritable.class.cast(b).getKey();
			
			int KeyCompare=onekey.compareTo(anotherKey);
			if (KeyCompare!=0) {
				return KeyCompare;
			}
			
			Comparable oneOrder=CustomWritable.class.cast(a).getOrder();
			Comparable anotherOrder=CustomWritable.class.cast(b).getOrder();
			//其次是排序的值进行比较。这次按降序获取，所以用-号返回
			return -oneOrder.compareTo(anotherOrder);
		}
		return super.compare(a, b);
	}

	
	
}
