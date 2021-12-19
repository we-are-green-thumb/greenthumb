package com.ssh.greenthumb.api.service;

import com.ssh.greenthumb.api.dao.plant.PlantHospitalRepository;
import com.ssh.greenthumb.api.domain.hospital.PlantImageRequest;
import com.ssh.greenthumb.api.domain.hospital.PlantImageResponse;
import com.ssh.greenthumb.api.domain.plant.HospitalPlant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HospitalService {

//    @Autowired
    private final PlantHospitalRepository hospitalDao;

//    @Transactional
    public Object getHospitalAnswer(PlantImageRequest plantImage) {
        RestTemplate restTemplate = new RestTemplate();
        PlantImageResponse plantImageResponse = new PlantImageResponse();
        String flaskResponse;

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("imageUrl", plantImage.getImageUrl());

        ResponseEntity<String> response = restTemplate.postForEntity( "http://localhost:5000/predict", new HttpEntity<>(map) , String.class );
        flaskResponse = response.getBody();

        Optional<HospitalPlant> hospitalPlantOptional = hospitalDao.findByDisease(flaskResponse);

        if(!hospitalPlantOptional.isPresent()){
            plantImageResponse.setDisease("질병 데이터가 없습니다.");
            plantImageResponse.setContent("질병 데이터가 없습니다.");
        }else{
            plantImageResponse.setDisease(flaskResponse);
            plantImageResponse.setContent(hospitalPlantOptional.get().getContent());
        }

        return plantImageResponse;
    }
}
