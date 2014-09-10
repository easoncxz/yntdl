package com.easoncxz.yntdl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Playground {

	public static void main(String[] args) {
		Logger l = LoggerFactory.getLogger(Playground.class);
		l.trace("trace");
		l.debug("debug");
		l.info("info");
		l.warn("warn");
		l.error("error");
	}

}