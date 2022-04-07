package com.logisticsapp.logistics.service.sender;

import com.github.fge.jsonpatch.JsonPatch;
import com.logisticsapp.logistics.data.dto.AddSenderRequest;
import com.logisticsapp.logistics.data.dto.AddSenderResponse;
import com.logisticsapp.logistics.data.models.Sender;
import com.logisticsapp.logistics.web.exceptions.BusinessLogicException;
import com.logisticsapp.logistics.web.exceptions.SenderNotFoundException;

import java.util.List;
import java.util.Optional;

public interface SenderService {
    Sender findSenderById(Long id) throws SenderNotFoundException;
    AddSenderResponse createSender(AddSenderRequest senderDto) throws BusinessLogicException, SenderNotFoundException;
    Sender UpdateSenderDetails(Long id, JsonPatch senderPatch) throws SenderNotFoundException, BusinessLogicException;
    List<Sender> findAllSender()throws SenderNotFoundException;
    Optional<Sender> findByEmail(String email) throws SenderNotFoundException;
    void deleteSenderAll();
    void deleteSenderByEmail(String email) throws SenderNotFoundException;
    void delete(Long id) throws SenderNotFoundException;
}
