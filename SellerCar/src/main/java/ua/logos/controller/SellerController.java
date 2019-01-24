package ua.logos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.logos.domain.SellerDTO;
import ua.logos.entity.SellerEntity;
import ua.logos.service.SellerService;

import java.util.List;

@RestController
@RequestMapping("sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping
    public  ResponseEntity<?> createSeller(
            @RequestBody SellerDTO sellerDTO
    ){
        System.out.println(
                sellerDTO.getFirstName()+" "+
                        sellerDTO.getLastName()+" "+
                        sellerDTO.getNumberPhone()+" "+
                        sellerDTO.getEmail()
        );

        sellerService.saveSeller(sellerDTO);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public  ResponseEntity<?>getSellers(){
        List<SellerDTO> sellers=sellerService.findAllSellers();
        return  new ResponseEntity<>(sellers,HttpStatus.OK);
    }

    @GetMapping("{sellerId:[0-9]{1,5}}")
    public  ResponseEntity<?>getSellerById(@PathVariable("sellerId")Long id) {
        SellerDTO sellers=sellerService.findSellerById(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{sellerId:[0-9]{1,5}}")
   public  ResponseEntity<?>updateSeller(
           @PathVariable("sellerId")Long id,@RequestBody SellerDTO sellerToUpdate
    ){
        SellerDTO seller=sellerService.updateSeller(id,sellerToUpdate);

        if(seller==null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(seller,HttpStatus.OK);
   }

    }

