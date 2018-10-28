import org.apache.hadoop.mapreduce.Partitioner;

public class SecondKeyTextPairPartitionor extends Partitioner<CustomTextPair, CustomLongPair> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getPartition(CustomTextPair key, CustomLongPair value, int numPartitions) {

		int partitionValue = 0;
		partitionValue = (key.getSecond().hashCode() & Integer.MAX_VALUE) % numPartitions;
		return partitionValue;
	}

}
