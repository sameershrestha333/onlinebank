package com.userfront.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.userfront.domain.Appointment;
import com.userfront.domain.User;
import com.userfront.service.AppointmentService;
import com.userfront.service.UserService;

import groovyjarjarcommonscli.ParseException;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String createAppointment(Model model){
		Appointment appointment=new Appointment();
		model.addAttribute("dateString","");
		model.addAttribute("appointment",appointment);
		
		return "appointment";
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String createAppointmentPost(@ModelAttribute("appointment")Appointment appointment,@ModelAttribute("dateString") String date,Principal principal)throws ParseException, java.text.ParseException{
		
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date d1=format.parse(date);
		
		appointment.setDate(d1);
		User user=userService.findByUsername(principal.getName());
		appointment.setUser(user);
		appointmentService.createAppointment(appointment);
		return "redirect:/userFront";
	}
}
