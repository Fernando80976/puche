package com.example.demo;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class mapeosHolaMundo {
static AtomicInteger contador=new AtomicInteger(0);
@GetMapping("/")
@ResponseBody
public String contador() {
	return contador.getAndIncrement()+"";
}
}
