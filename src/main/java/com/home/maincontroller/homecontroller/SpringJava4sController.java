/* 
 * Author ::. Sivateja Kandula | www.java4s.com 
 *
 */

package com.home.maincontroller.homecontroller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;



@RestController
public class SpringJava4sController {
	
	@Autowired
	daoclass dao;
	
	@GetMapping("/")
	public ModelAndView showLoginPage(Model model) {
        model.addAttribute("message", "Welcome to Java4s Spring Boot Tutorials");
        
        return new ModelAndView("login");
    }
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView login(Locale locale, Model model) {
		//logger.info("Welcome home! The client locale is {}.", locale);

		
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(Locale locale, Model model,
			@RequestParam String uname,@RequestParam String pass) {
		//logger.info("Welcome home! The client locale is {}.", locale);
		String redirect = null;
		
		if(uname.equals("user")&& pass.equals("pass"))
		{
			model.addAttribute("list", this.dao.show());
			redirect="user_index";
		}
		else
		{
			redirect="login";
		}
		
		
		
			
		model.addAttribute("message", uname);
		return new ModelAndView(redirect);
	}
	
	
	@RequestMapping(value = "/pregi", method = RequestMethod.POST)
	public ModelAndView log(Locale locale, Model model,
			@RequestParam String name,@RequestParam String fname,@RequestParam String date,
			@RequestParam String email,@RequestParam String sex,@RequestParam String phone,
			@RequestParam MultipartFile files){
			
			model m=new model();
			Blob blob =null;
			try
			{
				InputStream photo = files.getInputStream();
				byte[] content = IOUtils.toByteArray(photo);
				blob = new SerialBlob(content);
				
			}catch(Exception e)
			{	
				e.printStackTrace();
			}
			System.out.println(date);
			m.setName(name);
			m.setFname(fname);
			m.setDate(date);
			m.setEmail(email);
			m.setSex(sex);
			m.setPhone(phone);
			m.setPhoto(blob);
			dao.add(m);
			
			model.addAttribute("list", this.dao.show());
			
		return new ModelAndView("user_index");
	}
	
	@RequestMapping(value = "/deletee", method = RequestMethod.POST)
	public ModelAndView delete(@RequestParam int id1,Model model) {
		
		model m=new model();
		m.setId(id1);
		try
		{
			dao.delete(id1);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		model.addAttribute("list", this.dao.show());
		return new ModelAndView("user_index");
	}
	
	
	@RequestMapping(value = "/updateCrud", method = RequestMethod.POST)
	public ModelAndView up(@RequestParam int id1,Model model){
		
		
		 model m=dao.edit(id1);
		model.addAttribute("object", m);
		return new ModelAndView("user_index");
		
	
	}
	
	
	@RequestMapping(value = "/up", method = RequestMethod.POST)
	public ModelAndView update(Model model,@RequestParam int id,
			@RequestParam String name,@RequestParam String fname,@RequestParam String date,
			@RequestParam String email,@RequestParam String sex,
			@RequestParam String phone,@RequestParam MultipartFile files) {
		
		System.out.println("---=================="+id);
		
		model m=new model();
		Blob blob =null;
		try
		{
			InputStream photo = files.getInputStream();
			byte[] content = IOUtils.toByteArray(photo);
			blob = new SerialBlob(content);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		m.setId(id);
		m.setName(name);
		m.setFname(fname);
		m.setDate(date);
		m.setEmail(email);
		m.setSex(sex);
		m.setPhone(phone);
		m.setPhoto(blob);
		dao.update(m);	
		model.addAttribute("list",dao.show());
	
		return new ModelAndView("user_index"); 
	}
	
	
	@RequestMapping(value = "/excel", method = RequestMethod.GET)
	public ModelAndView excel(Model model,HttpServletRequest req) {
		
		excel e=new excel();
		List<model> person = dao.show();
		
			return new ModelAndView(e, "data", person);
				
		}

	@RequestMapping(value = "/pdf/{id}/{name}", method = RequestMethod.GET)
	public ModelAndView pdf( @PathVariable("id") int id,Model model,HttpServletRequest req,HttpServletResponse res) throws IOException {
		
		pdfpage e=new pdfpage();
		List<model> person = dao.pdf(id);
		
		ModelAndView mov=new ModelAndView(e,"pdf",person);
		
		return mov;

		}
}

