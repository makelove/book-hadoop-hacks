package ch2.p95.ReduceSideJoin;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

public class IntPair  implements Writable{

	private Text key;
	private IntWritable order;
	private String hoge;
	
	public IntPair(Integer valueOf, int i) {
		
	}

	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		
	}

	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		
	}

	public int getFirst() {
		
		return 0;
	}

}
