$(function(){
    layui.use(['form'], function() {

        var $ = layui.$ //由于layer弹层依赖jQuery，所以可以直接得到
            , form = layui.form
        //现货搜索
        form.on('submit(goods_in_stock_search)', function (data) {
            var keyvalue = data.field.goods_in_stock_search;
            window.location.href = window.requestUrl + "web/gstock/gstock.jsp?keyvalue=" + keyvalue;
            return false;
        });

        //求购搜索
        form.on('submit(want_to_buy_search)', function (data) {
            var keyvalue = data.field.goods_in_stock_search;
            window.location.href = window.requestUrl + "web/supply/supply.jsp?keyvalue=" + keyvalue;
            return false;
        });
    });
});