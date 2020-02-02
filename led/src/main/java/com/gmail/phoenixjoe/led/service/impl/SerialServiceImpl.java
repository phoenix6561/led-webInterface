package com.gmail.phoenixjoe.led.service.impl;

import java.io.DataOutputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.gmail.phoenixjoe.led.service.SerialService;

import gnu.io.NRSerialPort;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException; 
@Service
public class SerialServiceImpl implements SerialService {

	int  defaultPort = 3; 
	private boolean conectionInProgress= false;
	private boolean connection= false;
	private DataOutputStream out = null;
	private NRSerialPort serial = null;
	/* (non-Javadoc)
	 * @see com.gmail.phoenixjoe.led.Service.SerialService#sendCode(java.lang.String)
	 */
	@Override
	public void sendCode(String parm) throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException, IOException, InterruptedException {
		
		
			int timeout = 10;
			while(conectionInProgress && timeout > 0) {
				
				Thread.sleep(100);
				timeout--;
				if(timeout == 0){
					System.out.println("timeout" + parm);
					return;
				}
			}
			
		
		
		
		if(!connection) {
			conectionInProgress=true;
			this.connect();
			conectionInProgress=false;
		}
		
		this.out.writeBytes(parm);
		this.out.flush();
	
	}
	
	
	private void connect() throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException, IOException, InterruptedException {
		String port = null;
		int baudRate = 115200 ;
        
		
		
		int trys = 11;
		int portNumber = defaultPort;
		while(null == serial || !serial.isConnected() ) {
			
			port = "COM"+portNumber;
			System.out.println("attempting to connect on port : " + port);
			serial = new NRSerialPort(port, baudRate);
			serial.connect();
			
			if(portNumber == 10) {
				portNumber = 0;
				
			}
			trys--;
			if(trys == 0 ) {
				System.out.println("failed to connect");
				return;
			}
			portNumber++;
			
		}
		
		
		//DataInputStream ins = new DataInputStream(serial.getInputStream());
		//System.out.println(ins.);
		
		
		Thread.sleep(2000);
		
		
		
		
		
		this.out = new DataOutputStream(serial.getOutputStream());
		
		
		System.out.println("connected");
		
		

		//serial.disconnect();
		connection = true;
		
	}
	/* (non-Javadoc)
	 * @see com.gmail.phoenixjoe.led.Service.SerialService#testConnection()
	 */
	@Override
	@PostConstruct
	@Scheduled(cron="0 */5 * * * *")
	public void testConnection() {
		
		
		if(null ==serial || !serial.isConnected()) {
			
			try {
				this.connect();
			} catch (NoSuchPortException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PortInUseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedCommOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(null !=serial) {
		System.out.println("serial connected status: " + serial.isConnected());
		}
	}
	
	
}
