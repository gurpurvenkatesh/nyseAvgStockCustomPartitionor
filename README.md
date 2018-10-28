# nyseAvgStockCustomPartitionor
MapReduce Program witnessing simple custom Parititionor. Parititioning done on the custom key types.
Partitioning happened by default using Hash Partitionor where Hash value of the Kay,Value is calculated. Key, Value is applied mod function with reducer counts. Throgh this program we are creating a class which extends Partitioner & override the getPartition() method.

		Problem Statement : Write Parititionor to Partition the data by Stock tickers so that all stocks related to particular stocks are written to one file.  
			i. Input : Optional command line argument with -D for providing the Stock names which needed to be filtered.
			ii. Code Approach
				1. Create a class which extends Partitioner & override the getPartition() method.
				2. Apply the simple Hash & Mode operation on the second field of the CustomTextInput class.
				3. Edit the Driver class & call the customPartitioner  class name in job.setPartitionerClass() function call.
			iii. HDFS Commands
		
          [cloudera@quickstart nyseStockFilter]$ hadoop jar nyseAvgStockPartitionor1.jar AvgStockDriver -Dfilter.by.stockTicker=BAC,APL /user/cloudera/venkatesh/hadoopMR/nyseStockFilter/input/nyse_201[2-3]* /user/cloudera/venkatesh/hadoopMR/nyseStockFilter/CustomPrtnTextHashOutput
