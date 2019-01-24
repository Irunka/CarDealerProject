package ua.logos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.logos.domain.SellerDTO;

import ua.logos.entity.SellerEntity;
import ua.logos.exceptions.NotFoundException;
import ua.logos.repository.SellerRepository;
import ua.logos.service.SellerService;
import ua.logos.utils.ObjectMapperUtils;


import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ObjectMapperUtils modelMapper;

    @Override
    public void saveSeller(SellerDTO seller) {
SellerEntity sellerEntity = modelMapper.map(seller,SellerEntity.class);
        sellerRepository.save(sellerEntity);
    }

    @Override
    public List<SellerDTO> findAllSellers() {
        List<SellerEntity>seller=sellerRepository.findAll();
        List <SellerDTO>sellerDTOS=modelMapper.mapAll(seller,SellerDTO.class);
        return sellerDTOS;
    }

    @Override
    public SellerDTO findSellerById(Long id) {
    boolean exists=sellerRepository.existsById(id);
    SellerEntity seller =sellerRepository.findById(id)
            .orElseThrow(
                    ()-> new NotFoundException("Seller with id["+ id+"] not found"));

    SellerDTO sellerDTO = modelMapper.map(seller,SellerDTO.class);
        return sellerDTO;
    }

    @Override
    public SellerDTO updateSeller(Long id, SellerDTO sellerToUpdate) {
        boolean exists=sellerRepository.existsById(id);
        SellerEntity sellerFromDB=sellerRepository.findById(id)
                .orElseThrow(
                        ()-> new NotFoundException("Seller with id ["+ id+"]not found"));

        sellerFromDB.setNumberPhone(sellerToUpdate.getNumberPhone());
        sellerFromDB.setEmail(sellerToUpdate.getEmail());
        sellerFromDB.setFirstName(sellerToUpdate.getFirstName());
        sellerRepository.save(sellerFromDB);
        SellerDTO sellerDTO = modelMapper.map(sellerFromDB,SellerDTO.class);
        sellerDTO.setId(sellerFromDB.getId());
        return  sellerDTO;

    }

      /*  private  SellerDTO entityToDtoMapper(SellerEntity sellerEntity) {
        SellerDTO sellerDTO = new SellerDTO();
        sellerDTO.setId(sellerEntity.getId());
        sellerDTO.setFirstName(sellerEntity.getFirstName());
        sellerDTO.setLastName(sellerEntity.getLastName());
        sellerDTO.setNumberPhone(sellerEntity.getNumberPhone());
        sellerDTO.setEmail(sellerEntity.getEmail());

        return sellerDTO;
    }
        private  SellerEntity dtoToEntityMapper(SellerDTO sellerDTO){
        SellerEntity sellerEntity=new SellerEntity();
        sellerEntity.setId(sellerDTO.getId());
        sellerEntity.setFirstName(sellerDTO.getFirstName());
        sellerEntity.setLastName(sellerDTO.getLastName());
        sellerEntity.setNumberPhone(sellerDTO.getNumberPhone());
        sellerEntity.setEmail(sellerDTO.getEmail());
        return  sellerEntity;
        }*/
}
