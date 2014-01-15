package ch2.p73.CustomWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

/**
 * p74
 * @author PLAYMORE
 *
 */
public class CustomWritableComparable 
implements WritableComparable<CustomWritableComparable>{

	private Text foo;
	private IntWritable bar;
	private String hoge;
	public CustomWritableComparable(Text foo, IntWritable bar, String hoge) {
		super();
		this.foo = foo;
		this.bar = bar;
		this.hoge = hoge;
	}
	public void write(DataOutput out) throws IOException {
		foo.write(out);
		bar.write(out);
		out.writeUTF(hoge);
	}
	public void readFields(DataInput in) throws IOException {
		foo.readFields(in);
		bar.readFields(in);
		hoge=in.readUTF();
	}
	public int compareTo(CustomWritableComparable other) {
		return this.foo.compareTo(other.foo);
	}
	
	
}
