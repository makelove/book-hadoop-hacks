package ch2.p98.multi_mapreduce;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;


public abstract class WrodCountWritable implements WritableComparable<Object>{


	private Text foo;
	private IntWritable bar;
	private String hoge;
	
	
	
	public WrodCountWritable(Text foo, IntWritable bar, String hoge) {
		super();
		this.foo = foo;
		this.bar = bar;
		this.hoge = hoge;
	}

	public void readFields(DataInput in) throws IOException {
		foo.readFields(in);
		bar.readFields(in);
		hoge=in.readUTF();
	}

	public void write(DataOutput out) throws IOException {
		foo.write(out);
		bar.write(out);
		out.writeUTF(hoge);
	}

	public int compareTo(WrodCountWritable o) {
		return this.foo.compareTo(o.foo);
	}

	public Text getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	public LongWritable getCount() {
		// TODO Auto-generated method stub
		return null;
	}

}
