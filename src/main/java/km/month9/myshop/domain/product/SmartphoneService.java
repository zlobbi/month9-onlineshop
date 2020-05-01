package km.month9.myshop.domain.product;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
