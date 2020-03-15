package org.insuredoo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.insuredoo.model.Product;
import org.insuredoo.repository.ProductRepository;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.JsonObject;


@RestController
public class ViewsController {
	
	@Autowired private JobLauncher jobLauncher;
	@Autowired private Job importProductsJob;
	@Autowired private ProductRepository productsRepository;
	
	
	@RequestMapping(value= {"/"}, method=RequestMethod.GET)
	 public ModelAndView doViewSearchPage(HttpServletRequest request) {
		 ModelAndView mav = new ModelAndView();
		 mav.setViewName("search");
		 return mav;
	}
	
	
	@RequestMapping("/load")
	 public String loadCSVFile(HttpServletRequest request) throws Exception {
		 JsonObject js = new JsonObject();
		 try {
			 Map<String,JobParameter> maps = new HashMap<>();
			 maps.put("time", new JobParameter(System.currentTimeMillis()));
			 JobParameters jobparameters = new JobParameters(maps);
			 JobExecution jobExecution = jobLauncher.run(importProductsJob, jobparameters);
			 jobExecution.getStatus();
			 BatchStatus bs = jobExecution.getStatus();
			 if(bs.toString().equals("COMPLETED")){js.addProperty("respcode",0);}
			 else {js.addProperty("respcode",1);}
		 }catch(Exception ex) {
			 ex.printStackTrace();
			 js.addProperty("respcode",1);
		 }
		 
		 return js.toString();
	}
	 
	
	 @RequestMapping(value= {"/findproducts"}, method=RequestMethod.POST)
	 public ModelAndView doViewResultsPage(HttpServletRequest request) {
		 String marke = request.getParameter("marke");
		 int year = Integer.parseInt(request.getParameter("year"));
		 
		 List<Product> myProducts = productsRepository.findProducts(year,marke);
		 for(Product p: myProducts) {p.setTotal(p.getAmount() + ((p.getAmount() * p.getTax()) / 100));}
		 ModelAndView mav = new ModelAndView();
		 mav.setViewName("results");
		 mav.addObject("message", "Search: "+marke+" / "+year);
		 mav.addObject("products", myProducts);
		 return mav;
	}
	 
}
