package org.insuredoo.test;

import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpServletRequest;

import org.insuredoo.controller.ViewsController;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;


@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class LoadCSVTests {
	
	@Autowired ViewsController viewsController;

	@LocalServerPort
    int randomServerPort;
	
	 @DisplayName("Test Spring @Autowired Integration")
	 @Test
	 void testLoadCSV(HttpServletRequest request) throws Exception {
	        assertEquals("{respcode:0}", viewsController.loadCSVFile(request));
	 }
	
	
	
	

}

