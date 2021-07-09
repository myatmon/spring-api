package sg.mms.springapi;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import sg.mms.springapi.dao.ApplianceRepository;
import sg.mms.springapi.model.Appliance;

import java.sql.Timestamp;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class SpringApiApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    ApplianceRepository applianceRepository;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port + "/api/";
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testGetAllAppliances() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/appliances",
                HttpMethod.GET, entity, String.class);

        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testCreateAppliance() {
        Appliance appliance = new Appliance();
        appliance.setSerialNumber("A01q34234");
        appliance.setBrand("Miko");
        appliance.setModel("3BR");
        appliance.setStatus("old");
        appliance.setDateBought(LocalDate.of(2021, 7, 12));

        ResponseEntity<Appliance> postResponse = restTemplate.postForEntity(getRootUrl() + "/appliance", appliance, Appliance.class);
        Assert.assertEquals(201, postResponse.getStatusCodeValue());


        Appliance savedAppliance = postResponse.getBody();
        assert savedAppliance != null;
        Assert.assertNotNull(applianceRepository.findById(savedAppliance.getId()));
        Assert.assertEquals(appliance.getBrand(), savedAppliance.getBrand());
        Assert.assertEquals(appliance.getModel(), savedAppliance.getModel());
        Assert.assertEquals(appliance.getModel(), savedAppliance.getModel());
    }

    @Test
    public void testUpdatePost() {
        int id = 1;
        Appliance appliance = restTemplate.getForObject(getRootUrl() + "/appliance/" + id, Appliance.class);
        appliance.setSerialNumber("A02q34234");
        appliance.setBrand("Miko");
        appliance.setModel("5BR");
        appliance.setStatus("sold");

        restTemplate.put(getRootUrl() + "/appliance/" + id, appliance);

        Appliance updatedAppliance = restTemplate.getForObject(getRootUrl() + "/appliance/" + id, Appliance.class);
        Assert.assertNotNull(updatedAppliance);
    }

    @Test
    public void testDeletePost() {
        int id = 2;
        Appliance appliance = restTemplate.getForObject(getRootUrl() + "/appliance/" + id, Appliance.class);
        Assert.assertNotNull(appliance);

        restTemplate.delete(getRootUrl() + "/users/" + id);

        try {
            appliance = restTemplate.getForObject(getRootUrl() + "/appliances/" + id, Appliance.class);
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

}
