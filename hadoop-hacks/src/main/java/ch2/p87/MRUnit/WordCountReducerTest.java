package ch2.p87.MRUnit;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;

import junit.framework.TestCase;

/**
 * 测试Wordcount的Reducer测试用例
 * @author PLAYMORE
 *
 */
public class WordCountReducerTest extends TestCase{

	private Reducer<Text, LongWritable, Text, LongWritable> reducer;
	private ReduceDriver<Text, LongWritable, Text, LongWritable> driver;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		reducer=new WordCountReducer();
		driver=new ReduceDriver<Text, LongWritable, Text, LongWritable>(reducer);
	}

	public void testReducer(){
		List<LongWritable> values=new ArrayList<LongWritable>();
		values.add(new LongWritable(2));
		values.add(new LongWritable(2));
		driver.withInput(new Text("this"),   values);
		//driver.withInput(new Text("this"), (List<IntWritable>) new IntWritable(1));
		
		List<LongWritable> values2=new ArrayList<LongWritable>();
		values2.add(new LongWritable(4));
//		values2.add(new LongWritable(2));
		driver.withInput(new Text("you"),   values2);		
		driver.withOutput(new Text("you"), new LongWritable(4));		
		driver.runTest();
	}
}
