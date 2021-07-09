package sg.mms.springapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sg.mms.springapi.dao.ApplianceRepository;
import sg.mms.springapi.model.Appliance;

import javax.validation.Valid;
import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@RequestMapping("/api")
@RestController
public class ApplianceController implements Serializable {

    @Autowired
    ApplianceRepository ApplianceRepository;

    @GetMapping("/appliances")
    public List<Appliance> get() {
        return ApplianceRepository.findAll();
    }

    @PostMapping(value = "/appliance")
    public ResponseEntity<Object> save(@Valid @RequestBody Appliance appliance) {
        Appliance savedAppliance = ApplianceRepository.save(appliance);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedAppliance.getId()).toUri();
        return ResponseEntity.created(location).body(savedAppliance);
    }

    @GetMapping("/appliance/{id}")
    public Appliance get(@PathVariable int id) {
        Optional<Appliance> appliance = ApplianceRepository.findById(id);
        if (appliance.isPresent()) {
            return appliance.get();
        } else {
            throw new RuntimeException("Appliance not found for the id " + id);
        }
    }

    @DeleteMapping("/appliance/{id}")
    public String delete(@PathVariable int id) {
        Optional<Appliance> appliance = ApplianceRepository.findById(id);
        if (appliance.isPresent()) {
            ApplianceRepository.delete(appliance.get());
            return "Appliance is deleted with id " + id;
        } else {
            throw new RuntimeException("Appliance not found for the id " + id);
        }
    }

    @PutMapping("/appliance")
    public Appliance update(@RequestBody Appliance appliance) {
        return ApplianceRepository.save(appliance);
    }

}
