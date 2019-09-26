package com.flk.batch;


import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.JoinOperator;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.configuration.Configuration;


/**
 * @author lm
 * @date 2019 - 09 - 24 - 6:32 下午
 */
public class Transformation {
    private static ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

    public static void main(String[] args) throws Exception {
//        // create a configuration object
//        Configuration parameters;
//        parameters = new Configuration();
//
//        // set the recursive enumeration parameter
//        parameters.setBoolean("recursive.file.enumeration", true);
//
//        // pass the configuration to the data source
//        DataSource<String> input = env.readTextFile("input")
//                .withParameters(parameters);
//
//        aaa.map((MapFunction<String, Object>) String::length).print();
//
//        aaa.flatMap(new FlatMapFunction<String, String>() {
//            @Override
//            public void flatMap(String value, Collector<String> out) {
//                for (String s : value.split("")) {
//                    out.collect(s);
//                }
//            }
//        }).print();
//
//        aaa.mapPartition(new MapPartitionFunction<String, Object>() {
//            @Override
//            public void mapPartition(Iterable<String> values, Collector<Object> out) throws Exception {
//                long a = 0;
//                for (String value : values) {
//                    a++;
//                }
//                out.collect(a);
//            }
//        }).print();
//
//        aaa.filter(new FilterFunction<String>() {
//            @Override
//            public boolean filter(String value) throws Exception {
//                return value.length()>3;
//            }
//        }).print();
/*
        DataSet<Tuple3<Integer, String, Integer>> input1 = env.readTextFile("test").map(new MapFunction<String, Tuple3<Integer, String, Integer>>() {
            @Override
            public Tuple3<Integer, String, Integer> map(String s) {
                String[] tuple2 = s.split(",");
                return new Tuple3<>(Integer.parseInt(tuple2[0]), tuple2[1], Integer.parseInt(tuple2[2]));
            }
        });

        DataSet<Tuple2<Integer, Integer>> input2 = env.readTextFile("bb").map(new MapFunction<String, Tuple2<Integer, Integer>>() {
            @Override
            public Tuple2<Integer, Integer> map(String s) {
                String[] tuple2 = s.split(",");
                return new Tuple2<>(Integer.parseInt(tuple2[0]), Integer.parseInt(tuple2[1]));
            }
        });

        JoinOperator.DefaultJoin<Tuple3<Integer, String, Integer>, Tuple2<Integer, Integer>> join = input1.join(input2)
                //test里的第3个字段              .where(2)
                //bb里的第0个字段
                .equalTo(0);
        //以text写出，并行大于1会创建一个文件夹根据并行数量创建文件
        join.writeAsText("abc").setParallelism(1);
        env.execute();
*/
    }
}
