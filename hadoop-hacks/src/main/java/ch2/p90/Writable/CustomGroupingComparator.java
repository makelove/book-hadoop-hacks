package ch2.p90.Writable;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class CustomGroupingComparator extends WritableComparator{

	@SuppressWarnings("rawtypes")
	protected CustomGroupingComparator(
			Class<? extends WritableComparable> keyClass,boolean createInstances) {
		super(keyClass,createInstances);
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		if (a instanceof CustomWritable && b instanceof CustomWritable) {
			Comparable one=CustomWritable.class.cast(a).getKey();
			Comparable another=CustomWritable.class.cast(b).getKey();
			return one.compareTo(another);
		}
		return super.compare(a, b);
	}
	
	
	
}
