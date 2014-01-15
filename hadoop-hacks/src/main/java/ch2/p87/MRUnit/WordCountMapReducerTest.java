package ch2.p87.MRUnit;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;

import junit.framework.TestCase;

public class WordCountMapReducerTest extends TestCase{

	private MapReduceDriver<IntWritable, Text, Comparable,  IntWritable,Text, IntWritable> driver;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Job job=new Job();
		
		job.setMapOutputKeyClass(WordCountMapper.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		job.setReducerClass(WordCountReducer.class);
		//driver=MapReduceDriverFactory.createMapReduceDriver(job);
		//driver=new MapReduceDriver<IntWritable, Text, Comparable, IntWritable, Text, IntWritable>(WordCountMapper.class, WordCountReducer.class);
		//driver=new MapReduceDriver<IntWritable, Text, Comparable, IntWritable, Text, IntWritable>();
		driver=new MapReduceDriver(new WordCountMapper(), new WordCountReducer());
	}
	
	public void testMapReduce() throws IOException{
		driver.addInput(new IntWritable(1),new Text("this is a dog"));
		driver.addInput(new IntWritable(1),new Text("this is a cat"));
		
		List<Pair<Text,IntWritable>> actual=driver.run();
		assertEquals(5, actual.size());
		
		assertEquals(new Text("this"),actual.get(0).getFirst());
		assertEquals(new IntWritable(2),actual.get(0).getSecond());		
		assertEquals(new Text("is"),actual.get(1).getFirst());
		assertEquals(new IntWritable(2),actual.get(1).getSecond());
		//以下重复
	}

}
