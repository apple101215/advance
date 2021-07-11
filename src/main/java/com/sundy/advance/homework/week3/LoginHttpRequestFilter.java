package com.sundy.advance.homework.week3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author shawn
 * @date 2021/7/11
 */
public class LoginHttpRequestFilter implements HttpRequestFilter{
    @Override
    public void doFilter(FullHttpRequest request, ChannelHandlerContext ctx) {
        request.headers().set("userId", "loginSuccess");
    }
}
