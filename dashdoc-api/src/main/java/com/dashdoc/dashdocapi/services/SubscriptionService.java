package com.dashdocnow.services;

import com.dashdocnow.DTO.SubscriptionDTO;
import com.dashdocnow.entities.Subscription;
import com.dashdocnow.interfaces.DashDocService;
import com.dashdocnow.interfaces.enums.SubscriptionType;
import com.dashdocnow.repository.SubscriptionDAO;
import com.dashdocnow.utils.BadRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional
public class SubscriptionService implements DashDocService<SubscriptionDTO> {
    @Autowired
    private SubscriptionDAO subscriptionDAO;
    @Autowired
    private DozerBeanMapper mapper;

    public SubscriptionDTO getById(Long id) throws JsonProcessingException, BadRequestException {
        if (id < 1)
            throw new BadRequestException("Invalid Subscription ID");

        var asSubscriptionDBO = subscriptionDAO.getById(id);

        return asSubscriptionDBO.asDTO();
    }

    public SubscriptionDTO save(SubscriptionDTO subscription) throws IOException, BadRequestException {
        if (subscription == null)
            throw new BadRequestException("No subscription to create");

        var asSubscriptionDBO = mapper.map(subscription, Subscription.class);

        subscriptionDAO.save(asSubscriptionDBO);

        return asSubscriptionDBO.asDTO();
    }

    public SubscriptionDTO update(SubscriptionDTO subscription) throws JsonProcessingException, BadRequestException {
        if (subscription == null)
            throw new BadRequestException("No subscription to update");

        var asSubscriptionDBO = mapper.map(subscription, Subscription.class);

        subscriptionDAO.update(asSubscriptionDBO);

        return asSubscriptionDBO.asDTO();
    }

    public void updateUserCount(SubscriptionDTO subscription) throws BadRequestException {
        if (subscription.getSubscriptionType() != SubscriptionType.AGENCY)
            return;
        var subscriptionAsDBO = mapper.map(subscription, Subscription.class);

        var userCount = subscriptionAsDBO.getNumberOfUsers();
        subscriptionAsDBO.setNumberOfUsers(userCount + 1);

        subscriptionDAO.update(subscriptionAsDBO);
    }
}
