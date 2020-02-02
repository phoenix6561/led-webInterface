package com.gmail.phoenixjoe.led.service;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.Scheduled;

import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;

public interface SerialService {

	void sendCode(String parm) throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException,
			IOException, InterruptedException;

	void testConnection();

}