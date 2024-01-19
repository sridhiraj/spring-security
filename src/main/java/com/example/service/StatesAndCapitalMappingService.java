package com.example.service;

import com.example.dto.StatesAndCapital;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatesAndCapitalMappingService {
    List<StatesAndCapital> statesAndCapitalList = new ArrayList<StatesAndCapital>();

    public List<StatesAndCapital> populateStatesandCapitalMapping(){
        statesAndCapitalList.add(new StatesAndCapital("UP", "Lucknow"));
        statesAndCapitalList.add(new StatesAndCapital("TN","Chennai"));
        statesAndCapitalList.add(new StatesAndCapital("KA","Bangalore"));

        return statesAndCapitalList;
    }

}



