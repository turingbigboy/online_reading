var ip = initIP();
$(function() {
    init();
    initBookSize()
});
function init(){
    returnZindex();
    initMyShelf();

}
//layer的問題，需要改變zindex並且在調用彈出窗口時（登錄和退出時）改變z-index
function changeZindex(){
    let dom = document.getElementById("main")
    dom.style.zIndex = -1;
}
function returnZindex(){
    let dom = document.getElementById("main")
    dom.style.zIndex = 0;

}

//獲取iframe裡面的initIP方法
function getChildIp(){
    const ip = myFrame.window.initIP();
    return ip;
}
function initMyShelf(){
    let myshelfMethod={
        url:ip+"BookRack/"+sessionStorage.getItem("userId"),
        method:"GET"
    }
    axios(myshelfMethod).then(data=>{
        let myshelf2 = data.data.data.records;
        let nHtml = "";
        for(let i=0;i<myshelf2.length;i++){
            let item = myshelf2[i].book;
            let outItem = myshelf2[i]
            var $row = JSON.stringify(item).replace(/\"/g,"'");
            var $outrow = JSON.stringify(outItem).replace(/\"/g,"'");
            nHtml+='<div class="oneBook m-2 book-solid"><img src="'+item.cover+'"  class="oneBook" title="'+item.name+'" onclick="openBook('+$row+')"><div class="center">'
                +item.name+
                '</div><div class="center"><button class="layui-btn layui-btn-normal" onclick="openBook('+$row+')">查看</button><button class="layui-btn layui-btn-danger" onclick="deleteshelfBook('+$outrow+')">删除</button></div>'+
                '</div>'
        }
        // onclick=swiperItemClick('+JSON.stringify(item)+')
        // nHtml+='<div class="oneBook m-2 book-solid dotted" onclick=toIndex()><i class="layui-icon large">&#xe624;</i></div>'
        $("#books").html(nHtml)
    })
}
function deleteshelfBook(row) {
    quesForm("你確定要刪除"+row.book.name+"嗎？",deleteOneBook,row)
}
function deleteOneBook(row) {
    //  http://127.0.0.1:8080/online_reading/BookRack/{id:\\d+}
    let removeBook={
        url:ip+"BookRack/"+row.id,
        method:"DELETE"
    }
    axios(removeBook).then(data=>{
        alertLayer("移除书架",data.data.msg,closeForm);
    })
}
function closeForm() {
    layer.closeAll();
    init();
}
function toIndex(){
    let ip = getChildIp();
    //前往首頁
    window.location.href = ip+"index.html"
}
function openBook(item) {
    window.location.href=ip+"clickBook.html?id="+item.id;
}
function searchBook() {
    let dom = document.getElementById("bookSearch").value;
    if(!dom.trim()){
        alertLayer2("提示","请输入关键字查询")
    }else{
        let name = encodeURI(dom)
        window.location.href=ip+"search.html?name="+name
    }

}
function initBookSize() {
    let screenHeight = window.screen.height*80/100+"px";
    let screenWidth = window.screen.width*80/100+"px";
    $("#books")[0].style.width = screenWidth
    $("#books")[0].style.height = screenHeight
}