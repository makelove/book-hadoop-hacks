package ch2.p90.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

/**
 * 自定义Writable类型
 * @author PLAYMORE
 *
 */
public class CustomWritable implements WritableComparable<CustomWritable>{
	
	private Text key;
	private IntWritable order;
	private String hoge;
	

	public CustomWritable(Text foo, IntWritable bar, String hoge) {
		super();
		this.key = foo;
		this.order = bar;
		this.hoge = hoge;
	}

	
	public void readFields(DataInput in) throws IOException {
		key.readFields(in);
		order.readFields(in);
		hoge=in.readUTF();
	}

	public void write(DataOutput out) throws IOException {
		key.write(out);
		order.write(out);
		out.writeUTF(hoge);
	}


	public Text getKey() {
		return key;
	}


	public IntWritable getOrder() {
		return order;
	}


	public int compareTo(CustomWritable o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
