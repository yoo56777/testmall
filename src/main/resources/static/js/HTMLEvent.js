function getAll(){
//    var dataUrl = "http://172.16.82.2:9090/com/getAll"
    var dataUrl = "http://localhost:9090/com/getAll"

    $('#com-list').empty();
    $.ajax({
        url: dataUrl,
        method: 'GET',
        dataType: 'json',
        data: '',
        async: true,

        success: res => {
            console.log(res)
            $.each(res, function(i, n){
                $('#com-list').append('<div class="items">'+
                                           '<a href="CommodityDetail.html?id='+n.commodityID+'" style="display:block">'+
                                           '<img src='+n.commodityImgPath+'>'+
                                           '<p>'+n.commodityName+'</p>'+
                                           '</a>'+
                                      '</div');
            })
        },
//            <div class="items">
//                <a href="CommodityDetail.html?id=1" style="display:block">
//                <img src="images/apple.jpg">
//                <p>蘋果</p>
//                </a>
//            </div>
        error: err => {
            console.log(err)
        },
    });
}

function getById(){
//    var dataUrl = "http://172.16.82.2:9090/com/getById"
    var dataUrl = "http://localhost:9090/com/getById"
    var getUrlString = location.href;
    var url = new URL(getUrlString);
    var id = url.searchParams.get('id');
    var userdata = { id : id }

    $('#com-list').empty();

    $.ajax({
        url: dataUrl,
        method: 'GET',
        dataType: 'json',
        data: userdata,
        async: false,

        success: res => {
            console.log(res);
            $('#com-img').attr('src', res.com_img_path);
            $('#com-detail').text(res.com_detail);
        },

        error: err => {
            console.log(err)
        },
    });
}

function getByTag(obj){
//    var dataUrl = "http://172.16.82.2:9090/com/getByTag"
    var dataUrl = "http://localhost:9090/com/getByTag"
    var tag = $(obj).text();
    console.log(tag)
    var userdata = { tag : tag }
    console.log(userdata)

    $('#com-list').empty();

    $.ajax({
        url: dataUrl,
        method: 'GET',
        dataType: 'json',
        data: userdata,
        async: true,

        success: res => {
            console.log(res)
            $.each(res, function(i, n){
                $('#com-list').append('<div class="items">'+
                                           '<a href="CommodityDetail.html?id='+n.commodityID+'" style="display:block">'+
                                           '<img src='+n.commodityImgPath+'>'+
                                           '<p>'+n.commodityName+'</p>'+
                                           '</a>'+
                                      '</div');
            })
        },
//            <div class="items">
//                <a href="CommodityDetail.html?id=1" style="display:block">
//                <img src="images/apple.jpg">
//                <p>蘋果</p>
//                </a>
//            </div>
        error: err => {
            console.log(err)
        },
    });
}