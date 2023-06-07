<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <title>借书</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css" media="all">
    <style>
        body {
            background-color: #ffffff;
        }
    </style>
</head>
<body>
<div class="layui-form layuimini-form">
    <!---高级查询图书-->
<%--    <div class="layui-form-item">--%>
<%--        <label class="layui-form-label required">图书名称</label>--%>
<%--        <div class="layui-input-block">--%>
<%--            <input class="layui-input" name="name" id="name1" autocomplete="off" data-type="reload">--%>
<%--        </div>--%>
<%--    </div>--%>
    <div class="layui-form-item">
        <label class="layui-form-label required">借书卡号</label>
        <div class="layui-input-block">
            <input type="text" name="readerNumber" lay-verify="required" lay-reqtext="借书卡不能为空"
                   placeholder="请输入借书卡" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
<%--        <label class="layui-form-label required">图书列表</label>--%>
<%--        <label class="layui-form-label required"> </label>--%>
        <div class="layui-input-block">
            <div class="demoTable">
                <div class="layui-form-item layui-form ">
                    图书编号：
                    <div class="layui-inline">
                        <input class="layui-input" name="isbn" id="isbn" autocomplete="off">
                    </div>
                    书名：
                    <div class="layui-inline">
                        <input class="layui-input" name="name" id="name" autocomplete="off">
                    </div>
                    图书分类：
                    <div class="layui-inline">
                        <select id="typeId" name="typeId">
<%--                            lay-verify="required"--%>
                            <option value="">请选择</option>
                        </select>
                    </div>
                    <button class="layui-btn" data-type="reload">搜索</button>
                </div>
            </div>

            <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>
        </div>
    </div>



    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认借书</button>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
</body>
<script>
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;


        //动态获取图书类型的数据，即下拉菜单，跳出图书类型
        $.get("findAllList", {}, function (data) {
            console.log(data)
            var list = data.data;
            var select = document.getElementById("typeId");
            if (list != null || list.size() > 0) {
                for (var obj in list) {
                    var option = document.createElement("option");
                    option.setAttribute("value", list[obj].id);
                    option.innerText = list[obj].name;
                    select.appendChild(option);
                }
            }
            form.render('select');
            localStorage.setItem("categoryData", JSON.stringify(list))
        }, "json")

        //数据表单
        table.render({
            elem: '#currentTableId',
            <%--url: '${pageContext.request.contextPath}/bookAll',//查询类型数据--%>
            url: 'SelectOneBookInfoStatus',//查询类型数据
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[
                {type: "checkbox", width: 50},
                //{field: 'id', width: 100, title: 'ID', sort: true},
                {field: 'isbn', width: 100, title: '图书编号'},
                {field: 'name', width: 100, title: '图书名称'},
                {field: 'bookTypeInfo', width: 100, title: '图书类型', templet: function (d) {return d.bookTypeInfo.name}},
                {field: 'author', width: 80, title: '作者'},
                {field: 'price', width: 80, title: '价格'},
                {field: 'language', width: 80, title: '语言'},
                {templet:"<div>{{layui.util.toDateString(d.publishDate,'yyyy-MM-dd')}}</div>",
                    width: 200, title: '出版日期'},
                // {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
            ]],
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
            reload: function () {
                var name = $('#name').val();
                var isbn = $('#isbn').val();
                var typeId = $('#typeId').val();
                console.log(name)
                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , where: {
                        name: name,
                        isbn: isbn,
                        typeId: typeId
                    }
                }, 'data');
            }
        };

        $('.demoTable .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });


        //监听表格复选框选择
        table.on('checkbox(currentTableFilter)', function (obj) {
            console.log(obj)
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



        //监听提交
        form.on('submit(saveBtn)', function (data11) {
            var datas=data11.field;//form单中的数据信息
            var readerNumber=datas.readerNumber;
            //
            var checkStatus=table.checkStatus('testReload').data;
            var ids=getCheackId(checkStatus);
            //
            console.log("这是里面的借阅卡号 datas")
            console.log(readerNumber)
            console.log(ids)
            //向后台发送数据提交添加
            $.ajax({
                url:"LendBooksListStatus",
                type:"POST",
                data:{readerNumber, ids},
                success:function(result){
                    if(result.code==200){//如果成功
                        layer.msg('借阅成功',{
                            icon:6,
                            time:500
                        },function(){
                            // parent.window.location.reload();
                            parent.window.location.reload();
                            var iframeIndex = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(iframeIndex);
                        })
                    }else{
                        layer.msg("借阅失败");
                    }
                }
            })
            return false;
        });
    })
</script>
</html>
