package ch2.p76.Partitioner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;

/**
 * 使用自定义Patitioner的典型例子，就是以多个维度为基准进行排序的 二次排序
 * @author PLAYMORE
 *
 */
public class SummaryJob extends Configured implements Tool{

	public int run(String[] args) throws Exception {
		Configuration conf=new Configuration();
		Job job=new Job(conf);
		
		//47，准备47个 都道府县，所使用的Reduce任务
		job.setNumReduceTasks(47);
		
		//自定义partitioner的设置
		job.setPartitionerClass(SummaryPartitioner.class);
		
		return 0;
	}

}
