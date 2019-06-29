
new Vue({
    el: '#sub1',
    data: {
        name: 'Hello Vue.js!'
    },
    methods: {
        greet: function (event) {
            // `this` 在方法里指当前 Vue 实例
            /*alert('Hello ' + this.name + '!')
            // `event` 是原生 DOM 事件
            if (event) {
                alert(event.target.tagName)
            }*/

            $.ajax({
                type: "post",
                url: "http://localhost:8081/page/rest/appuser/appLogin.cs",
                data: {account: $("#account").val(), pwd: $("#pwd").val(), type: 1},
                dataType: "json",
                success: function (data) {
                    alert(JSON.stringify(data));
                    location.href = "web/goods/supply.html";

                }
            });
        }

    }
})

