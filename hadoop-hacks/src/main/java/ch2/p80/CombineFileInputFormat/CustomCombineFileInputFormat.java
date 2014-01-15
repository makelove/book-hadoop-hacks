package ch2.p80.CombineFileInputFormat;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.CombineFileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.CombineFileRecordReader;
import org.apache.hadoop.mapreduce.lib.input.CombineFileSplit;

/**
 * 自定义CombineFileInputFormat
 * @author PLAYMORE
 *
 */
public class CustomCombineFileInputFormat extends
		CombineFileInputFormat<Text, Text> {

	@Override
	public RecordReader<Text, Text> createRecordReader(InputSplit split,
			TaskAttemptContext context)  {

		@SuppressWarnings("unused")
		Configuration conf = context.getConfiguration();
		
		CombineFileRecordReader<Text, Text> reader = null;
		try {
			reader = new CombineFileRecordReader<Text, Text>(
					(CombineFileSplit) split, context,
					CombineFileLineRecordReader.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return reader;
	}

}
