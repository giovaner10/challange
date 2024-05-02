package br.com.omnilink.challange.enums.vehicle;

import br.com.omnilink.challange.exception.TypetNotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum VehicleBrand {
    TOYOTA(1),
    HONDA(2),
    FORD(3),
    BMW(4),
    AUDI(5),
    VOLKSWAGEN(6),
    TESLA(7),
    CHEVROLET(8),
    NISSAN(9),
    HYUNDAI(10),
    KIA(11),
    MAZDA(12),
    SUBARU(13),
    JEEP(14),
    PORSCHE(15),
    LEXUS(16),
    FIAT(17),
    SUZUKI(18),
    JAGUAR(19),
    LAND_ROVER(20),
    MITSUBISHI(21),
    VOLVO_TRUCKS(22),
    MERCEDES_BENZ_TRUCKS(23),
    SCANIA(24),
    DAF(25),
    MAN_TRUCKS(26),
    IVECO(27),
    HINO(28),
    KENWORTH(29),
    PETERBILT(30),
    MACK_TRUCKS(31),
    INTERNATIONAL_TRUCKS(32),
    FREIGHTLINER(33),
    WESTERN_STAR(34),
    RENAULT_TRUCKS(35),
    ISUZU_TRUCKS(36),
    FUSO(37),
    UD_TRUCKS(38),
    TATA_MOTORS(39),
    ASHOK_LEYLAND(40),
    SINOTRUK(41),
    FAW(42),
    BEIBEN(43),
    SHACMAN(44),
    JAC_MOTORS(45),
    DONGFENG(46),
    CAMC(47),
    CIMC(48),
    JMC(49),
    JMC_SPECIAL_VEHICLE(50),
    YUEJIN(51),
    FOTON(52),
    DAYUN(53),
    JINBEI(54),
    ZHONGTONG(55),
    GOLDEN_DRAGON(56),
    HUANGHAI(57),
    KING_LONG(58),
    YUTONG(59),
    ANKAI(60),
    JIULONG(61),
    BYD(62);

    private final Integer idBrand;

    VehicleBrand(Integer id) {
        this.idBrand = id;
    }

    public static VehicleBrand fromIdBrand(Integer idBrand) {
        return Arrays.stream(values())
                .filter(vehicleBrand -> vehicleBrand.idBrand.equals(idBrand))
                .findFirst()
                .orElseThrow(() -> new TypetNotFoundException("Invalid code VehicleBrand"));
    }

}

