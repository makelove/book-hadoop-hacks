package ch2.p87.MRUnit;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends
		Mapper<LongWritable, Text, Text, LongWritable> {

	private LongWritable emitValue=new LongWritable(1L);

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		//用空格分割字符串，英文
		String[] splits=value.toString().split(" ");
		for (String str : splits) {
			context.write(new Text(str), emitValue);
		}
	}

}
