package com.appdynamics.labs.isdk.apps;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.appdynamics.labs.isdk.apps.tcp.TcpClient;

@Controller
public class ApplicationController {

	@RequestMapping("/")
    String home() {
        return "index";
    }

	@RequestMapping("/tcpclient")
    String tcpClient(Map<String, Object> model) {
		String message = "TCP message sent succesfully!";
		
		try {
			new TcpClient("localhost", 8088).startClient();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			message="ERROR : "+e.getMessage();
		}
		model.put("message",message);
        return "index";
    }

	@RequestMapping("/httperror")
    String httpError(Map<String, Object> model, HttpServletResponse response) {
		int errorCode = new Random().nextBoolean() ? 404 : 500;

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			response.sendError(errorCode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.put("message","Error code : "+String.valueOf(errorCode));
        return "index";
    }
}
