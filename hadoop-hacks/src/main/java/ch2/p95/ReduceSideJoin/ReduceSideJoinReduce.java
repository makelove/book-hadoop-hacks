package ch2.p95.ReduceSideJoin;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReduceSideJoinReduce extends
		Reducer<IntPair, Text, IntWritable, Text> {

	@Override
	protected void reduce(IntPair key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		//key.getFirst()待实现
		IntWritable emitKey=new IntWritable(key.getFirst());
		Iterator<Text> ite=values.iterator();
		String itemName=ite.next().toString();
		while (ite.hasNext()) {
			context.write(emitKey, new Text(itemName+","+ite.next().toString()));
		}
	}

}
