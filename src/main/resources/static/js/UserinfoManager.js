function getAll(){
    let dataUrl = remoteUrl + "/user/getAll"

    $('.table').empty();

    $.ajax({
        url: dataUrl,
        method: 'GET',
        dataType: 'json',
        data: '',
        async: true,      

        success: res => {
            console.log(res)

            $('.table').append(
                '<thead>'+
                    '<tr>'+
                        '<th scope="col"></th>'+
                        '<th scope="col">帳號</th>'+
                        '<th scope="col">密碼</th>'+
                        '<th scope="col">鹽值</th>'+
                        '<th scope="col">姓名</th>'+
                        '<th scope="col">電話</th>'+
                        '<th scope="col">信箱</th>'+
                        '<th scope="col">通訊地址</th>'+
                        '<th scope="col">訊息</th>'+
                    '</tr>'+
                '</thead>'+
                '<tbody id="tbody">'+
                '</tbody>');

            $.each(res, function(i, n){
                $('#tbody').append(
                    '<tr>'+
                        '<th scope="row">'+
                            '<div class="form-check">'+
                                '<input class="form-check-input" type="checkbox" value="" id="checkbox-'+n.userAccount+'">'+
                            '</div>'+
                        '</th>'+
                        '<td>'+n.userAccount+'</td>'+
                        '<td>'+n.userPassword+'</td>'+
                        '<td>'+n.userSalt+'</td>'+
                        '<td>'+n.userName+'</td>'+
                        '<td>'+n.userPhone+'</td>'+
                        '<td>'+n.userEmail+'</td>'+
                        '<td>'+n.userAddress+'</td>'+
                        '<td>'+n.userMsg+'</td>'+
                    '</tr>');
            })
        },
        error: err => {
            console.log(err)
            $('.table').append("<p>查無資料</p>");
        },
    });
}

$('#insertModal').on('hide.bs.modal', function (event) {
    let formList = $('#insert-name').parents("form").children()

    for (let i = 0; i < formList.length; i++){
        $(formList).eq(i).children("input").val(null)
    }
})

function insertItem(){
    let dataUrl = remoteUrl + "/user/createUser"
    let jsonData = { 
                     userAccount : $('#insert-account').val(),
                     userPassword : $('#insert-password').val(),
                     userSalt : "",
                     userName : $('#insert-name').val(),
                     userPhone : $('#insert-phone').val(),
                     userEmail : $('#insert-email').val(),
                     userAddress : $('#insert-address').val(),
                     userMsg : $('#insert-note').val()
                   }
    console.log(jsonData)

    $.ajax({
        url: dataUrl,
        method: 'POST',
        dataType: 'text',
        data: JSON.stringify(jsonData),
        async: true,      
        contentType: 'application/json;charset=utf-8',
        cache: false,

        success: res => {
            window.alert(res)
            $('#insertModal').modal('hide')
            getAll()
        },

        error: err => {
            console.log(err)
            window.alert("新增失敗!")
        },
    });
}

$('#updateModal').on('shown.bs.modal', function (event) {
    let checkbox = $('.form-check-input:checkbox:checked')

    if (checkbox.length != 1){
        window.alert("請選擇一個使用者帳號!")
        $('#updateModal').modal('hide')
        return
    }
})

$('#updateModal').on('show.bs.modal', function (event) {
    let checkbox = $('.form-check-input:checkbox:checked')

    if (checkbox.length == 1){
        let childList = $(checkbox).parents(".form-check").parent().parent().children("td")
        let formList = $('#update-name').parents("form").children()

        for (let i = 0; i < formList.length; i++){
            $(formList).eq(i).children("input").val($(childList).eq(i).text())
        }
    }
})

$('#updateModal').on('hide.bs.modal', function (event) {
    let formList = $('#update-name').parents("form").children()

    for (let i = 0; i < formList.length; i++){
        $(formList).eq(i).children("input").val(null)
    }
})

function updateItem(){
    let dataUrl = remoteUrl + "/user/updateUser"
    let jsonData = { 
                     userAccount : $('#update-account').val(),
                     userPassword : $('#update-password').val(),
                     userSalt : $('#update-salt').val(),
                     userName : $('#update-name').val(),
                     userPhone : $('#update-phone').val(),
                     userEmail : $('#update-email').val(),
                     userAddress : $('#update-address').val(),
                     userMsg : $('#update-note').val()
                   }

    $.ajax({
        url: dataUrl,
        method: 'POST',
        dataType: 'text',
        data: JSON.stringify(jsonData),
        async: true,      
        contentType: 'application/json;charset=utf-8',
        cache: false,

        success: res => {
            window.alert(res)
            $('#updateModal').modal('hide')
            getAll()
        },

        error: err => {
            console.log(err)
            window.alert("更新失敗!")
        },
    });
}

function deleteItem(){
    let dataUrl = remoteUrl + "/user/deleteUser"
    let accounts = [];
    let checkboxes = $('.form-check-input:checkbox:checked')

    if (checkboxes.length == 0){
        window.alert("請先勾選要刪除的帳號!")
        return
    }

    checkboxes.each(function (i) {
        accounts.push($(this).parents(".form-check").parent().next().text());
    });

    $.ajax({
        url: dataUrl,
        method: 'POST',
        dataType: 'text',
        data: JSON.stringify(accounts),
        async: true,      
        contentType: 'application/json;charset=utf-8',
        cache: false,

        success: res => {
            window.alert(res)
            getAll()
        },

        error: err => {
            console.log(err)
            window.alert("刪除失敗!")
        },
    });
}