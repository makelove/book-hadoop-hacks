package ch2.p95.ReduceSideJoin;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;

import com.sun.jersey.core.impl.provider.entity.XMLJAXBElementProvider.Text;

public class TestReduceSideJoin extends Configured implements Tool {

	public int run(String[] args) throws Exception {
		Job job=Job.getInstance(getConf(), "reduce side join");
		job.setJarByClass(getClass());
		
		MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class,ReduceSideJoinMasterMap.class);
		MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class,ReduceSideJoinMasterMap.class);
		
		job.setMapOutputKeyClass(IntPair.class);
		job.setMapOutputValueClass(Text.class);
		job.setPartitionerClass(ReducesidejoinPartitioner.class);
		job.setGroupingComparatorClass(ReduceSideJoinGroupingComparator.class);
		
		job.setReducerClass(ReduceSideJoinReduce.class);
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(Text.class);
		
		job.setOutputFormatClass(TextOutputFormat.class);
		TextOutputFormat.setOutputPath(job, new Path(args[2]));
		
		return job.waitForCompletion(true)?0:1; 
	}

}
