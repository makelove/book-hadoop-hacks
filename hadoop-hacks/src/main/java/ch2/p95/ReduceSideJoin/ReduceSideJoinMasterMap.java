package ch2.p95.ReduceSideJoin;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ReduceSideJoinMasterMap extends
		Mapper<LongWritable, Text, IntPair, Text> {

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String[] s = value.toString().split("\t");
		//待实现
		IntPair intPair=new IntPair(Integer.valueOf(s[0]),1);
		context.write(intPair, new Text(s[1]));
	}

}
