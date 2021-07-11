package com.sundy.advance.homework.week3;

import com.sundy.advance.homework.week2.HttpClientDemo;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.ReferenceCountUtil;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * 我们整个NettServer启动以后，客户端的请求进来我们读取客户端请求的这个Handler
 *
 * @author: sundy
 * @Date: 2021/7/10
 */
public class HttpHandler extends ChannelInboundHandlerAdapter {

    HttpRequestFilter filter = new LoginHttpRequestFilter();

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        //重写channelRead方法就可以通过客户端连接Netty的这个通道里直接读到我们的数据，
        // 然后通过NettyhandlerContext这个参数，以及后面的第二个参数Object msg
        //这个msg本身就代表着这次请求的所有的数据包装类这样一个对象
        //客户端的这次请求的所有数据，它的HTTP协议的报文相关信息都在这个msg里面
        //我们通过这个msg把它转型成HttpRequest对象，就可以拿到它的内部结构
        try {
            FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
            String uri = fullHttpRequest.uri();
            //相当于一个路由
            if (uri.contains("/test")) {
                handle(fullHttpRequest, ctx, "http://localhost:8801");
            } else {
                //如果没有else，直接访问http://127.0.0.1:8808/，会发现啥也调不到
                handle(fullHttpRequest, ctx, "http://localhost:8802");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    private void handle(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx, String url) {
        filter.doFilter(fullHttpRequest, ctx);

        FullHttpResponse httpResponse = null;
        try {
            String value = HttpClientDemo.doGet(url);
            httpResponse = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes()));
            httpResponse.headers().set("Content-Type", "application/json");
            httpResponse.headers().set("Content-Length", httpResponse.content().readableBytes());
        } catch (Exception e) {
            System.out.println("处理出错:" + e.getMessage());
            httpResponse = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
        } finally {
            if (fullHttpRequest != null) {
                if (!HttpUtil.isKeepAlive(fullHttpRequest)) {
                    ctx.write(httpResponse).addListener(ChannelFutureListener.CLOSE);
                } else {
                    httpResponse.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(httpResponse);
                }
            }
        }


    }
}
