package ch2.p68.wordCount;

import java.io.IOException;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;

/**
 * p68, 从应用程序操作MapReduce， 要在生成的应用程序上操作Hadoop，需要将Hadoop的核心jar文件放置在类路径或配置文件中，
 * 通过配置使其嵌入到应用程序的jar中。
 * 
 * @author PLAYMORE
 * 
 */
public class WordCount extends Configured implements Tool {

	public int run(String[] args) throws Exception {
		Job job=new Job(getConf(), "word count");
		job.setMapperClass(WordMap.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		
		job.setCombinerClass(WordReduce.class);
		
		job.setReducerClass(WordReduce.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		
		TextInputFormat.setInputPaths(job, args[0]);
		job.setInputFormatClass(TextInputFormat.class);
		
		TextOutputFormat.setOutputPath(job, new Path(args[1]));
		job.setOutputFormatClass(TextOutputFormat.class);
		
		return job.waitForCompletion(true)?0:1;
	}

	public static class WordMap extends
			Mapper<LongWritable, Text, Text, LongWritable> {

		private LongWritable emitValue = new LongWritable(1);

		@Override
		protected void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {

			String[] splits = value.toString().split("\t");
			for (String str : splits) {
				context.write(new Text(str), emitValue);
			}
		}
	}

	public static class WordReduce extends
			Reducer<Text, LongWritable, Text, LongWritable> {
		@Override
		protected void reduce(Text key, Iterable<LongWritable> values,
				Context context) throws IOException, InterruptedException {
			long cnt=0;
			for (LongWritable lw : values) {
				cnt+= lw.get();
			}
			context.write(key, new LongWritable(cnt));
		}

	}
}
