var ip = initIP();
var key;
var value;
var chapters;
$(function() {
    init();
});

function init(){
    returnZindex();
    initBook()
    initAllChapter();
    openEdit();
    initContent()
}

var editor2;
function openEdit() {
    var E = window.wangEditor
    editor2 = new E('#contentId')
    editor2.customConfig.menus = [  //菜单配置，不要可以直接去掉这个默认全部功能都有如下图
        'head',
        'bold',
        'italic',
        'underline'
    ]
    editor2.create()
}
function initContent() {
    let contentMethod = {
        url:ip+"comment/"+value,
        method:"GET"
    }
    axios(contentMethod).then(data=>{
        console.log("88888",data.data.data.records)
        let contentJson = data.data.data.records;
        let nHtml="";
        for(let item of contentJson){
            if(item.user){
                nHtml+='<div class="ml-1"><img src="'+item.user.icon+'"  class="tx circle small m-0">'
                nHtml+='<span class="mb-5px reader" >'+item.user.name+'</span>'
                nHtml+='<div>'+item.content+'</div>'
                nHtml+='</div>'
                console.log("test",item.content)
            }
        }
        $("#allContent").html(nHtml)

    })
}
//发表评论
function addContent() {
    let content2 = editor2.txt.html();
    console.log("评论",content2)
    let params={};
    params.userId = sessionStorage.getItem("userId");
    if(!sessionStorage.getItem("userId")||!value){
        alertLayer2("提示","请登录后评论")
    }else{
        params.bookId = value;
        params.content = content2;
        let data=params;
        let addContentMethod={
            url:ip+"comment/",
            method:"POST",
            data:data
        }
        axios(addContentMethod).then(data=>{
            console.log("评论",data)
            alertLayer("编辑评论",data.data.msg,closeContent)
        })
    }

}
function closeContent() {
    layer.closeAll();
    // init();
    initContent();
}
function initAllChapter() {
    let Allchapters ={
        url:ip+"chapter/"+value,
        method:"GET"
    }
    axios(Allchapters).then(data=>{
        chapters = data.data.data.records;
    })
}
function initBook() {
    console.log(window.location.href)
    let href = window.location.href;
    let params = href.split("?")[1]
    let param = params.split("=");
    key = param[0];
    value = param[1];
    console.log(1,key,value)
    let book={
        url:ip+"book/"+value,
        method:"GET"
    }
    axios(book).then(data=>{
        let book = data.data.data;
        console.log(data.data.data)
        pjBook(book)
    })
}

function pjBook(book) {
    let nHtml="";
    nHtml+='<span class="BookLeft"><img class="img-book" src="'+book.cover+'"></span>'
    nHtml+='<div class="BookRight">'
    nHtml+='<div >书籍名称：'+book.name+''
    nHtml+='</div>'
    nHtml+='<div >作者：'+book.author+''
    nHtml+='</div>'
    nHtml+='<div class="desc lh-2 ">简介：'+book.synopsis+''
    nHtml+='</div>'
    nHtml+='<div class=" shelf-btn">'
    if(book.status==0){
        nHtml+='<div>由于版权原因，该书暂未上架</div>'
    }else{
        nHtml+='<button onclick="readBook()" class="layui-btn layui-btn-radius ">点击阅读</button>'
        nHtml+='<button onclick="findChapters()" class="layui-btn layui-btn-radius ">查看目录</button>'
        nHtml+='<button onclick="addShelf()" class="layui-btn layui-btn-primary layui-btn-radius ">加入书架</button>'
    }
    nHtml+='</div>'
    $("#main2").html(nHtml)
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
    console.log("ip"+ip)
    return ip;
}

function toIndex(){
    let ip = getChildIp();
    //前往首頁
    window.location.href = ip+"index.html"
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
//拼接目录
function pjChapters(allChapterMenu) {
    var nHtml = ''
    if(!allChapterMenu||allChapterMenu.length==0){
        nHtml+='<div>该书暂未更新</div>'
    }else{
        for(let i=0;i<allChapterMenu.length;i++){
            let item =allChapterMenu[i]
            var $row = JSON.stringify(item).replace(/\"/g,"'");
            let id = item.id
            nHtml+='<li  onclick="readBook2('+id+')" class="chapter-li">第'+item.sort+'章<span style="margin-left: 25%">'
                +item.title+'</span></li>'
        }
    }
    return nHtml;
}
//查看某本书的目录
function findChapters() {
    let allChapterMethod={
        url:ip+"chapter/"+value,
        method:"GET"
    }
    axios(allChapterMethod).then(data=>{
        console.log(data.data.data.records);
        let allChapterMenu = data.data.data.records;
        let html = pjChapters(allChapterMenu);
        openChapters(html)
    })


}
//彈出窗口模板2  带时间laydate的弹窗  html位js拼接的数据
function openChapters(html){
    layui.use(['form','layer'], function(){
        var form = layui.form;
        //數據驗證
        layer.open({
            // type:1,
            area: '450px',
            title:"目录",
            id:"chapterMenu",
            content: html  //這裡用html的拼接  nHtml
            //點擊右上角X
            //點擊登錄
            ,success: function(layero, index){
                layero.addClass('layui-form');
                // renderForm(data)
                form.render();
            },
        })
    })
}
function readBook() {
    if(!chapters.length){
        alertLayer2("提示","该书暂未更新")
    }else{
        window.location.href = ip+"readChapter.html?chapterId="+chapters[0].id
    }
}
function readBook2(id) {
    window.location.href = ip+"readChapter.html?chapterId="+id
}
function addShelf(){
    if(!sessionStorage.getItem("userId")){
        alertLayer2("提示","请先登录")
    }else{
        let data={
            bookId:value,
            userId:sessionStorage.getItem("userId")
        }
        let addbookObj = {
            url:ip+"BookRack/",
            method:"POST",
            data:data
        }
        axios(addbookObj).then(data2=>{
            if(data2.data.code==0||data2.data.code=="0"){
                alertLayer2("提示","加入书籍成功")
            }else{
                alertLayer2("提示",data2.data.msg)
            }

        }).catch(err=>alertLayer2("提示",err))
    }
}