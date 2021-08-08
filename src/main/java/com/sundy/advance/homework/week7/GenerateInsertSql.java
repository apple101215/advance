package com.sundy.advance.homework.week7;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author shawn
 * @date 2021/8/8
 */
public class GenerateInsertSql {
    public static void main(String[] args) throws URISyntaxException {
        try (FileOutputStream outputStream = new FileOutputStream(new File("/Users/shawn/Documents/doc/sql.txt"));) {

            FileChannel channel = outputStream.getChannel();
            channel.write(StandardCharsets.UTF_8.encode("insert into `order` (user_id, receivable_amount, discount_amount, receipt_amount) values \n"));
            for (int i = 1; i < 1000000; i++) {
                channel.write(StandardCharsets.UTF_8.encode("(" + i + "," + (i + 2) + "," + (i * 2) + "," + (i % 2) + "," + (i / 2) + "),"));
                if (i % 10 == 0) {
                    channel.write(StandardCharsets.UTF_8.encode("\n"));
                }
            }
            int i = 1000001;
            channel.write(StandardCharsets.UTF_8.encode("(" + i + "," + (i + 2) + "," + (i * 2) + "," + (i % 2) + "," + (i / 2) + ");"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
