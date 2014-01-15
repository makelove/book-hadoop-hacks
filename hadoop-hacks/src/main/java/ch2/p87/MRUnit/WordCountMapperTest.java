package ch2.p87.MRUnit;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;

import junit.framework.TestCase;

/**
 * 测试Wordcount的Mapper测试用例
 * @author PLAYMORE
 *
 */
public class WordCountMapperTest extends TestCase{
	private Mapper<LongWritable,Text,Text,LongWritable> mapper;
	private MapDriver<LongWritable,Text,Text,LongWritable>  driver;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mapper=new WordCountMapper();
		driver=new MapDriver<LongWritable, Text, Text, LongWritable>(mapper);
	}

	public void testMapper(){
		driver.withInput(new LongWritable(1),new Text("this this is a pen money"));
		driver.withOutput(new Text("this"),new LongWritable(1));
		driver.withOutput(new Text("this"),new LongWritable(1));
		driver.withOutput(new Text("is"),new LongWritable(1));
		driver.withOutput(new Text("a"),new LongWritable(1));
		driver.withOutput(new Text("pen"),new LongWritable(1));
		driver.withOutput(new Text("money"),new LongWritable(1));
		driver.runTest();
	}
	
	
}
