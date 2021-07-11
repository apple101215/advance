package com.sundy.advance.homework.week3;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author: sundy
 * @Date: 2021/7/10
 */
public class HttpInitializer extends ChannelInitializer<SocketChannel>{


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //通过SocketChannel拿到ChannelPipeline，也就是我们需要在这次网络处理里面，中间这一段需要我们自己控制，它的流水线它的流程这部分
        ChannelPipeline pipeline = ch.pipeline();
        //在末尾添加了一个http的编码器
        pipeline.addLast(new HttpServerCodec());
        //pipeline.addLast(new HttpServerExpectContinueHandler());
        //添加一个报文聚合器
        pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
        //添加自己定义的一个HttpHandler
        pipeline.addLast(new HttpHandler());
    }
}
