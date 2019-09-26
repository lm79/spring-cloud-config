package com.flk.test;


import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.configuration.Configuration;

/**
 * @author lm
 * @date 2019 - 09 - 23 - 2:38 下午
 */
public class Test {
    private static ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

    public static void main(String[] args) throws Exception {
        recursiveRead();
        fromFile();
        fromCsv();
    }

    private static void fromFile() throws Exception {
        //从一个文件读取
        env.readTextFile("aaa").print();
        //读取文件夹下所有文件(一层)
        env.readTextFile("input").print();
    }

    private static void recursiveRead() throws Exception {
        // create a configuration object
        Configuration parameters = new Configuration();

        // set the recursive enumeration parameter
        parameters.setBoolean("recursive.file.enumeration", true);

        // pass the configuration to the data source
        env.readTextFile("input")
                .withParameters(parameters).print();
    }

    private static void fromCsv() throws Exception {
        /*
        指定包含的数据类型
        若类型超过25个，则使用env.readCsvFile().pojoType(Class,Fields);
        */
        DataSource<Tuple3<Integer, String, Integer>> types = env
                .readCsvFile("test.csv")
                .ignoreFirstLine()
                .types(Integer.class, String.class, Integer.class);
        types.print();
    }
}

