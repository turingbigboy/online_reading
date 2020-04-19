var ip = initIP();
$(function() {
    init();

});
function init(){
    returnZindex();
    initTree();
    initMenu();

}

function returnZindex(){
    let dom = document.getElementById("main")
    dom.style.zIndex = 0;
}
function changeZindex(){
    let dom = document.getElementById("main")
    dom.style.zIndex = -1;
}
function initMenu() {
    let href = window.location.href;
    let params = href.split("?")[1]
    let param = params.split("=");
    key = param[0];
    value = param[1];
    var n1 = href.length;
    var n2 = href.indexOf("=");
    var name = decodeURI(href.substr(n2+1,n1-n2))
    changeContent(name)
}
function initTree(){
    let menuMethod={
        url:ip+"type/",
        method:"GET"
    }
    axios(menuMethod).then(data=>{
        menu = data.data.data.records;
        var nHtml = "";
        menu.forEach(item=>{
            var $row = JSON.stringify(item).replace(/\"/g,"'");
            nHtml+= '<li class="layui-nav-item layui-nav-itemed"><a href="'+ip+"menu.html?id="+item.id+'">'+item.typeName+'</a></li>'
        })
        $("#lay-tree").html(nHtml)
    })
}
//点击树
function changeContent(bookName){
    let bookMethod={
        url:ip+"book/",
        method:"GET",
        params:{
            name:bookName
        }
    }
    axios(bookMethod).then(data=>{
        console.log(data.data.data.records)
        let book = document.getElementById("book");
        let booksJson = data.data.data.records;
        let nHtml = "";
        booksJson.forEach(item=>{
            var $row = JSON.stringify(item).replace(/\"/g,"'");
            let itemDesc = item.synopsis
            nHtml+='<div class="oneEle"><img src="'+item.cover+'" class="img ib mr-1 menuBook cursor" onclick="toBook('+$row+')">'
            nHtml+='<div class="ib bookInfo">'
            nHtml+='<div class="title mb-1">书籍名称：'+item.name+'</div>'
            nHtml+='<div class="author mb-1">作者：'+item.author+'</div>'
            if(itemDesc.length>70){
                itemDesc = itemDesc.substring(0,70)+"...";

                nHtml+='<div class="author mb-1">简介：'+itemDesc+'<a class="cursor" onclick="toBook('+$row+')" >更多</a></div>'
            }else{
                nHtml+='<div class="author mb-1">简介：'+itemDesc+'</div>'
            }
            nHtml+='</div></div>'
            $("#book").html(nHtml)
        })
    })

}
function toBook(row) {
    console.log(row)
    window.location.href = ip+"clickBook.html?id="+row.id
}
// function toBook(id){
//   //TODO ajax根据bookid请求数据，获取书籍的信息 并跳转到clickBook页面,在这个页面根据id获取book信息，然后将json初始化
//   let ip = getChildIp();
//   // window.location.href = ip+'clickBook.html'
//   document.location.href("ip+'clickBook.html'?id="+id);
// }
function getChildIp(){
    const ip = myFrame.window.initIP();
    console.log("ip"+ip)
    return ip;
}