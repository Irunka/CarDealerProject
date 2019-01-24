package ua.logos.service;

import ua.logos.domain.SellerDTO;
import ua.logos.entity.SellerEntity;

import java.util.List;

public interface SellerService {

    void  saveSeller(SellerDTO seller);


    List<SellerDTO> findAllSellers();


    SellerDTO findSellerById(Long id);

    SellerDTO updateSeller(Long id,SellerDTO sellerToUpdate);
}
