package com.simple.server.http;

import com.simple.server.config.ContentType;

public interface IHttp {
	void sendHttp(Object msg, String url, ContentType contentType, Boolean useAuth) throws Exception;
}
