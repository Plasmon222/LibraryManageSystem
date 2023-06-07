<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>管理员管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css" media="all">
    <script src="${pageContext.request.contextPath}/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <div class="demoTable">
            <div class="layui-form-item layui-form">
                用户名：
                <div class="layui-inline">
                    <input class="layui-input" name="username" id="username" autocomplete="off">
                </div>
                管理员类型：
                <div class="layui-inline">
                    <select id="adminType" name="adminType" class="layui-input">
                        <option value="">请选择</option>
                        <option value="0">普通管理员</option>
                        <option value="1">高级管理员</option>
                    </select>
                </div>
                <button class="layui-btn" data-type="reload">搜索</button>
            </div>
        </div>

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"> 添加 </button>
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 删除 </button>
            </div>
        </script>

        <!--表单，查询出的数据在这里显示-->
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit">修改密码</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
        </script>

    </div>
</div>
</body>
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
            url: '${pageContext.request.contextPath}/selectAllAdmin',//查询类型数据
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[
                {type: "checkbox", width: 50},
                // {field: 'id', width: 80, title: 'ID', sort: true},
                {field: 'username', width: 180, title: '用户名'},
                {field: 'password', width: 180, title: '密码'},
                {field: 'adminType', width: 180, title: '管理员类型'},
                {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
            ]],
            done:function (res,curr,count){
                $("[data-field = 'adminType']").children().each(function (){
                    if ($(this).text()=='1'){
                        $(this).text("超级管理员");
                    }else if ($(this).text()=='0'){
                        $(this).text("管理员")
                    }
                })},
            limits: [10, 15, 20, 25, 50, 100],
            limit: 15,  <!--默认显示15条-->
            page: true,
            skin: 'line',
            id: 'testReload',
            response: {
                statusCode: 200 //规定成功的状态码，默认：0
            }
        });

        var $ = layui.$, active = {
            reload: function(){
                var topic = $('#topic').val();
                console.log(topic)
                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        topic: topic,
                    }
                }, 'data');
            }
        };
        //点击搜索
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

        function delAdmin(ids ,index){
            //向后台发送请求
            $.ajax({
                url: "delAdmin",
                type: "POST",
                data: {ids: ids},
                success: function (result) {
                    console.log(result);
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
                    title: '添加管理员',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: '${pageContext.request.contextPath}/addAdminPage',
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            }else if (obj.event === 'delete') {
                //获取选中的记录信息
                var checkStatus=table.checkStatus(obj.config.id);
                var data=checkStatus.data;
                if(data.length==0){//如果没有选中信息
                    layer.msg("请选择要删除的记录信息");
                }else{
                    //获取记录信息的id集合,拼接的ids
                    var ids=getCheackId(data);
                    console.log(ids);
                    layer.confirm('确定是否删除', function (index) {
                        //调用删除功能
                        delAdmin(ids,index);
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
            // localStorage.setItem("adminid",JSON.stringify(data.id))
            console.log("这是id data")
            var adminid=data.id
            console.log(adminid)
            ${session.setAttribute("upadminid",adminid)}


            if (obj.event === 'edit') {  // 监听修改操作
                var index = layer.open({
                    title: '查看详情',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: '${pageContext.request.contextPath}/updateAdminPage?id='+data.id,
                    <%--content: '${pageContext.request.contextPath}/updateAdminPage'--%>
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            } else if (obj.event === 'delete') {  // 监听删除操作
                layer.confirm('确定是否删除', function (index) {
                    //调用删除功能
                    delAdmin(data.id,index);
                    layer.close(index);
                });
            }
        });



    });
</script>
</html>
