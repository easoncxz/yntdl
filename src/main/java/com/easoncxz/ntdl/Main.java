package com.easoncxz.ntdl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

	public static void main(String[] args) {
		Logger l = LoggerFactory.getLogger(Main.class);
		l.trace("trace");
		l.debug("debug");
		l.info("info");
		l.warn("warn");
		l.error("error");
	}

}
