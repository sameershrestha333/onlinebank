package com.userfront.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.userfront.domain.Appointment;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class AppointmentServiceTest {

	@Autowired
	private AppointmentService appointmentService;
	
	/*@Before
	public void setUp() throws Exception {
	}*/

	@Test
	public void test() {
		List<Appointment> findAll = appointmentService.findAll();
		assertEquals(2, findAll.size());
	}

}
