package com.sbbank.cards.service;

import com.sbbank.cards.constants.CardsConstants;
import com.sbbank.cards.dto.CardsDto;
import com.sbbank.cards.entity.Cards;
import com.sbbank.cards.exception.CardAlreadyExistsException;
import com.sbbank.cards.exception.ResourceNotFoundException;
import com.sbbank.cards.mapper.CardsMapper;
import com.sbbank.cards.repository.CardsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService{

    private CardsRepository cardsRepository;

    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> card = cardsRepository.findByMobileNumber(mobileNumber);
        if(card.isPresent()){
            throw new CardAlreadyExistsException("Card already present for current mobile number");
        }
        cardsRepository.save(createNewCard(mobileNumber));
    }



    private Cards createNewCard(String mobileNumber){
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }
    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Card", "mobile number", mobileNumber)
        );
        return CardsMapper.mapToCardsDto(cards,new CardsDto());
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {

        Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                ()->new ResourceNotFoundException("Card","card number", cardsDto.getCardNumber())
        );

        cardsRepository.save(CardsMapper.mapToCards(cardsDto,cards));
        return true;

    }

    @Override
    public boolean deleteCard(String mobileNumber) {

        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Card","mobile number",mobileNumber)
        );
        cardsRepository.deleteById(cards.getCardId());
        return true;
    }

}
