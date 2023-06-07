let userToken;
$(function () {
    userToken = localStorage.getItem("userToken");
    if(userToken){
        $("#iflytekLogo").html(" <img class=\"mb-3\" id=\"iflytekImage\" src=\"../asserts/img/iflytek.png\" alt=\"\" width=\"272\" height=\"72\">");
        let mediaStreamTrack = null;
        openMedia();
        setTimeout("openCamera()", "1000");
        setTimeout("faceToCamera()", "2000");
        setTimeout("takePhoto()", "2000");
    }else
    {
        layer.msg("账号不准确",{icon: 2, time: 2000}, function () {
            window.location.href = "/face/toIndex"
        });
    }


})
var number = 0;

function openCamera() {
    $("#flag").html("正在打开摄像头")
}

function faceToCamera() {
    $("#flag").html("请正视摄像头")
}

function backToIndex() {
    window.location.href = "/";
}

function openMedia() {
    let constraints = {
        video: {width: 400, height: 400},
        audio: false
    };
    //获得video摄像头
    let video = document.getElementById('video');
    let promise = navigator.mediaDevices.getUserMedia(constraints);
    promise.then((mediaStream) => {
        mediaStreamTrack = typeof mediaStream.stop === 'function' ? mediaStream : mediaStream.getTracks()[1];
        video.srcObject = mediaStream;
        video.play();
    });
}

// 拍照
function takePhoto() {
    //获得Canvas对象
    number++;
    let video = document.getElementById('video');
    let canvas = document.getElementById('canvas');
    let ctx = canvas.getContext('2d');
    ctx.drawImage(video, 0, 0, 400, 400);
    // toDataURL  ---  可传入'image/png'---默认, 'image/jpeg'
    let img = document.getElementById('canvas').toDataURL();
    // 这里的img就是得到的图片
    console.log('img-----', img);
    document.getElementById('imgTag').src = img;
    $("#flag").html("正在识别");
    $.ajax({
        url: "/face/faceRecognize",    //请求的url地址
        dataType: "json",   //返回格式为json
        async: true,//请求是否异步，默认为异步，这也是ajax重要特性
        // contentType:"application/json",
        data: {"imageBase64": img,"userToken":userToken.toString()}, //参数值
        type: "POST", //请求方式
        success: function (data) {
            data = JSON.parse(data)
            console.log(data)
            if (data.ret == 1 || data.score<0.88) {
                $("#flag").css("color","red");
                $("#flag").html("识别失败，请保持人像处于框内 2秒后重新识别");
                if (number < 3) {
                    setTimeout("takePhoto()", "3000");
                } else {
                    $("#flag").css("color","red");
                    $("#flag").html("识别失败请使用账号密码登录 三秒后回到主页");
                    setTimeout("backToIndex()", "3000");
                }
            }
            if (data.score > 0.88) {
                window.location.href = "/face/faceSucceed"
            }


        }
    })

}

// 关闭摄像头
function closeMedia() {
    mediaStreamTrack.stop();
}