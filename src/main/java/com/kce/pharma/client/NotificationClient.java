package com.kce.pharma.client;

import com.kce.pharma.dto.EmailRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification", url = "${notification.service.url}")
public interface NotificationClient {

    @PostMapping("/email/send")
    void sendEmail(@RequestBody EmailRequest request);
}