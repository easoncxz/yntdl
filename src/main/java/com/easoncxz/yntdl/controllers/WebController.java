package com.easoncxz.yntdl.controllers;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.easoncxz.yntdl.domain.Thing;

@Controller
public class WebController {

	@RequestMapping(value = "/servertime", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("home");
		mnv.addObject("serverTime", (new Date()));
		return mnv;
	}

	@RequestMapping(value = "/something", method = RequestMethod.GET)
	@ResponseBody
	public Thing doSomeThing() {
		Thing t = new Thing();
		t.setLabel("something");
		return t;
	}

}
