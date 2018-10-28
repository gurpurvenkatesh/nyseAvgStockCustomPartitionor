import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AvgStockMapper extends Mapper<LongWritable, Text, CustomTextPair, CustomLongPair> {

	private static NYSEParser parser = new NYSEParser();
	private static CustomTextPair mapOutputKey = new CustomTextPair();
	private static CustomLongPair mapOutputValue = new CustomLongPair();
	
	private String argStockTicker = new String();
	private Set<String> stockTickersSet = new HashSet<String>();
	
	protected void setup(Context context){
		
		argStockTicker = context.getConfiguration().get("filter.by.stockTicker");
		if(argStockTicker != null) {
			String[] tickersArray = argStockTicker.split(",");
			
			for(String ticker : tickersArray) {
				stockTickersSet.add(ticker);
			}
		}		
	}
	
	public void map(LongWritable LineOffset, Text record, Context context) throws IOException, InterruptedException{
		
		parser.parse(record.toString());
		
		if(stockTickersSet.isEmpty() || stockTickersSet.contains(parser.getStockTicker())) {
		
			mapOutputKey.setFirst(new Text(parser.getTradeMonth()));
			mapOutputKey.setSecond(new Text(parser.getStockTicker()));
			
			mapOutputValue.setFirst(new LongWritable(parser.getVolume()));
			mapOutputValue.setSecond(new LongWritable(1));
			
			context.write(mapOutputKey, mapOutputValue);
		}		
	}
}
