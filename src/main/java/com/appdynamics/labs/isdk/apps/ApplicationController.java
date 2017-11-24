package com.appdynamics.labs.isdk.apps;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
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
		try {
			new TcpClient("localhost", 8088).startClient();
			model.put("message", "TCP message sent succesfully!");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			model.put("errormsg",e.getMessage());
		}
        return "index";
    }

	@RequestMapping("/httperror")
    String httpError(Map<String, Object> model, HttpServletResponse response) throws IOException, InterruptedException {
		int errorCode = new Random().nextBoolean() ? HttpStatus.NOT_FOUND.value() : HttpStatus.INTERNAL_SERVER_ERROR.value();

		Thread.sleep(500);
		response.sendError(errorCode);
		model.put("message","Error code : "+String.valueOf(errorCode));
        return "index";
    }
}
