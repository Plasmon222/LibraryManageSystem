<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false"%>
<html>
<head>
    <title>统计分析</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css" media="all">
    <script src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8"></script>
</head>
<body>



    <div style="width: 100%;height: 300px" id="monitorZhu"></div>
    <div style="width: 100%;height: 300px" id="monitorBing"></div>
<%--    <div style="width: 100%;height: 300px" id="monitorZhe"></div>--%>

<script type="text/javascript">
    layui.config({
        version:1,
        base:'${pageContext.request.contextPath}/js/lay-module/echarts/'
    }).use(['jquery','element','echarts'],function (){
        var $=layui.jquery;
        var element=layui.element;
        var echarts=layui.echarts;

        let nameArray=new Array();
        <c:forEach items="${list}" var="analysis">
            nameArray.push(`${analysis.name}`)
        </c:forEach>
        console.log(nameArray)

        let counts=new Array();
        <c:forEach items="${list}" var="analysis">
            counts.push(`${analysis.counts}`)
        </c:forEach>
        console.log(counts)

        var monitorZhu = echarts.init(document.getElementById('monitorZhu'));
        var monitorBing = echarts.init(document.getElementById('monitorBing'));
        // var monitorZhe = echarts.init(document.getElementById('monitorZhe'));
        //指定图表配置项和数据
        var optionchart = {
            title: {
                text: '图书借阅量分析'
            },
            tooltip: {},
            legend: {
                data: ['数据量']
            },
            xAxis: {
                data:nameArray
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                name: '数据量',
                type: 'bar', //柱状
                data: counts,
                itemStyle: {
                    normal: { //柱子颜色
                        color: 'red'
                    }
                },
            }]
        };
        //
        // var optionchartZhe = {
        //     title: {
        //         text: '图书借阅量分析'
        //     },
        //     tooltip: {},
        //     legend: { //顶部显示 与series中的数据类型的name一致
        //         data: ['三国演义', '资本论', '经济学管理', '大局下的中国法制', '庆余年']
        //     },
        //     xAxis: {
        //         // type: 'category',
        //         // boundaryGap: false, //从起点开始
        //         data: ['周一', '周二', '周三', '周四', '周五','周六','周日']
        //     },
        //     yAxis: {
        //         type: 'value'
        //     },
        //     series: [{
        //         name: '三国演义',
        //         type: 'line', //线性
        //         data: [50, 51, 35, 72, 45, 36, 29],
        //     }, {
        //         name: '资本论',
        //         type: 'line', //线性
        //         data: [24, 34, 26, 15, 9, 11, 17],
        //     }, {
        //         smooth: true, //曲线 默认折线
        //         name: '经济学管理',
        //         type: 'line', //线性
        //         data: [21, 32, 11, 34, 12, 13, 16],
        //     }, {
        //         smooth: true, //曲线
        //         name: '大局下的中国法制',
        //         type: 'line', //线性
        //         data: [22, 33, 41, 53, 40, 38, 42],
        //     },
        //         {
        //         smooth: true, //曲线
        //         name: '庆余年',
        //         type: 'line', //线性
        //         data: [22, 33, 41, 54, 69, 73, 82],
        //     }
        //     ]
        // };
        //

        let bing=new Array()
        <c:forEach items="${list}" var = "analysis">
            bing.push({
                value:`${analysis.counts}`,
                name:`${analysis.name}`
            })
        </c:forEach>
        var optionchartBing = {
            title: {
                text: '数据分析',
                x: 'center' //标题居中
            },
            tooltip: {
                // trigger: 'item' //悬浮显示对比
            },
            legend: {
                orient: 'vertical', //类型垂直,默认水平
                left: 'left', //类型区分在左 默认居中
                data: nameArray,
            },
            series: [{
                type: 'pie', //饼状
                radius: '60%', //圆的大小
                center: ['50%', '50%'], //居中
                data: bing,
            }]
        };
        monitorZhu.setOption(optionchart, true);       // 柱状图
        // monitorZhe.setOption(optionchartZhe, true);    // 折线图
        monitorBing.setOption(optionchartBing, true);      // 饼状图

    });

</script>
</body>
</html>
