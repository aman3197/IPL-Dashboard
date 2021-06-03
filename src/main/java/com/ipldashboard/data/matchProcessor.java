package com.ipldashboard.data;

import java.time.LocalDate;

import com.ipldashboard.model.match;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class matchProcessor implements ItemProcessor<matchInput, match> {

  private static final Logger log = LoggerFactory.getLogger(matchProcessor.class);

  @Override
  public match process(final matchInput matchinput) throws Exception {
        match mat = new match();

        mat.setId(Long.parseLong(matchinput.getId()));
        mat.setCity(matchinput.getCity());
        mat.setDate(LocalDate.parse(matchinput.getDate()));
        mat.setPlayerOfMatch(matchinput.getPlayer_of_match());
        mat.setVenue(matchinput.getVenue());
    
        String firstIng,secondIng;

        if(matchinput.getToss_decision().equals("bat")) {
            firstIng = matchinput.getToss_winner();
            secondIng = matchinput.getToss_winner().equals(matchinput.getTeam1())?matchinput.getTeam2():matchinput.getTeam1();
        }
        else {
            secondIng = matchinput.getToss_winner();
            firstIng =  matchinput.getToss_winner().equals(matchinput.getTeam1())?matchinput.getTeam2():matchinput.getTeam1();
        }

        mat.setTeam1(firstIng);
        mat.setTeam2(secondIng);
        mat.setTossWinner(matchinput.getToss_winner());
        mat.setTossDecision(matchinput.getToss_decision());
        mat.setWinner(matchinput.getWinner());
        mat.setResult(matchinput.getResult());
        mat.setResultMargin(matchinput.getResult_margin());
        mat.setUmpire1(matchinput.getUmpire1());
        mat.setUmpire2(matchinput.getUmpire2());
        return mat;
  }

}