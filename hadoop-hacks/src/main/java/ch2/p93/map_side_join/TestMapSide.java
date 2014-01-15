package ch2.p93.map_side_join;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.KeyValueTextInputFormat;
import org.apache.hadoop.mapred.join.CompositeInputFormat;
import org.apache.hadoop.mapred.join.TupleWritable;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;

import com.sun.jersey.core.impl.provider.entity.XMLJAXBElementProvider.Text;

/**
 * 通过CompositeInputFormat类中的Compose方法指定的第1个参数来查找指定Inner
 * @author PLAYMORE
 *
 */
public class TestMapSide extends Configured implements Tool {

	public int run(String[] args) throws Exception {
		Job job = Job.getInstance(getConf(), "map side join");
		Configuration conf = job.getConfiguration();
		job.setJarByClass(getClass());

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(TupleWritable.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(TupleWritable.class);

		Class<? extends InputFormat> cls = null;
		job.setInputFormatClass(cls);
		// job.setInputFormatClass(CompositeInputFormat.class);
		// 导入路径设置为master和数据两种
		TextInputFormat.addInputPaths(job, args[0]);
		TextInputFormat.addInputPaths(job, args[1]);

		conf.set(CompositeInputFormat.JOIN_EXPR, CompositeInputFormat.compose(
				"inner", KeyValueTextInputFormat.class,
				TextInputFormat.getInputPaths(job)));

		TextOutputFormat.setOutputPath(job, new Path(args[2]));
		job.setOutputFormatClass(TextOutputFormat.class);
		
		return job.waitForCompletion(true)?0:1;
	}

}
