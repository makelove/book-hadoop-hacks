package ch2.p90.Writable;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 生成PartitionerClass
 * @author PLAYMORE
 *
 */
public class CustomPartitioner extends Partitioner<CustomWritable, Writable>{

	@Override
	public int getPartition(CustomWritable key, Writable value,
			int numPartitions) {
		return Math.abs(key.getKey().hashCode())%numPartitions;
	}

}
