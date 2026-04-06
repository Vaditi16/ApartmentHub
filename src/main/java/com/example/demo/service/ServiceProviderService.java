//package com.example.demo.service;
//
//public class ServiceProviderService {
//
//}
package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ServiceProvider;
import com.example.demo.repository.ServiceProviderRepository;

@Service
public class ServiceProviderService {

    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    public ServiceProvider saveProvider(ServiceProvider provider) {
        return serviceProviderRepository.save(provider);
    }

    public List<ServiceProvider> getAllProviders() {
        return serviceProviderRepository.findAll();
    }
}