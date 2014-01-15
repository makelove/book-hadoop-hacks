package ch2.p76.Partitioner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class SummaryPartitioner extends Partitioner<IntWritable, Text>{

	@Override
	public int getPartition(IntWritable key, Text value, int numPartitions) {
		String StrValue=value.toString();
		String prefectureCode=StrValue.split(",")[0];
		
		//从value挑选 都道府县 代码 返回
		return Integer.parseInt(prefectureCode);
	}

}
