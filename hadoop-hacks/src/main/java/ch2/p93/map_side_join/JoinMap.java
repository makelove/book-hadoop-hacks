package ch2.p93.map_side_join;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 单独读入文件进行连接
 * 下面从DistributedCache的链接link进行读取，尝试连接
 * @author PLAYMORE
 *
 */
public class JoinMap extends Mapper<LongWritable, Text, Text, Text> {

	private static final String LINK = "link";
	private Map<String, String> master = new HashMap<String, String>();

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String[] s = value.toString().split("\t");
		context.write(new Text(master.get(s[0])), new Text(s[1]));
	}

	@Override
	protected void setup(Context context) throws IOException,
			InterruptedException {
		super.setup(context);
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(LINK)));
		String line;
		while ((line=br.readLine())!=null ){
			String[] s=line.split("\t");
			master.put(s[0], s[1]);
		}
		br.close();
	}

}
