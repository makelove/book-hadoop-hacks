package ch2.p80.CombineFileInputFormat;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.CombineFileSplit;
import org.apache.hadoop.util.LineReader;

/**
 * 自定义RecordReader
 * @author PLAYMORE
 *
 */
public class CombineFileLineRecordReader extends RecordReader<Text, Text> {

	private CompressionCodecFactory compressionCodescs = null;
	private long start;
	private long pos;
	private long end;

	private LineReader in;
	private int maxLineLength;
	private Text key = null;
	private Text value = null;
	private int idx;
	private String fileName;

	public CombineFileLineRecordReader(CombineFileSplit split,
			TaskAttemptContext context, Integer idx) {
		this.idx = idx;
		this.fileName = split.getPath(idx).getName();
	}

	@Override
	public void initialize(InputSplit genericSplit, TaskAttemptContext context)
			throws IOException, InterruptedException {
		CombineFileSplit split = (CombineFileSplit) genericSplit;
		Configuration job = context.getConfiguration();
		this.maxLineLength = job.getInt("mapred.linerecordreader.maxlength",
				Integer.MAX_VALUE);

		this.start = split.getStartOffsets()[idx];
		this.end = start + split.getLength();
		Path file = split.getPath(idx);
		this.compressionCodescs = new CompressionCodecFactory(job);
		final CompressionCodec codec = compressionCodescs.getCodec(file);

		FileSystem fs = file.getFileSystem(job);
		FSDataInputStream fileIn = fs.open(split.getPath(idx));
		boolean skipFirstLine = false;
		if (codec != null) {
			in = new LineReader(codec.createInputStream(fileIn), job);
			end = Long.MAX_VALUE;
		} else {
			if (start != 0) {
				skipFirstLine = true;
				--start;
				fileIn.seek(start);
			}
			in = new LineReader(fileIn, job);
		}
		if (skipFirstLine) {// skip first line and re-establish "start"
			start += in.readLine(new Text(), 0,
					(int) Math.min((long) Integer.MAX_VALUE, end - start));
		}
		this.pos = start;
	}

	@Override
	public boolean nextKeyValue() throws IOException, InterruptedException {
		if (key == null) {
			key = new Text();
		}
		key.set(fileName + ":" + pos);

		if (value == null) {
			value = new Text();
		}

		int newSize = 0;
		while (pos < end) {
			newSize = in.readLine(value, maxLineLength, (int) Math.max(
					Math.min(Integer.MAX_VALUE, end - pos), maxLineLength));
			if (newSize==0) {
				break;
			}
			
			pos+=newSize;
			if (newSize<maxLineLength) {
				break;
			}
		}
		
		if (newSize==0) {
			key=null;
			value=null;
			return false;
		}else {
			return true;
		}
	}

	@Override
	public Text getCurrentKey() throws IOException, InterruptedException {
		return key;
	}

	@Override
	public Text getCurrentValue() throws IOException, InterruptedException {
		return value;
	}

	@Override
	public float getProgress() throws IOException, InterruptedException {
		if (start==end) {
			return 0.0f;
		}else {
			return Math.min(1.0f, (pos-start)/(float)(end-start));
		}
	}

	@Override
	public void close() throws IOException {
		if (in != null) {
			in.close();
		}
	}

}
