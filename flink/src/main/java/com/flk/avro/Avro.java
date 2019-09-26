package com.flk.avro;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author dyz
 * @Create 2019-09-07-下午6:38
 */

public class Avro {

    void test() throws IOException {
        Schema.Parser parser = new Schema.Parser();
        InputStream is = new FileInputStream("/home/dyz/IdeaProjects/flink/src/main/resources/StringPair.avsc");
        Schema schema = parser.parse(is);
        GenericRecord datum = new GenericData.Record(schema);
        datum.put("left", "L");
        datum.put("right", "R");
        FileOutputStream out = new FileOutputStream("StringPair.dat");
        DatumWriter<GenericData.Record> writer = new GenericDatumWriter<>(schema);
        Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);
        writer.write((GenericData.Record) datum, encoder);
        encoder.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        Avro avro = new Avro();
        avro.test();
    }
}
