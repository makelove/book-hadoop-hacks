package ch2.p90.Writable;


import java.io.IOException;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapreduce.Job;

public class TestComparator extends Configured{

	@org.junit.Test
	public  void Test(String[] args) throws IOException {
		//最后设置目前为止，为各个Comparator创建的作业
		Job job=new Job(getConf(), "job");
		job.setGroupingComparatorClass(CustomGroupingComparator.class);
		job.setPartitionerClass(CustomPartitioner.class);
		job.setSortComparatorClass(SortComparatorClass.class);
	}
}
