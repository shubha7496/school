package com.student.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="NOTIFICATION-SERVICE")
public interface TwilioConsumer {
	

}
