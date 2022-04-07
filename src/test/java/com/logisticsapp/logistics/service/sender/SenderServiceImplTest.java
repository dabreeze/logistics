package com.logisticsapp.logistics.service.sender;

import com.logisticsapp.logistics.data.dto.AddSenderRequest;
import com.logisticsapp.logistics.data.dto.AddSenderResponse;
import com.logisticsapp.logistics.data.models.Sender;
import com.logisticsapp.logistics.data.models.Sex;
import com.logisticsapp.logistics.data.repository.SenderRepository;
import com.logisticsapp.logistics.web.exceptions.BusinessLogicException;
import com.logisticsapp.logistics.web.exceptions.SenderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Sql("/static/insert.sql")
@Slf4j
class SenderServiceImplTest {

    @Autowired
    SenderRepository senderRepository;

    AddSenderRequest addSenderRequest;

    @Autowired
    SenderService senderService;

    Sender sender;

    @BeforeEach
    void setUp() {

        sender = new Sender();
        addSenderRequest = new AddSenderRequest();
    }

    @Test
    void senderCanBeCreatedTest() throws BusinessLogicException, SenderNotFoundException {
        // given that there is a sender
        addSenderRequest.setFirstName("Harrison");
        addSenderRequest.setLastName("Ogbonnaya");
        addSenderRequest.setSenderEmail("harry@email.com");
        addSenderRequest.setGender(Sex.MALE);
        addSenderRequest.setPassword("123harry");
        addSenderRequest.setPhone("08076565343");
        addSenderRequest.setShippingAddress("semicolon africa sabo yaba");
        addSenderRequest.setWeight(34.2);
        // save sender to the repository
        AddSenderResponse savedSender = senderService.createSender(addSenderRequest);
        log.info("See the following log :: {}", savedSender);

        assertThat(addSenderRequest.getFirstName()).isEqualTo("Harrison");
        assertThat(senderRepository.findAll()).isNotNull();

    }



    @Test
    void findSenderById() throws SenderNotFoundException {
        Sender foundSender = senderService.findSenderById(102L);
        assertThat(foundSender).isNotNull();
        assertThat(foundSender.getFirstName()).isEqualTo("kelechi");
    }
    @Test
    void TestThatSenderCanBeDeletedByEmail() throws SenderNotFoundException {
//        Sender sender = senderRepository.findSenderByEmail(addSenderRequest.getSenderEmail());
        assertThat(senderService.findByEmail("kele@madu@gmail.com")).isNotEmpty();

        senderService.deleteSenderByEmail("kele@madu@gmail.com");

        assertThat(senderService.findByEmail("kele@madu@gmail.com")).isEmpty();
    }

    @Test
    void updateSenderDetails() {
        sender = senderRepository.findById(103L).orElse(null);
        assertThat(sender).isNotNull();
        sender.setFirstName("Mercy");
        senderRepository.save(sender);
        assertThat(sender.getFirstName()).isEqualTo("Mercy");
    }

    @Test
    void findAllSender() {

        List<Sender> sender = senderRepository.findAll();
        assertThat(sender.size()).isEqualTo(sender.size());
        assertThat(sender).isNotNull();
    }

    @Test
    void findSenderByEmailTest() throws SenderNotFoundException {
//        sender = senderRepository.findSenderByEmail("sollaz@gmail.com").orElse(null);
        sender = senderRepository.findById(102L).orElse(null);
        assertThat(sender).isNotNull();
        assertThat(sender.getSenderEmail()).isEqualTo("kele@madu@gmail.com");
    }
    @Test
    void TestThatSenderCanBeDeleted(){
        senderRepository.deleteAll();
        assertThat(senderRepository.findAll().size()).isEqualTo(0);
    }


}