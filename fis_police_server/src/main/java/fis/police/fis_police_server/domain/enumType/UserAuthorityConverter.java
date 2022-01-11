//package fis.police.fis_police_server.domain.enumType;
//
//import fis.police.fis_police_server.domain.User;
//
//import javax.persistence.AttributeConverter;
//import javax.persistence.Converter;
//import java.util.Arrays;
//
///*
//    날짜 : 2022/01/10 5:06 오후
//    작성자 : 원보라
//    작성내용 : 컨버터
//*/
//
//@Converter
//public class UserAuthorityConverter implements AttributeConverter<UserAuthority, String> {
//
//
//    @Override
//    public String convertToDatabaseColumn(UserAuthority attribute) {
//        return attribute !=null ? attribute.getUserAuthority() : null;
//
//    }
//
//    @Override
//    public UserAuthority convertToEntityAttribute(String dbData) {
//        return dbData != null ? Arrays.stream(UserAuthority.values())
//                .filter(type -> type.getUserAuthority().equals(dbData))
//                .findFirst()
//                .orElseThrow(IllegalArgumentException::new)
//                : null;
//    }
//}
