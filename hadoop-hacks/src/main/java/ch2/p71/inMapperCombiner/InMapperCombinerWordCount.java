package ch2.p71.inMapperCombiner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.Tool;

public class InMapperCombinerWordCount extends Configured implements Tool {

	public int run(String[] args) throws Exception {
		return 0;
	}
	
	/**
	 * Combiner在这里实现
	 * @author PLAYMORE
	 *
	 */
	public static class InMapperCombinerWordCountMapper extends
	Mapper<LongWritable, Text, Text, LongWritable> {
		
		private HashMap<String, LongWritable> hashMap=new HashMap<String, LongWritable>();
		//存储100个数据后写入关联数组的内容
		private static final int MAP_CAPACITY_LIMIT=100;
		@Override
		protected void map(LongWritable ikey, Text ival, Context context)
				throws IOException, InterruptedException {
			String line=ival.toString();
			
			//
			StringTokenizer tokenizer=new StringTokenizer(line, " ,.;:!\"'`()&?<>|\\-%$#*+[]");
			
			while(tokenizer.hasMoreTokens()){
				String token=tokenizer.nextToken();
				if (hashMap.containsKey(token)) {
					LongWritable value=hashMap.get(token);
					value.set(value.get()+1);
					
					//保存关联数组规定量以上的数据后写入
					if (hashMap.size()>MAP_CAPACITY_LIMIT) {
						flush(context);
					}
				}else{
					hashMap.put(token, new LongWritable(1));
				}
			}
		}
		private void flush(Context context) throws IOException, InterruptedException {
			Iterator<String> iterator =hashMap.keySet().iterator();
			while (iterator.hasNext()) {
				String key=iterator.next();
				LongWritable value=hashMap.get(key);
				context.write(new Text(key), value);
			}
			hashMap.clear();
		}
		@Override
		protected void cleanup(Context context) throws IOException,
				InterruptedException {
			//处理完毕所有记录后，写入关联数组中剩余的数据
			if (!hashMap.isEmpty()) {
				flush(context);
			}
		}
		
		
	}


}
