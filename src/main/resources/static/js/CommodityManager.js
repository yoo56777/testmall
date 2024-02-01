function getAll(){
    let dataUrl = remoteUrl + "/com/getAll"

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
                        '<th scope="col">商品編號</th>'+
                        '<th scope="col">商品名稱</th>'+
                        '<th scope="col">庫存數量</th>'+
                        '<th scope="col">價格</th>'+
                        '<th scope="col">商品分類</th>'+
                        '<th scope="col">圖片位址</th>'+
                        '<th scope="col">詳細資訊</th>'+
                        '<th scope="col">銷售中</th>'+
                        '<th scope="col">折扣</th>'+
                        '<th scope="col">幾折</th>'+
                    '</tr>'+
                '</thead>'+
                '<tbody id="tbody">'+
                '</tbody>');

            $.each(res, function(i, n){
                $('#tbody').append(
                    '<tr>'+
                        '<th scope="row">'+
                            '<div class="form-check">'+
                                '<input class="form-check-input" type="checkbox" value="" id="checkbox-'+n.commodityID+'">'+
                            '</div>'+
                        '</th>'+
                        '<td id="com-id">'+n.commodityID+'</td>'+
                        '<td>'+n.commodityName+'</td>'+
                        '<td>'+n.commodityQty+'</td>'+
                        '<td>'+n.commodityPrice+'</td>'+
                        '<td>'+n.commodityTag+'</td>'+
                        '<td>'+n.commodityImgPath+'</td>'+
                        '<td>'+n.commodityDetail+'</td>'+
                        '<td>'+n.commoditySaleFlag+'</td>'+
                        '<td>'+n.commodityDiscount+'</td>'+
                        '<td>'+n.commodityDisRate+'</td>'+
                    '</tr>');
            })
        },
    // <thead>
    //     <tr>
    //         <th scope="col"></th>
    //         <th scope="col">商品編號</th>
    //         <th scope="col">商品名稱</th>
    //         <th scope="col">庫存數量</th>
    //         <th scope="col">價格</th>
    //         <th scope="col">商品分類</th>
    //         <th scope="col">圖片位址</th>
    //         <th scope="col">詳細資訊</th>
    //         <th scope="col">銷售中</th>
    //         <th scope="col">折扣</th>
    //         <th scope="col">幾折</th>
    //     </tr>
    // </thead>
    // <tbody>
    //     <tr>
    //         <th scope="row">
    //             <div class="form-check">
    //                 <input class="form-check-input" type="checkbox" value="" id="checkbox-1">
    //             </div>
    //         </th>
    //         <td>1</td>
    //         <td>Mark</td>
    //         <td>Otto</td>
    //         <td>@mdo</td>
    //         <td>@mdo</td>
    //         <td>@mdo</td>
    //         <td>@mdo</td>
    //         <td>@mdo</td>
    //         <td>@mdo</td>
    //         <td>@mdd</td>
    //     </tr>
    // </tbody>
        error: err => {
            console.log(err)
            $('.table').append("<p>查無資料</p>");
        },
    });
}


$('#insertModal').on('show.bs.modal', function (event) {
    // // Button that triggered the modal
    // var button = event.relatedTarget
    // // Extract info from data-bs-* attributes
    // var recipient = button.getAttribute('data-bs-whatever')
    // // If necessary, you could initiate an AJAX request here
    // // and then do the updating in a callback.
    // //
    // // Update the modal's content.
    // var modalTitle = insertModal.querySelector('.modal-title')
    // var modalBodyInput = insertModal.querySelector('.modal-body input')

    $('#insert-imgPath').val("static/images/")
    
})

$('#insertModal').on('hide.bs.modal', function (event) {
    let formList = $('#insert-name').parents("form").children()

    for (let i = 0; i < formList.length; i++){
        $(formList).eq(i).children("input").val(null)
    }
})

function insertItem(){
    let dataUrl = remoteUrl + "/com/insertItem"
    let jsonData = { 
                     commodityID : 0,
                     commodityName : $('#insert-name').val(),
                     commodityQty : Number($('#insert-qty').val()),
                     commodityPrice : Number($('#insert-price').val()),
                     commodityTag : $('#insert-tag').val(),
                     commodityImgPath : $('#insert-imgPath').val(),
                     commodityDetail : $('#insert-detail').val(),
                     commoditySaleFlag : Number($('#insert-sale').val()),
                     commodityDiscount : Number($('#insert-discount').val()),
                     commodityDisRate : Number($('#insert-rate').val())
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
        window.alert("請選擇一個商品!")
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
    let dataUrl = remoteUrl + "/com/updateItem"
    let jsonData = { 
                     commodityID : Number($('.form-check-input:checkbox:checked').parents(".form-check").parent().next().text()),
                     commodityName : $('#update-name').val(),
                     commodityQty : Number($('#update-qty').val()),
                     commodityPrice : Number($('#update-price').val()),
                     commodityTag : $('#update-tag').val(),
                     commodityImgPath : $('#update-imgPath').val(),
                     commodityDetail : $('#update-detail').val(),
                     commoditySaleFlag : Number($('#update-sale').val()),
                     commodityDiscount : Number($('#update-discount').val()),
                     commodityDisRate : Number($('#update-rate').val())
                   }

    $.ajax({
        url: dataUrl,
        method: 'PATCH',
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
    let dataUrl = remoteUrl + "/com/deleteItem"
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
            window.alert(res)
            getAll()
        },

        error: err => {
            console.log(err)
            window.alert("刪除失敗!")
        },
    });
}