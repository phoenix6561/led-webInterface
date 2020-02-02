package com.gmail.phoenixjoe.led.controller;


	

	import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.phoenixjoe.led.service.SerialService;

import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;

	@RestController
	@RequestMapping(path="/led") 
	public class LedRestController {


		@Autowired private SerialService serialService;
		
		

		    @GetMapping("/send/{parm}")
		    public boolean test(Model model ,@PathVariable(name = "parm") String parm) throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException, IOException, InterruptedException {
				
		    	serialService.sendCode(parm);
		       
				return true;
			}
		
	}

