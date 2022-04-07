package com.logisticsapp.logistics.web.controllers;

import com.logisticsapp.logistics.data.dto.AddSenderRequest;
import com.logisticsapp.logistics.data.dto.AddSenderResponse;
import com.logisticsapp.logistics.data.models.Sender;
import com.logisticsapp.logistics.service.sender.SenderService;
import com.logisticsapp.logistics.web.exceptions.BusinessLogicException;
import com.logisticsapp.logistics.web.exceptions.SenderNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/sender")
public class SenderControllers {
    private final SenderService senderService;

    public SenderControllers(SenderService senderService) {
        this.senderService = senderService;
    }

    @PostMapping
    public ResponseEntity<?> createSender(@RequestBody AddSenderRequest addSenderRequest){
        try{
            AddSenderResponse savedSender = senderService.createSender(addSenderRequest);
            return ResponseEntity.ok().body(savedSender);
        } catch (BusinessLogicException | SenderNotFoundException e) {
           return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/email/{email}")
    public Optional<Sender> getSender(@PathVariable String email) throws SenderNotFoundException {
        return (senderService.findByEmail(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findSenderById (@PathVariable("id") Long id){
        try{
            Sender savedSender = senderService.findSenderById(id);
            return ResponseEntity.ok().body(savedSender);
        } catch (SenderNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSenderById(@PathVariable("id") Long id) throws SenderNotFoundException {
        try {
            Sender sender = senderService.findSenderById(id);
//            senderService.deleteSenderByEmail(String.valueOf(sender.getSenderEmail()));
            senderService.delete(id);

        } catch (SenderNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Deleted successfully");
    }

}
