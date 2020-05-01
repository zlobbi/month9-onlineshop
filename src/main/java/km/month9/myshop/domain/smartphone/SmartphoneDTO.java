package km.month9.myshop.domain.smartphone;

import lombok.*;

@Getter
@Setter
@ToString
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SmartphoneDTO {

    private int id;
    private String name;
    private String image;
    private BrandDTO brand;
    private String description;
    private float price;

    static SmartphoneDTO from(Smartphone smartphone) {
        return builder()
                .id(smartphone.getId())
                .name(smartphone.getName())
                .brand(BrandDTO.from(smartphone.getBrand()))
                .description(smartphone.getDescription())
                .image(smartphone.getImage())
                .price(smartphone.getPrice())
                .build();
    }

    @Getter
    @Setter
    @ToString
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class BrandDTO {
        private int id;
        private String name;
        private String icon;

        public static BrandDTO from(Brand brand) {
            return builder()
                    .id(brand.getId())
                    .name(brand.getName())
                    .icon(brand.getIcon())
                    .build();
        }
    }
}
