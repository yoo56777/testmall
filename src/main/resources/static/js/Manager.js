function getAll(){
    let dataUrl = remoteUrl + "/manager/getAll"

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
                        '<th scope="col">管理員ID</th>'+
                        '<th scope="col">帳號</th>'+
                        '<th scope="col">密碼</th>'+
                        '<th scope="col">鹽值</th>'+
                        '<th scope="col">姓名</th>'+
                        '<th scope="col">電話</th>'+
                        '<th scope="col">信箱</th>'+
                        '<th scope="col">通訊地址</th>'+
                        '<th scope="col">備註</th>'+
                    '</tr>'+
                '</thead>'+
                '<tbody id="tbody">'+
                '</tbody>');

            $.each(res, function(i, n){
                $('#tbody').append(
                    '<tr>'+
                        '<th scope="row">'+
                            '<div class="form-check">'+
                                '<input class="form-check-input" type="checkbox" value="" id="checkbox-'+n.manID+'">'+
                            '</div>'+
                        '</th>'+
                        '<td id="com-id">'+n.manID+'</td>'+
                        '<td>'+n.manAccount+'</td>'+
                        '<td>'+n.manPassword+'</td>'+
                        '<td>'+n.manSalt+'</td>'+
                        '<td>'+n.manName+'</td>'+
                        '<td>'+n.manPhone+'</td>'+
                        '<td>'+n.manEmail+'</td>'+
                        '<td>'+n.manAddress+'</td>'+
                        '<td>'+n.manMsg+'</td>'+
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
    let dataUrl = remoteUrl + "/manager/createManager"
    let jsonData = { 
                     manID : 0,
                     manAccount : $('#insert-account').val(),
                     manPassword : $('#insert-password').val(),
                     manSalt : "",
                     manName : $('#insert-name').val(),
                     manPhone : $('#insert-phone').val(),
                     manEmail : $('#insert-email').val(),
                     manAddress : $('#insert-address').val(),
                     manMsg : $('#insert-note').val()
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
        window.alert("請選擇一個管理員帳號!")
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
            $(formList).eq(i).children("input").val($(childList).eq(i + 1).text())
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
    let dataUrl = remoteUrl + "/manager/updateManager"
    let jsonData = { 
                     manID : $('.form-check-input:checkbox:checked').parents(".form-check").parent().next().text(),
                     manAccount : $('#update-account').val(),
                     manPassword : $('#update-password').val(),
                     manSalt : $('#update-salt').val(),
                     manName : $('#update-name').val(),
                     manPhone : $('#update-phone').val(),
                     manEmail : $('#update-email').val(),
                     manAddress : $('#update-address').val(),
                     manMsg : $('#update-note').val()
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
    let dataUrl = remoteUrl + "/manager/deleteManager"
    let idList = [];
    let checkboxes = $('.form-check-input:checkbox:checked')

    if (checkboxes.length == 0){
        window.alert("請先勾選要刪除的帳號!")
        return
    }

    checkboxes.each(function (i) {
        idList.push(Number($(this).parents(".form-check").parent().next().text()));
    });

    $.ajax({
        url: dataUrl,
        method: 'POST',
        dataType: 'text',
        data: JSON.stringify(idList),
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