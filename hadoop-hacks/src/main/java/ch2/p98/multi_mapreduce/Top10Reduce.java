package ch2.p98.multi_mapreduce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * 前10名，Reduce
 * @author PLAYMORE
 *
 */
public class Top10Reduce extends
		Reducer<Text, LongWritable, Text, LongWritable> {

	protected void reduce(Text key, Iterable<WrodCountWritable> values,
			Context context) throws IOException, InterruptedException, ClassNotFoundException {

		int cnt = 0;
		for (WrodCountWritable w : values) {
			if (cnt > 10) {
				break;
			}
			cnt++;
			context.write(w.getKey(), w.getCount());
		}
		
		List<Job> jobs=new ArrayList<Job>();
		MultiMapReduce mr=new MultiMapReduce();
		jobs.add(mr.createWordCount());
		jobs.add(mr.createTop10());
		
		for (Job job : jobs) {
			boolean isSuccessful=job.waitForCompletion(true);
			if (!isSuccessful) {
				break;
			}
		}
	}

}
