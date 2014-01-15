package ch2.p98.multi_mapreduce;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/**
 * 多段MapReduce
 * 
 * @author PLAYMORE
 * 
 */
public class MultiMapReduce extends Configured{

	public Job createWordCount() throws IOException {
		Job job = new Job(getConf(), "word count");
		job.setMapperClass(WordMap.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);

		job.setCombinerClass(WordReduce.class);
		job.setReducerClass(WordReduce.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);

		TextInputFormat.setInputPaths(job, "input");
		job.setInputFormatClass(TextInputFormat.class);

		SequenceFileOutputFormat.setOutputPath(job, new Path("wordcount"));
		job.setOutputFormatClass(SequenceFileOutputFormat.class);

		return job;
	}

	public Job createTop10() throws IOException {

		Job job = new Job(getConf(), "word count");
		job.setMapperClass(WordMap.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);

		job.setReducerClass(Top10Reduce.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		
		job.setSortComparatorClass(SortDownComparator.class);



		SequenceFileInputFormat.setInputPaths(job, "wordcount");
		job.setInputFormatClass(SequenceFileInputFormat.class);
		
		SequenceFileOutputFormat.setOutputPath(job, new Path("output"));
		job.setOutputFormatClass(TextOutputFormat.class);

		return job;
	}

}
