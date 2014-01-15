package ch2.p68.wordCount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;

/**
 * p69,运行wordcount MapReduce程序
 * @author PLAYMORE
 *
 */
public class RunMapReduce {

	public static void main(String[] args) throws Exception {
		Configuration conf=new Configuration();
		conf.set("mapred.jar", "wordcount.jar");
		
		int status=ToolRunner.run(conf, new WordCount(), args);
		System.exit(status);
	}

}
