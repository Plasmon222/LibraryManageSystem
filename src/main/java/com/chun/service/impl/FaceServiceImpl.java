//package com.chun.service.impl;
//
//import com.chun.entity.UserFace;
//import com.chun.entity.WebFaceCompare;
//import com.chun.service.FaceService;
//import com.chun.utils.GsonUtils;
//import com.chun.dao.FaceDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Base64;
//import java.util.Map;
//
//@Service
//public class FaceServiceImpl implements FaceService {
//
//    @Autowired
//    FaceDao faceDao;
//
//    @Override
//    public int addUserFace(UserFace userFace) {
//        return faceDao.addUserFace(userFace);
//    }
//
//    @Override
//    public int validateUserToken(UserFace userFace) {
//        Map<String, Long> countByToken = faceDao.getCountByToken(userFace);
//        return countByToken.get("count").intValue();
//    }
//
//    @Override
//    public String faceRecognize(StringBuffer imageBase64,String userToken) {
//        String back = "{\n" +
//                "\t\"ret\" : 1,\n" +
//                "\t\"score\" : 0.86\n" +
//                "}";
//        UserFace savedUserFace = faceDao.getUserByToken(userToken);
//        if(savedUserFace!=null){
//            WebFaceCompare demo = new WebFaceCompare();
//            WebFaceCompare.ResponseData respData = null;
//            try {
//                respData = demo.faceContrastImageBase64(imageBase64.toString(), savedUserFace.getUserFaceBase64());
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//            if (respData != null && respData.getPayLoad().getFaceCompareResult() != null) {
//                String textBase64 = respData.getPayLoad().getFaceCompareResult().getText();
//                String text = new String(Base64.getDecoder().decode(textBase64));
//                System.out.println("人脸比对结果(text)base64解码后：");
//                System.out.println(text);
//                back = text;
//            }
//        }
//
//        return GsonUtils.toJson(back);
//    }
//}
