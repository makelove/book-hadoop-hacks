package ch2.p98.multi_mapreduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordMap extends Mapper<LongWritable, Text, Text, LongWritable>{

}
