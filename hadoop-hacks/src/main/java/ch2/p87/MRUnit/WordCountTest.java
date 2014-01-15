package ch2.p87.MRUnit;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.junit.Before;
import org.junit.Test;

public class WordCountTest {
	private Mapper<LongWritable, Text, Text, LongWritable> mapper;
	private Reducer<Text, LongWritable, Text, LongWritable> reducer;
	private MapReduceDriver<LongWritable, Text, Text, LongWritable, Text, LongWritable> driver;

	// private MapReduceDriver<?, Text, Text, ?, Text, ?> driver;

	@Before
	public void init() {
		mapper = new WordCountMapper();
		reducer = new WordCountReducer();
		driver = new MapReduceDriver<LongWritable, Text, Text, LongWritable, Text, LongWritable>(mapper, reducer);
	}

	@Test
	public void test() throws RuntimeException, IOException {
		String line = "taobao is a Great website is it not good";
		driver.withInput(new LongWritable(1), new Text(line));
		
		driver.withOutput(new Text("Great"), new LongWritable(1));
		
		driver.withOutput(new Text("a"), new LongWritable(1));
		driver.withOutput(new Text("good"), new LongWritable(1));
		
		driver.withOutput(new Text("is"), new LongWritable(2));
		driver.withOutput(new Text("it"), new LongWritable(1));
		driver.withOutput(new Text("not"), new LongWritable(1));		
		driver.withOutput(new Text("taobao"), new LongWritable(1));
		driver.withOutput(new Text("website"), new LongWritable(1));
		// driver.withOutput(new Text("you"), new LongWritable(4));
		driver.runTest();
	}
}
