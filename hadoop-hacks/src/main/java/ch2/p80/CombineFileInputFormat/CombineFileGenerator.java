package ch2.p80.CombineFileInputFormat;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * 测试-自定义CustomCombineFileInputFormat的使用
 * @author PLAYMORE
 *
 */
public class CombineFileGenerator extends Configured implements Tool {

	public int run(String[] args) throws Exception {
		Configuration conf=new Configuration();
		Job job=new Job(conf);
		
		job.setJobName("CombineFileGenerator");
		job.setJarByClass(CombineFileGenerator.class);
		
		job.setMapperClass(CombineFileGeneratorMapper.class);
		job.setReducerClass(CombineFileGeneratorReducer.class);
		
		//在这里设置！！！
		job.setInputFormatClass(CustomCombineFileInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		return (job.waitForCompletion(true))?0:1;		
	}

	public static void main(String[] args) throws Exception {
		int returnCode=ToolRunner.run(new CombineFileGenerator(), args);
		System.exit(returnCode);
	}
	
	
	public static class CombineFileGeneratorMapper extends Mapper<Text, Text, Text, Text>{

		@Override
		protected void map(Text key, Text value, Context context)
				throws IOException, InterruptedException {
			context.write(key, value);
		}
		
	}
	public static class CombineFileGeneratorReducer extends Reducer<Text, Text, Text, Text>{

		@Override
		protected void reduce(Text key, Iterable<Text> vals,
				Context context)
				throws IOException, InterruptedException {
			
			Iterator<Text> iterator=vals.iterator();
			while (iterator.hasNext()) {
				context.write(key, iterator.next());
			}
		}
		
	}

}
