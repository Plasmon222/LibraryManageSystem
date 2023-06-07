<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>借阅管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <div class="layuimini-main">
            <div class="demoTable">
                <div class="layui-form-item layui-form ">
                    借书卡
                    <div class="layui-inline">
                        <input class="layui-input" name="readerNumber" id="readerNumber" autocomplete="off">
                    </div>
                    图书名称
                    <div class="layui-inline">
                        <input class="layui-input" name="name" id="name" autocomplete="off">
                    </div>
                    归还类型
                    <div class="layui-inline">
                        <select class="layui-input" name="type" id="type" defaultValue=null>
                            <option value=""></option>
                            <option value="0">正常还书</option>
                            <option value="1">延迟还书</option>
                            <option value="2">破损还书</option>
                            <option value="3">丢失</option>
                        </select>
                    </div>
                   图书类型
                    <div class="layui-inline">
                        <select class="layui-input" name="status" id="status" defaultValue=null>
                            <option value=""></option>
                            <option value="0">未借出</option>
                            <option value="1">在借中</option>
                        </select>
                    </div>
                    <button class="layui-btn" data-type="reload">搜索</button>
                </div>
            </div>
        </div>
        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"> 借书 </button>
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="back"> 还书 </button>
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 删除 </button>
            </div>
        </script>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            {{# if(d.backDate==null){ }}
                <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit">异常还书</a>
                <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
            {{# }else{ }}
               <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
            {{# } }}
        </script>

    </div>
</div>
<script src="${pageContext.request.contextPath}/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/base.js"></script>
<script type="text/html" id="time">
    {{#  if(d.backDate !=null){ }}
    <div>{{layui.util.toDateString(d.backDate,'yyyy-MM-dd HH:mm:ss')}}</div>
    {{#  } else {}}
    <div>{{}}</div>
    {{#  } }}
</script>
<script>
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;

        table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/SelectAllLendInfo',//查询类型数据
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'id', width: 80, title: 'ID', sort: true},
                {field: 'readerNumber', width: 180, title: '借书卡号',templet:function (d){
                        return d.readerInfo.readerNumber}},
                // {field: 'name', width: 180, title: '图书名称'},
                {field: 'name', width: 180, title: '图书名称',templet:function (d){
                        return d.bookInfo.name}},
                {field: 'backType', width: 180, title: '归还类型'},
                {field: 'status', width: 180, title: '图书类型',templet:function (d){
                        return d.bookInfo.status}},
                // {field: 'lendDate', width: 180, title: '借出时间'},
                {templet:"<div>{{layui.util.toDateString(d.lendDate,'yyyy-MM-dd HH:mm:ss')}}</div>",
                    width: 200, title: '借出时间'},
                // {field: 'backDate', width: 180, title: '还书时间'},
                // {templet:"<div>{{layui.util.toDateString(d.backDate,'yyyy-MM-dd HH:mm:ss')}}</div>",
                //     width: 200, title: '还书时间'},
                {field: 'backDate', width: 180, title: '还书时间',templet:"#time"},
                {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
            ]],
            done:function (res,curr,count){
                $("[data-field = 'status']").children().each(function (){
                    if ($(this).text()=='1'){
                        $(this).text("在借中");
                    }else if ($(this).text()=='0'){
                        $(this).text("未借出")
                    }
                });
                $("[data-field = 'backType']").children().each(function (){
                    if ($(this).text()==''){
                        $(this).text("未借出");
                    }else if ($(this).text()=='0'){
                        $(this).text("正常还书")
                    }else if ($(this).text()=='1'){
                        $(this).text("延迟还书")
                    }else if ($(this).text()=='2'){
                        $(this).text("破损还书")
                    }else if ($(this).text()=='3'){
                        $(this).text("丢失")
                    }
                })
            },
            limits: [10, 15, 20, 25, 50, 100],
            limit: 15,  <!--默认显示15条-->
            page: true,
            skin: 'line',
            id:'testReload',
            response:{
                statusCode: 200 //规定成功的状态码，默认：0
            }
        });
        // 分页 条件查询
        var $ = layui.$, active = {
            reload: function(){
                var name = $('#name').val();
                var readerNumber = $('#readerNumber').val();
                var backType = $('#type').val();
                var status = $('#status').val();
                console.log(name)
                console.log(readerNumber)
                console.log(backType)
                console.log(status)
                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        name: name,
                        readerNumber:readerNumber,
                        backType:backType,
                        status:status
                    }
                }, 'data');
            }
        };
        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        /**
         * 获取选中记录的id信息
         */
        function getCheackId(data){
            var arr=new Array();
            for(var i=0;i<data.length;i++){
                arr.push(data[i].id);
            }
            //拼接id,变成一个字符串
            return arr.join(",");
        };

        function BackBooksByIds(ids ,index){
            //向后台发送请求
            $.ajax({
                url: "BackBooksByIds",
                type: "POST",
                data: {ids: ids},
                success: function (result) {
                    if (result.code == 200) {//如果成功
                        layer.msg('还书成功', {
                            icon: 6,
                            time: 500
                        }, function () {
                            window.location.reload();
                            var iframeIndex = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(iframeIndex);
                        });
                    } else {
                        layer.msg("还书失败");
                    }
                }
            })
        };
        function deleteLendInfoByIds(ids ,index){
            //向后台发送请求
            $.ajax({
                url: "deleteLendInfoByIds",
                type: "POST",
                data: {ids: ids},
                success: function (result) {
                    if (result.code == 200) {//如果成功
                        layer.msg('删除成功', {
                            icon: 6,
                            time: 500
                        }, function () {
                            window.location.reload();
                            var iframeIndex = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(iframeIndex);
                        });
                    } else {
                        layer.msg("删除失败");
                    }
                }
            })
        };


        /**
         * toolbar栏监听事件
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {  // 监听添加操作
                var index = layer.open({
                    title: '借书',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    <%--content: '${pageContext.request.contextPath}/readerAdd',--%>
                    content: '${pageContext.request.contextPath}/addLendList',
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            }else if(obj.event === 'back'){
                console.log(obj)
                //批量还书模块
                // var checkStatus=table.checkStatus('testReload');
                // var ids=getCheackId(checkStatus);

                var checkStatus=table.checkStatus('testReload');
                // var checkStatus=table.checkStatus(obj.config.id);
                console.log(checkStatus)
                var data=checkStatus.data;
                if(data.length==0){//如果没有选中信息
                    layer.msg("请选择要归还的书籍");
                }else{
                    //获取记录信息的id集合,拼接的ids
                    var ids=getCheackId(data);
                    //获取记录信息的读者id集合,拼接的rds
                    console.log("这是ids-----")
                    console.log(ids)
                    layer.confirm('确定是否还书', function (index) {
                        //调用还书功能
                        BackBooksByIds(ids,index);
                        layer.close(index);
                    });
                }
            } else if (obj.event === 'delete') {
                //获取选中的记录信息
                var checkStatus=table.checkStatus(obj.config.id);
                var data=checkStatus.data;
                if(data.length==0){//如果没有选中信息
                    layer.msg("请选择要删除的记录信息");
                }else{
                    //获取记录信息的id集合,拼接的ids
                    var ids=getCheackId(data);
                    layer.confirm('确定是否删除', function (index) {
                        //调用删除功能
                        deleteLendInfoByIds(ids,index);
                        layer.close(index);
                    });
                }
            }
        });

        /**
         * tool操作栏监听事件
         */
        table.on('tool(currentTableFilter)', function (obj) {
            var data=obj.data;
            console.log("这是id data")
            console.log(data)
            // sessionStorage.setItem('lendid',data.id);
            localStorage.setItem("lendid",JSON.stringify(data.id))
            // 异常还书
            if (obj.event === 'edit') {  // 监听修改操作
                var index = layer.open({
                    title: '异常还书信息',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: '${pageContext.request.contextPath}/excBackBook?id='+data.id,
                    // content: 'pages/book/updateBook.jsp',
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            } else if (obj.event === 'delete') {  // 监听删除操作
                layer.confirm('确定是否删除', function (index) {
                    //调用删除功能
                    deleteInfoByIds(data.id,index);
                    layer.close(index);
                });
            }
        });



    })
</script>
</body>
</html>
