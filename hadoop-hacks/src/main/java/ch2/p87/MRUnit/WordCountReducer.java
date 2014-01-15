package ch2.p87.MRUnit;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReducer extends
		Reducer<Text, LongWritable, Text, LongWritable> {

	protected void reduce(Text key, Iterable<LongWritable> values, Context context)
			throws IOException, InterruptedException {
		long cnt=0;
		for (LongWritable lw : values) {
			cnt +=lw.get();
		}
		context.write(key, new LongWritable(cnt));
	}

}
