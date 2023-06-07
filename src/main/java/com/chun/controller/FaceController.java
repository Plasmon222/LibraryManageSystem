package com.chun.controller;

//import com.iflytek.cn.entityFaceDao.ServerResponse;
//import com.iflytek.cn.entity.UserFace;
//import com.iflytek.cn.service.FaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/face")
public class FaceController {
//
//    @Autowired
//    FaceService faceService;
//
//
//    @RequestMapping("/jumpGetFace")
//    public String getFace() {
//        return "getFace";
//    }
//
    @RequestMapping("/jumpTakeFace")
    public String takeFace() {
        return "takeFace";
    }
//
//    @RequestMapping("/toIndex")
//    public String index() {
//        return "index";
//    }
//
//    @RequestMapping("/faceRecognize")
//    @ResponseBody
//    public String faceRecognize(@RequestParam(name = "imageBase64") StringBuffer imageBase64, @RequestParam("userToken") String userToken) throws IOException {
//        return faceService.faceRecognize(imageBase64, userToken);
//    }
//    @RequestMapping("/faceSucceed")
//    public String faceLoginSucceed() {
//        System.out.println(1222222);
//        return "succeed";
//    }
//    @RequestMapping("/validateUserToken")
//    @ResponseBody
//    public ServerResponse validateUserToken(UserFace userFace) {
//        int i = faceService.validateUserToken(userFace);
//        if (i == 0) {
//            return ServerResponse.createBySuccess("放心使用", null);
//
//        } else {
//            return ServerResponse.createByError("账号已存在");
//        }
//    }
//
//    @RequestMapping("/saveFaceBase64")
//    @ResponseBody
//    public ServerResponse saveFace(UserFace userFace) {
//        int i = faceService.addUserFace(userFace);
//        if (i > 0) {
//            return ServerResponse.createBySuccess("保存成功", null);
//        } else {
//            return ServerResponse.createByError("保存失败");
//        }
//    }

}
