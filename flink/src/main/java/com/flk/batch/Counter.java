package com.flk.batch;

import org.apache.flink.api.common.JobExecutionResult;
import org.apache.flink.api.common.accumulators.LongCounter;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.core.fs.FileSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lm
 */
public class Counter {
    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        List<String> list = new ArrayList<>();
        list.add("tom");
        list.add("jerry");
        list.add("ben");

        DataSet<String> dataSet = env.fromCollection(list).setParallelism(2);

        DataSet<String> dataSet1 = dataSet.map(new RichMapFunction<String, String>() {
            LongCounter longCounter = new LongCounter();

            @Override
            public void open(Configuration parameters) throws Exception {
                super.open(parameters);
                getRuntimeContext().addAccumulator("listCounter", longCounter);
            }

            @Override
            public String map(String s) {
                longCounter.add(1);
                return s;
            }
        });

        dataSet1.writeAsText("counter", FileSystem.WriteMode.OVERWRITE).setParallelism(3);
        JobExecutionResult jobExecutionResult = env.execute("JavaDataSetDataSourceApp");
        long counter = jobExecutionResult.getAccumulatorResult("listCounter");
        System.out.println(counter);


    }
}