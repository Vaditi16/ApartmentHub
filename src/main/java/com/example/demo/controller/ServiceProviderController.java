//package com.example.demo.controller;
//
//public class ServiceProviderController {
//
//}

package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.ServiceProvider;
import com.example.demo.service.ServiceProviderService;

@RestController
@RequestMapping("/providers")
@CrossOrigin("*")
public class ServiceProviderController {

    @Autowired
    private ServiceProviderService serviceProviderService;

    @PostMapping
    public ServiceProvider addProvider(@RequestBody ServiceProvider provider) {
        return serviceProviderService.saveProvider(provider);
    }

    @GetMapping
    public List<ServiceProvider> getProviders() {
        return serviceProviderService.getAllProviders();
    }
}