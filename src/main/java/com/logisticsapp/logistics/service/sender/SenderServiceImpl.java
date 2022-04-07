package com.logisticsapp.logistics.service.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.logisticsapp.logistics.data.dto.AddSenderRequest;
import com.logisticsapp.logistics.data.dto.AddSenderResponse;
import com.logisticsapp.logistics.data.models.Sender;
import com.logisticsapp.logistics.data.repository.SenderRepository;
import com.logisticsapp.logistics.utils.ModelMapper;
import com.logisticsapp.logistics.web.exceptions.BusinessLogicException;
import com.logisticsapp.logistics.web.exceptions.SenderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SenderServiceImpl implements SenderService{

    private final SenderRepository senderRepository;

    @Autowired
    public SenderServiceImpl(SenderRepository senderRepository) {
        this.senderRepository = senderRepository;
    }

//    @Autowired
//    SenderService senderService;

//    Sender sender;

    @Override
    public Sender findSenderById(Long senderId) throws SenderNotFoundException {
        // check if Id exists;

        // return Id
        return senderRepository.findById(senderId).orElseThrow(
                ()->new SenderNotFoundException("Sender with the ID: "+ senderId+ " does not exist"));
    }

    @Override
    public AddSenderResponse createSender(AddSenderRequest addSenderRequest) throws BusinessLogicException, SenderNotFoundException {
    // check for sender validity if sender exist
    if(addSenderRequest == null){
        throw new IllegalArgumentException("Argument cannot be null");
    }
    Optional<Sender> query = senderRepository.findByFirstName(addSenderRequest.getFirstName());
    if(query.isPresent()){
        throw new BusinessLogicException("This Sender with first name: "
                + addSenderRequest.getFirstName()+" already exist");
    }
    // create sender
//    addSenderRequest.setSenderId(addSenderRequest.getSenderId());
    Sender sender = new Sender();
    sender.setFirstName(addSenderRequest.getFirstName());
    sender.setLastName(addSenderRequest.getLastName());
    sender.setSenderPhone(addSenderRequest.getPhone());
    sender.setPassword(addSenderRequest.getPassword());
    sender.setGender(addSenderRequest.getGender());
    sender.setSenderEmail(addSenderRequest.getSenderEmail());
    sender.setSenderAddress(addSenderRequest.getShippingAddress());

    Sender savedSender = senderRepository.save(sender);

    AddSenderResponse response = ModelMapper.map(savedSender);
    // save sender to repository
        return response;
    }

    private Sender saveOrUpdate(Sender sender) throws BusinessLogicException {
        if(sender == null){
            throw new BusinessLogicException("Sender cannot be Null");
        }
        return senderRepository.save(sender);
    }
    @Override
    public Sender UpdateSenderDetails(Long senderId, JsonPatch senderPatch) throws SenderNotFoundException, BusinessLogicException {
        Optional<Sender> senderQuery = senderRepository.findById(senderId);
        if(senderQuery.isEmpty()) { throw new SenderNotFoundException("Sender not found");}

        Sender targetSender = senderQuery.get();

        try{
            targetSender = applyPatchToSender(senderPatch, targetSender);
            log.info("Sender after patch -->{}", targetSender);
            return saveOrUpdate(targetSender);
        } catch (BusinessLogicException | JsonProcessingException | JsonPatchException e) {
            throw new BusinessLogicException("Update failed");
        }



    }

    private Sender applyPatchToSender(JsonPatch senderPatch, Sender targetSender) throws JsonProcessingException, JsonPatchException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = senderPatch.apply(objectMapper.convertValue(targetSender, JsonNode.class));

        return objectMapper.treeToValue(patched, Sender.class);
    }

    @Override
    public List<Sender> findAllSender() throws SenderNotFoundException {

        List<Sender> senderList = senderRepository.findAll();
        return senderList;
    }

    @Override
    public Optional<Sender> findByEmail(String senderEmail) throws SenderNotFoundException {
        return senderRepository.findBySenderEmail(senderEmail);
    }

    @Override
    public void deleteSenderAll() { senderRepository.deleteAll();}

    @Override
    public void delete(Long id) throws SenderNotFoundException {

        Sender deleteSender = senderRepository.findById(id).orElseThrow(
                () -> new SenderNotFoundException("Sender with id: "+ id+" does not exist"));

            senderRepository.deleteById(id);

    }
    @Override
    public void deleteSenderByEmail(String senderEmail) throws SenderNotFoundException {
//       Optional<Sender> send = Optional.ofNullable(senderRepository.findSenderByEmail(email).orElseThrow(
//               () -> new SenderNotFoundException("No sender with email found")
//       ));

        Sender senderByEmail = senderRepository.findBySenderEmail(senderEmail).orElseThrow(
                () -> new SenderNotFoundException("No sender with email found " + senderEmail )
        );

        senderRepository.deleteById(senderByEmail.getSenderId());
    }

    public void deleteSenderById(Long id) throws SenderNotFoundException {
        Sender senderByid = senderRepository.findById(id).orElseThrow(
                () -> new SenderNotFoundException("No sender with id :" + id + " found")
        );
        senderRepository.deleteById(senderByid.getSenderId());
    }

}
