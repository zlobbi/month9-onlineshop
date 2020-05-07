package km.month9.myshop.domain.smartphone;

import km.month9.myshop.domain.exception.ResourceNotFoundException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SmartphoneService {
    private final SmartphoneRepository smartphoneRepository;
    private final BrandRepository brandRepository;

    public List<SmartphoneDTO> findAllProducts() {
        return smartphoneRepository.findAll().stream()
                .map(SmartphoneDTO::from).collect(Collectors.toList());
    }

    public List<SmartphoneDTO.BrandDTO> findAllProductTypes() {
        return brandRepository.findAll().stream()
                .map(SmartphoneDTO.BrandDTO::from).collect(Collectors.toList());
    }

    public Page<SmartphoneDTO> getSmartphones(Pageable pageable) {
        return smartphoneRepository.findAll(pageable).map(SmartphoneDTO::from);
    }

    public Page<SmartphoneDTO> searchSmartphones(Pageable pageable, String param, String text) {
        Page<SmartphoneDTO> sm;
        if(param.equals("by name")) {
            sm = smartphoneRepository.findAllByNameContains(pageable, text).map(SmartphoneDTO::from);
        } else if(param.equals("by brand")) {
            sm = smartphoneRepository.findAllByBrandContains(pageable, text).map(SmartphoneDTO::from);
        } else if(param.equals("by price")) {
            sm = smartphoneRepository.findAllByPriceIsLessThanEqual(pageable, Float.parseFloat(text.trim())).map((SmartphoneDTO::from));
        } else {
            sm = smartphoneRepository.findAllByDescriptionContains(pageable, text).map(SmartphoneDTO::from);
        }
        return sm;
    }
}
