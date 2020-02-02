package com.gmail.phoenixjoe.led.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gmail.phoenixjoe.led.service.impl.SerialServiceImpl;

import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;

@Controller
@RequestMapping(path="/main") 
public class MainController {


	
	
	

	    @GetMapping("/")
	    public String main(Model model ) throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException, IOException, InterruptedException {
			
	    	
	       
			return "main";
		}
	
}
