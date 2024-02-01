function getAll(){
    let dataUrl = remoteUrl + "/api/cart/getCartAll"

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
                        '<th scope="col">流水號</th>'+
                        '<th scope="col">帳號</th>'+
                        '<th scope="col">商品編號</th>'+
                        '<th scope="col">購買數量</th>'+
                    '</tr>'+
                '</thead>'+
                '<tbody id="tbody">'+
                '</tbody>');

            $.each(res, function(i, n){
                $('#tbody').append(
                    '<tr>'+
                        '<th scope="row">'+
                            '<div class="form-check">'+
                                '<input class="form-check-input" type="checkbox" value="" id="checkbox-'+n.cartSeq+'">'+
                            '</div>'+
                        '</th>'+
                        '<td>'+n.cartSeq+'</td>'+
                        '<td>'+n.cartAccount+'</td>'+
                        '<td>'+n.cartCommodityID+'</td>'+
                        '<td>'+n.cartQty+'</td>'+
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
    let dataUrl = remoteUrl + "/api/cart/add"
    let jsonData = { 
                     cartSeq : 0,
                     cartAccount : $('#insert-account').val(),
                     cartCommodityID : Number($('#insert-id').val()),
                     cartQty : Number($('#insert-qty').val())
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
        window.alert("請選擇一個商品!")
        $('#updateModal').modal('hide')
        return
    }
})

$('#updateModal').on('show.bs.modal', function (event) {
    let checkbox = $('.form-check-input:checkbox:checked')

    if (checkbox.length == 1){
        let childList = $(checkbox).parents(".form-check").parent().parent().children("td")
        let formList = $('#update-account').parents("form").children()

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
    let dataUrl = remoteUrl + "/api/cart/update"
    let jsonData = { 
                     cartSeq : Number($('.form-check-input:checkbox:checked').parents(".form-check").parent().next().text()),
                     cartAccount : $('#update-account').val(),
                     cartCommodityID : Number($('#update-id').val()),
                     cartQty : Number($('#update-qty').val())
                   }


    $.ajax({
        url: dataUrl,
        method: 'PUT',
        dataType: 'text',
        data: JSON.stringify(jsonData),
        async: true,      
        contentType: 'application/json;charset=utf-8',
        cache: false,

        success: res => {
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
    let dataUrl = remoteUrl + "/api/cart/remove"
    let idList = [];
    let checkboxes = $('.form-check-input:checkbox:checked')

    if (checkboxes.length == 0){
        window.alert("請先勾選要刪除的商品!")
        return
    }

    checkboxes.each(function (i) {
        idList.push(Number($(this).parents(".form-check").parent().next().text()));
    });

    $.ajax({
        url: dataUrl,
        method: 'DELETE',
        dataType: 'text',
        data: JSON.stringify(idList),
        async: true,      
        contentType: 'application/json;charset=utf-8',
        cache: false,

        success: res => {
            getAll()
        },

        error: err => {
            console.log(err)
            window.alert("刪除失敗!")
        },
    });
}